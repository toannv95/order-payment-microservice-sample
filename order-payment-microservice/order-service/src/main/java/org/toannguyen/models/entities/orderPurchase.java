package org.toannguyen.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.toannguyen.enums.orderStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
@ToString
public class orderPurchase {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    private Integer productId;
    private double price;
    private orderStatus status;
}
