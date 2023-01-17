package org.toannguyen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.toannguyen.models.entities.orderPurchase;

@Repository
public interface orderPurchaseRepository extends JpaRepository<orderPurchase, Integer> {
}
