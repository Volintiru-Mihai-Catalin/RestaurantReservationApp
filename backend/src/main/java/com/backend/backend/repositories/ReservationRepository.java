package com.backend.backend.repositories;

import com.backend.backend.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Date> {
    Optional<Reservation> findByClientEmail(String email);
}
