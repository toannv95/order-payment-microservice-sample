package org.toannguyen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.toannguyen.models.entities.transaction;

@Repository
public interface transactionRepository extends JpaRepository<transaction, Integer> {
}
