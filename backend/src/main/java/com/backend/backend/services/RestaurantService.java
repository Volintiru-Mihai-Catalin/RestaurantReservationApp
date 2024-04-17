package com.backend.backend.services;

import com.backend.backend.models.Restaurant;
import com.backend.backend.repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public ResponseEntity<List<Restaurant>> getRestaurants() {
        return new ResponseEntity<>(restaurantRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Restaurant> addRestaurant(Restaurant restaurant) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findByName(restaurant.getName());

        if (optionalRestaurant.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(restaurantRepository.save(restaurant), HttpStatus.CREATED);
    }

    public ResponseEntity<Restaurant> updateRestaurant(Restaurant restaurant, Integer id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);

        if (optionalRestaurant.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Optional<Restaurant> restaurantOptional = restaurantRepository.findByName(restaurant.getName());
        if (restaurantOptional.isPresent()) {
            if (!Objects.equals(restaurantOptional, optionalRestaurant))
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

        Restaurant restaurantToUpdate = optionalRestaurant.get();
        restaurantToUpdate.setAddress(restaurant.getAddress());
        restaurantToUpdate.setName(restaurant.getName());
        restaurantToUpdate.setPhoneNumber(restaurant.getPhoneNumber());

        return new ResponseEntity<>(restaurantRepository.save(restaurantToUpdate), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteRestaurant(Integer id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);

        if (optionalRestaurant.isEmpty()) {
            return new ResponseEntity<>("Restaurant not found", HttpStatus.NOT_FOUND);
        }

        restaurantRepository.delete(optionalRestaurant.get());
        return new ResponseEntity<>("Restaurant deleted", HttpStatus.OK);
    }
}
