package com.backend.backend.controllers;

import com.backend.backend.models.Waiter;
import com.backend.backend.services.WaiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WaiterController {
    private final WaiterService waiterService;

    @GetMapping("/waiters")
    public ResponseEntity<List<Waiter>> getWaiters() {
        return waiterService.getWaiters();
    }

    @PostMapping("/waiter")
    public ResponseEntity<Waiter> addWaiter(@RequestBody Waiter waiter) {
        return waiterService.addWaiter(waiter);
    }

    @PutMapping("/waiter/{id}")
    public ResponseEntity<Waiter> updateWaiter(@RequestBody Waiter waiter, @PathVariable Integer id) {
        return waiterService.updateWaiter(waiter, id);
    }

    @DeleteMapping("/waiter/{id}")
    public ResponseEntity<String> deleteWaiter(@PathVariable Integer id) {
        return waiterService.deleteWaiter(id);
    }
}