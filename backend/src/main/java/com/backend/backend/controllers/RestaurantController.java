package com.backend.backend.controllers;

import com.backend.backend.models.Restaurant;
import com.backend.backend.services.RestaurantService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@SecurityRequirement(name = "bearerAuth")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public ResponseEntity getRestaurants() {
        return restaurantService.getRestaurants();
    }

    @PostMapping("/restaurant")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.addRestaurant(restaurant);
    }

    @PutMapping("/restaurant/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable Integer id) {
        return restaurantService.updateRestaurant(restaurant, id);
    }

    @DeleteMapping("/restaurant/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteRestaurant(@PathVariable Integer id) {
        return restaurantService.deleteRestaurant(id);
    }
}
