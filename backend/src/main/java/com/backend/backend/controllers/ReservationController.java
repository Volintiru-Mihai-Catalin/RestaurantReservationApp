package com.backend.backend.controllers;

import com.backend.backend.dto.ReservationTableRequestBody;
import com.backend.backend.models.Reservation;
import com.backend.backend.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/reservations")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Reservation>> getReservations() {
        return reservationService.getReservations();
    }

    @PostMapping("/reservation")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Reservation> postReservation(@RequestBody ReservationTableRequestBody reservationTableRequestBody) {
        return reservationService.addReservation(reservationTableRequestBody);
    }

    @DeleteMapping("/reservation/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteReservation(@PathVariable Integer id) {
        return reservationService.deleteReservation(id);
    }
}
