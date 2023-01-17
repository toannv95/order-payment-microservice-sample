package org.toannguyen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.toannguyen.enums.orderStatus;
import org.toannguyen.models.entities.order;
import org.toannguyen.models.entities.orderPurchase;
import org.toannguyen.processors.orderPurchaseProcessor;
import org.toannguyen.repositories.orderPurchaseRepository;
import org.toannguyen.repositories.productRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

@Service
public class orderService {
    private final orderPurchaseRepository orderPurchaseRepository;
    private final orderPurchaseProcessor orderPurchaseProcessor;
    private final productRepository productRepository;
    private final Scheduler jdbcScheduler;

    @Autowired
    public orderService(
            orderPurchaseRepository orderPurchaseRepository,
            orderPurchaseProcessor orderPurchaseProcessor,
            productRepository productRepository, Scheduler jdbcScheduler) {
        this.orderPurchaseRepository = orderPurchaseRepository;
        this.orderPurchaseProcessor = orderPurchaseProcessor;
        this.productRepository = productRepository;
        this.jdbcScheduler = jdbcScheduler;
    }

    public Mono<orderPurchase> createOrder(order order) {
        orderPurchase orderPurchase = getOrderPurchase(order);
        orderPurchase savedOrderPurchase = orderPurchaseRepository.save(orderPurchase);
        orderPurchaseProcessor.process(orderPurchase);
        return Mono.just(savedOrderPurchase);
    }

    public Flux<orderPurchase> getAll() {
        return Flux.defer(
                        () -> Flux.fromIterable(orderPurchaseRepository.findAll()))
                .subscribeOn(jdbcScheduler);
    }

    public Flux<List<orderPurchase>> reactiveGetAll() {
        Flux<Long> interval = Flux.interval(Duration.ofMillis(2000));
        Flux<List<orderPurchase>> orderPurchaseFlux = Flux.fromStream(
                Stream.generate(orderPurchaseRepository::findAll));
        return Flux.zip(interval, orderPurchaseFlux)
                .map(Tuple2::getT2);
    }

    public Mono<orderPurchase> getOrderById(Integer id) {
        return Mono.fromCallable(
                        () -> orderPurchaseRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(
                                        HttpStatus.NOT_FOUND, "Order id: " + id + " does not exist")))
                .subscribeOn(jdbcScheduler);
    }

    private orderPurchase getOrderPurchase(final order order) {
        Integer productId = order.getProductId();
        var newOrderPurchase = new orderPurchase();
        newOrderPurchase.setProductId(productId);
        newOrderPurchase.setUserId(order.getUserId());
        newOrderPurchase.setPrice(productRepository.findById(productId)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Product ID: " + productId + " does not exist"))
                        .getPrice());
        newOrderPurchase.setStatus(orderStatus.CREATED);
        return newOrderPurchase;
    }
}
