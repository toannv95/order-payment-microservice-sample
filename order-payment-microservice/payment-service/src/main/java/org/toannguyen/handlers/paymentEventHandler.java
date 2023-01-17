package org.toannguyen.handlers;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.toannguyen.models.entities.transaction;
import org.toannguyen.models.events.paymentEvent;
import org.toannguyen.models.events.transactionEvent;
import org.toannguyen.repositories.transactionRepository;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import static org.toannguyen.enums.paymentStatus.APPROVED;
import static org.toannguyen.enums.transactionStatus.SUCCESSFUL;
import static org.toannguyen.enums.transactionStatus.UNSUCCESSFUL;

@Component
public class paymentEventHandler implements eventHandler<paymentEvent, transactionEvent> {
    private final transactionRepository transactionRepository;
    private final Scheduler jdbcScheduler;

    @Autowired
    public paymentEventHandler(
            transactionRepository transactionRepository,
            Scheduler jdbcScheduler) {
        this.transactionRepository = transactionRepository;
        this.jdbcScheduler = jdbcScheduler;
    }

    @Transactional
    public transactionEvent handleEvent(paymentEvent event) {
        var newTransaction = new transaction();
        newTransaction.setOrderId(event.getOrderId());
        newTransaction.setPrice(event.getPrice());
        Mono.fromRunnable(() -> transactionRepository.save(newTransaction))
                .subscribeOn(jdbcScheduler)
                .subscribe();
        var newtransactionEvent = new transactionEvent();
        newtransactionEvent.setOrderId(event.getOrderId());
        newtransactionEvent.setStatus(APPROVED.equals(event.getStatus())
                ? SUCCESSFUL
                : UNSUCCESSFUL);
        return newtransactionEvent;

    }
}
