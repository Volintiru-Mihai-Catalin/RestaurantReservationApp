package com.backend.backend.controllers;

import com.backend.backend.dto.RestaurantTableRequestBody;
import com.backend.backend.models.RestaurantTable;
import com.backend.backend.services.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TableController {
    private final TableService tableService;

    @GetMapping("/tables")
    public ResponseEntity<List<RestaurantTable>> getTables() {
        return tableService.getTables();
    }

    @PostMapping("/table")
    public ResponseEntity<RestaurantTable> addTable(@RequestBody RestaurantTableRequestBody restaurantTableRequestBody) {
        return tableService.addTable(restaurantTableRequestBody);
    }
}
