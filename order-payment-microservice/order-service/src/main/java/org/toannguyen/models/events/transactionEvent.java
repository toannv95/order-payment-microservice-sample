package org.toannguyen.models.events;

import lombok.*;
import org.toannguyen.enums.transactionStatus;
import org.toannguyen.interfaces.event;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class transactionEvent implements event {
    private static final String EVENT = "Transaction";
    private Integer orderId;
    private transactionStatus status;
    @Override
    public String getEvent() {
        return EVENT;
    }
}
