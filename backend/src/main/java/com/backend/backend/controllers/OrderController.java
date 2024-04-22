package com.backend.backend.controllers;

import com.backend.backend.dto.OrderRequestBody;
import com.backend.backend.models.Order;
import com.backend.backend.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/orders")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Order>> getOrders() {
        return orderService.getOrders();
    }

    @PostMapping("/order")
    public ResponseEntity<Order> addOrders(@RequestBody OrderRequestBody orderRequestBody) {
        return orderService.addOrder(orderRequestBody);
    }

    @PostMapping("/order/table_{id}")
    public ResponseEntity<List<Order>> getOrdersByTable(@PathVariable Integer id) {
        return orderService.getOrdersByTable(id);
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
        return orderService.deleteOrder(id);
    }
}
