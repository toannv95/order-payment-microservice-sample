package org.toannguyen.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.toannguyen.interfaces.eventConsumer;
import org.toannguyen.models.events.orderPurchaseEvent;
import org.toannguyen.models.events.transactionEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Consumer;
import java.util.function.Supplier;
@Configuration
public class orderServiceConfig {
    private final eventConsumer<transactionEvent> transactionEventConsumer;

    @Autowired
    public orderServiceConfig(eventConsumer<transactionEvent> transactionEventConsumer) {
        this.transactionEventConsumer = transactionEventConsumer;
    }

    @Bean
    public Sinks.Many<orderPurchaseEvent> sink() {
        return Sinks.many()
                .multicast()
                .directBestEffort();
    }

    @Bean
    public Supplier<Flux<orderPurchaseEvent>> orderPurchaseEventPublisher(
            Sinks.Many<orderPurchaseEvent> publisher) {
        return publisher::asFlux;
    }

    @Bean
    public Consumer<transactionEvent> transactionEventProcessor() {
        return transactionEventConsumer::consumeEvent;
    }
}
