package com.backend.backend.controllers;

import com.backend.backend.models.Waiter;
import com.backend.backend.services.WaiterService;
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
public class WaiterController {
    private final WaiterService waiterService;

    @GetMapping("/waiters")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getWaiters() {
        return waiterService.getWaiters();
    }

    @PostMapping("/waiter")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity addWaiter(@RequestBody Waiter waiter) {
        return waiterService.addWaiter(waiter);
    }

    @PutMapping("/waiter/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateWaiter(@RequestBody Waiter waiter, @PathVariable Integer id) {
        return waiterService.updateWaiter(waiter, id);
    }

    @DeleteMapping("/waiter/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteWaiter(@PathVariable Integer id) {
        return waiterService.deleteWaiter(id);
    }
}
