package com.backend.backend.repositories;

import com.backend.backend.models.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WaiterRepository extends JpaRepository<Waiter, Integer> {
    Optional<Waiter> findByFirstNameAndAndLastName(String firstName, String lastName);
}
