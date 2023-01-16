package org.toannguyen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.toannguyen.models.entities.order;
import org.toannguyen.models.entities.orderPurchase;
import org.toannguyen.services.orderService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class orderController {
    private final orderService orderService;
    @Autowired
    public orderController(orderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("orders/create")
    public Mono<orderPurchase> createOrder(@RequestBody order order) {
        return orderService.createOrder(order);
    }
    @GetMapping("orders/all")
    public Flux<orderPurchase> getAllOrders() {
        return orderService.getAll();
    }
    @GetMapping(path = "orders/all/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<List<orderPurchase>> getAllOrdersStream() {
        return orderService.reactiveGetAll();
    }
    @GetMapping("orders/{id}")
    public Mono<orderPurchase> getOrderById(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }

}
