package org.toannguyen.processors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.toannguyen.models.entities.orderPurchase;
import org.toannguyen.models.events.orderPurchaseEvent;
import reactor.core.publisher.Sinks;

@Component
public class orderPurchaseProcessor {
    private final Sinks.Many<orderPurchaseEvent> sink;

    @Autowired
    public orderPurchaseProcessor(Sinks.Many<orderPurchaseEvent> sink) {
        this.sink = sink;
    }

    public void process(orderPurchase orderPurchase) {
        orderPurchaseEvent orderPurchaseEvent = new orderPurchaseEvent();
        orderPurchaseEvent.setUserId(orderPurchase.getUserId());
        orderPurchaseEvent.setOrderId(orderPurchase.getId());
        orderPurchaseEvent.setPrice(orderPurchase.getPrice());
        sink.emitNext(orderPurchaseEvent, Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
