package com.backend.backend.repositories;

import com.backend.backend.models.Order;
import com.backend.backend.models.Product;
import com.backend.backend.models.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByRestaurantTable(RestaurantTable table);
}
