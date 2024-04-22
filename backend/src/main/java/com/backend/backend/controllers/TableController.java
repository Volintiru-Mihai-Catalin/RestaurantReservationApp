package com.backend.backend.controllers;

import com.backend.backend.dto.RestaurantTableRequestBody;
import com.backend.backend.dto.RestaurantTableUpdateRequestBody;
import com.backend.backend.models.RestaurantTable;
import com.backend.backend.services.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TableController {
    private final TableService tableService;

    @GetMapping("/tables")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RestaurantTable>> getTables() {
        return tableService.getTables();
    }

    @PostMapping("/table")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RestaurantTable> addTable(@RequestBody RestaurantTableRequestBody restaurantTableRequestBody) {
        return tableService.addTable(restaurantTableRequestBody);
    }

    @PutMapping("/table/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RestaurantTable> updateTable(@RequestBody RestaurantTableUpdateRequestBody restaurantTableUpdateRequestBody,
                                                       @PathVariable Integer id) {
        return tableService.updateTable(restaurantTableUpdateRequestBody, id);
    }

    @DeleteMapping("/table/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteTable(@PathVariable Integer id) {
        return tableService.deleteTable(id);
    }
}
