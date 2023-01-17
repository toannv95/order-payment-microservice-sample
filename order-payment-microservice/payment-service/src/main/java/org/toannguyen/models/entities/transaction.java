package org.toannguyen.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@ToString
public class transaction {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer orderId;
    private double price;
}
