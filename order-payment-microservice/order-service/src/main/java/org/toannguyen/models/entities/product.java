package org.toannguyen.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
public class product {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer productId;
    private double price;
}
