package com.backend.backend.services;

import com.backend.backend.dto.RestaurantTableRequestBody;
import com.backend.backend.dto.RestaurantTableUpdateRequestBody;
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
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TableService {
    private final TableRepository tableRepository;
    private final WaiterRepository waiterRepository;
    private final RestaurantRepository restaurantRepository;

    public ResponseEntity getTables() {
        return new ResponseEntity<>(tableRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity addTable(RestaurantTableRequestBody restaurantTableRequestBody) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository
                .findByName(restaurantTableRequestBody.getRestaurantName());

        if (optionalRestaurant.isEmpty()) {
            return new ResponseEntity<>("Restaurant not found!", HttpStatus.NOT_FOUND);
        }

        List<Integer> waiterIds = waiterRepository.findAll().stream().map(Waiter::getWaiterId).toList();
        Optional<Waiter> optionalWaiter = waiterRepository.findById(waiterIds.get(new Random().nextInt(waiterIds.size())));


        if (optionalWaiter.isEmpty()) {
            return new ResponseEntity<>("Waiter not found!", HttpStatus.NOT_FOUND);
        }

        RestaurantTable restaurantTable = RestaurantTable
                .builder()
                .capacity(restaurantTableRequestBody.getCapacity())
                .restaurant(optionalRestaurant.get())
                .waiter(optionalWaiter.get())
                .build();

        return new ResponseEntity<>(tableRepository.save(restaurantTable), HttpStatus.CREATED);
    }

    public ResponseEntity updateTable(RestaurantTableUpdateRequestBody restaurantTableUpdateRequestBody,
                                                       Integer id) {
        Optional<RestaurantTable> optionalRestaurantTable = tableRepository.findById(id);

        if (optionalRestaurantTable.isEmpty()) {
            return new ResponseEntity<>("Table not found!", HttpStatus.NOT_FOUND);
        }

        Optional<Waiter> optionalWaiter = waiterRepository.findById(restaurantTableUpdateRequestBody.getWaiterId());

        if (optionalWaiter.isEmpty()) {
            return new ResponseEntity<>("Waiter not found!", HttpStatus.NOT_FOUND);
        }

        RestaurantTable tableToUpdate = optionalRestaurantTable.get();
        tableToUpdate.setCapacity(restaurantTableUpdateRequestBody.getCapacity());
        tableToUpdate.setWaiter(optionalWaiter.get());

        return new ResponseEntity<>(tableRepository.save(tableToUpdate), HttpStatus.OK);
    }

    public ResponseEntity deleteTable(Integer id) {
        Optional<RestaurantTable> restaurantTableOptional = tableRepository.findById(id);

        if (restaurantTableOptional.isEmpty()) {
            return new ResponseEntity<>("Table Not Found", HttpStatus.NOT_FOUND);
        }

        tableRepository.delete(restaurantTableOptional.get());
        return new ResponseEntity<>("Table deleted", HttpStatus.OK);
    }
}
