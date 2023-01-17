package org.toannguyen.interfaces;

public interface eventConsumer<T extends event> {
    void consumeEvent(T event);
}
