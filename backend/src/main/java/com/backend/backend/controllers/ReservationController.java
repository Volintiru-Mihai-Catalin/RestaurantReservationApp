package com.backend.backend.controllers;

import com.backend.backend.dto.ReservationGetDTO;
import com.backend.backend.dto.ReservationTableRequestBody;
import com.backend.backend.models.Reservation;
import com.backend.backend.services.ReservationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/reservations")
    public ResponseEntity getReservations() {
        return reservationService.getReservations();
    }

    @PostMapping("/reservation")
    public ResponseEntity postReservation(@RequestBody ReservationTableRequestBody reservationTableRequestBody) {
        return reservationService.addReservation(reservationTableRequestBody);
    }
}
