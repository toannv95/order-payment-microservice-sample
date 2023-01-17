package org.toannguyen.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "users")
public class user {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    private double balance;
}
