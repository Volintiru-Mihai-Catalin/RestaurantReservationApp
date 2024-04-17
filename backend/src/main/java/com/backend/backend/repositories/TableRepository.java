package com.backend.backend.repositories;

import com.backend.backend.models.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<RestaurantTable, Integer> {
}
