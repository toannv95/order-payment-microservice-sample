package org.toannguyen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.toannguyen.models.entities.user;

@Repository
public interface userRepository extends JpaRepository<user, Integer> {
}
