package org.toannguyen.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.toannguyen.handlers.eventHandler;
import org.toannguyen.models.events.orderPurchaseEvent;
import org.toannguyen.models.events.paymentEvent;
import org.toannguyen.models.events.transactionEvent;

import java.beans.EventHandler;
import java.util.function.Function;

@Configuration
public class paymentServiceConfig {
    private final eventHandler<paymentEvent, transactionEvent> paymentEventHandler;
    private final eventHandler<orderPurchaseEvent, paymentEvent> orderPurchaseEventHandler;

    @Autowired
    public paymentServiceConfig(
            eventHandler<paymentEvent, transactionEvent> paymentEventHandler,
            eventHandler<orderPurchaseEvent, paymentEvent> orderPurchaseEventHandler) {
        this.paymentEventHandler = paymentEventHandler;
        this.orderPurchaseEventHandler = orderPurchaseEventHandler;
    }

    @Bean
    public Function<orderPurchaseEvent, paymentEvent> orderPurchaseEventProcessor() {
        return orderPurchaseEventHandler::handleEvent;
    }

    @Bean
    public Function<paymentEvent, transactionEvent> paymentEventSubscriber() {
        return paymentEventHandler::handleEvent;
    }
}
