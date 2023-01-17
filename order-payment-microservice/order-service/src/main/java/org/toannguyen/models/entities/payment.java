package org.toannguyen.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
public class payment {
    @Id
    @GeneratedValue
    private Integer id;
    private BigDecimal amount;
}
