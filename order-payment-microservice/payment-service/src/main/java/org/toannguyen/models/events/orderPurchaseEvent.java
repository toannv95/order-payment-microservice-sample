package org.toannguyen.models.events;

import lombok.*;
import org.toannguyen.interfaces.event;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class orderPurchaseEvent implements event {
    private static final String EVENT = "OrderPurchase";

    private Integer orderId;
    private Integer userId;
    private double price;
    @Override
    public String getEvent() {
        return EVENT;
    }
}
