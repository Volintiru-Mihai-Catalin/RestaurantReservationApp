package com.backend.backend.services;

import com.backend.backend.dto.RestaurantTableRequestBody;
import com.backend.backend.models.Restaurant;
import com.backend.backend.models.RestaurantTable;
import com.backend.backend.models.Waiter;
import com.backend.backend.repositories.RestaurantRepository;
import com.backend.backend.repositories.TableRepository;
import com.backend.backend.repositories.WaiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TableService {
    private final TableRepository tableRepository;
    private final WaiterRepository waiterRepository;
    private final RestaurantRepository restaurantRepository;

    public ResponseEntity<List<RestaurantTable>> getTables() {
        return new ResponseEntity<>(tableRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<RestaurantTable> addTable(RestaurantTableRequestBody restaurantTableRequestBody) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository
                .findByName(restaurantTableRequestBody.getRestaurantName());

        if (optionalRestaurant.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Optional<Waiter> optionalWaiter = waiterRepository.findById(1);

        if (optionalWaiter.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        RestaurantTable restaurantTable = RestaurantTable
                .builder()
                .capacity(restaurantTableRequestBody.getCapacity())
                .restaurant(optionalRestaurant.get())
                .waiter(optionalWaiter.get())
                .build();

        return new ResponseEntity<>(tableRepository.save(restaurantTable), HttpStatus.CREATED);
    }
}
