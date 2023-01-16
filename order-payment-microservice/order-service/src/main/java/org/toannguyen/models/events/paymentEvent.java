package org.toannguyen.models.events;

import lombok.*;
import org.toannguyen.enums.paymentStatus;
import org.toannguyen.interfaces.event;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class paymentEvent implements event {
    private static final String EVENT = "Payment";
    private Integer orderId;
    private paymentStatus status;
    private double price;
    @Override
    public String getEvent() {
        return EVENT;
    }
}
