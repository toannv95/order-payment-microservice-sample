package org.toannguyen.handlers;

import org.toannguyen.interfaces.event;

public interface eventHandler<T extends event, R extends event> {

    R handleEvent(T event);
}
