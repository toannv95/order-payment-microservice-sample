package org.toannguyen.consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.toannguyen.interfaces.eventConsumer;
import org.toannguyen.models.entities.orderPurchase;
import org.toannguyen.models.events.transactionEvent;
import org.toannguyen.repositories.orderPurchaseRepository;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import static org.toannguyen.enums.orderStatus.COMPLETED;
import static org.toannguyen.enums.orderStatus.FAILED;
import static org.toannguyen.enums.transactionStatus.SUCCESSFUL;

@Component
public class transactionEventConsumer implements eventConsumer<transactionEvent> {
    private final orderPurchaseRepository orderPurchaseRepository;
    private final Scheduler jdbcScheduler;

    @Autowired
    public transactionEventConsumer(
            orderPurchaseRepository orderPurchaseRepository,
            Scheduler jdbcScheduler) {
        this.orderPurchaseRepository = orderPurchaseRepository;
        this.jdbcScheduler = jdbcScheduler;
    }

    public void consumeEvent(transactionEvent event) {
        Mono.fromRunnable(
                        () -> orderPurchaseRepository.findById(event.getOrderId())
                                .ifPresent(order -> {
                                    setStatus(event, order);
                                    orderPurchaseRepository.save(order);
                                }))
                .subscribeOn(jdbcScheduler)
                .subscribe();
    }

    private void setStatus(transactionEvent transactionEvent, orderPurchase order) {
        order.setStatus(SUCCESSFUL.equals(transactionEvent.getStatus())
                ? COMPLETED
                : FAILED);
    }
}
