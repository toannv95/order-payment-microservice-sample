package org.toannguyen.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.toannguyen.models.entities.transaction;
import org.toannguyen.services.transactionService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("transactions")
public class transactionController {
    private final transactionService transactionService;

    @Autowired
    public transactionController(transactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping()
    public Flux<transaction> getAllOrders() {
        return transactionService.getAll();
    }

    @GetMapping("{id}")
    public Mono<transaction> getOrderById(@PathVariable Integer id) {
        return transactionService.getOrderById(id);
    }
}
