package org.toannguyen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.toannguyen.models.entities.product;

@Repository
public interface productRepository extends JpaRepository<product, Integer> {
}
