package com.backend.backend.controllers;

import com.backend.backend.models.Restaurant;
import com.backend.backend.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public ResponseEntity<List<Restaurant>> getRestaurants() {
        return restaurantService.getRestaurants();
    }

    @PostMapping("/restaurant")
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.addRestaurant(restaurant);
    }

    @PutMapping("/restaurant/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable Integer id) {
        return restaurantService.updateRestaurant(restaurant, id);
    }

    @DeleteMapping("/restaurant/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Integer id) {
        return restaurantService.deleteRestaurant(id);
    }
}
