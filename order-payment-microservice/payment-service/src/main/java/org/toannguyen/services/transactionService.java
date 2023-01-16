package org.toannguyen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.toannguyen.models.entities.transaction;
import org.toannguyen.repositories.transactionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

@Service
public class transactionService {
    private final transactionRepository transactionRepository;
    private final Scheduler jdbcScheduler;

    @Autowired
    public transactionService(transactionRepository transactionRepository,
                              Scheduler jdbcScheduler) {
        this.transactionRepository = transactionRepository;
        this.jdbcScheduler = jdbcScheduler;
    }

    public Flux<transaction> getAll() {
        return Flux.defer(
                        () -> Flux.fromIterable(transactionRepository.findAll()))
                .subscribeOn(jdbcScheduler);
    }

    public Mono<transaction> getOrderById(Integer id) {
        return Mono.fromCallable(
                        () -> transactionRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(
                                        HttpStatus.NOT_FOUND, "Transaction id: " + id + " does not exist")))
                .subscribeOn(jdbcScheduler);
    }
}
