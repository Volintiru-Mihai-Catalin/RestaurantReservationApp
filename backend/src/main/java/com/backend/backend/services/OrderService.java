package com.backend.backend.services;

import com.backend.backend.dto.OrderRequestBody;
import com.backend.backend.models.Order;
import com.backend.backend.models.Product;
import com.backend.backend.models.RestaurantTable;
import com.backend.backend.repositories.OrderRepository;
import com.backend.backend.repositories.ProductRepository;
import com.backend.backend.repositories.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final TableRepository tableRepository;
    private final ProductRepository productRepository;

    public ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<Order>> getOrdersByTable(Integer tableId) {
        Optional<RestaurantTable> optionalTable = tableRepository.findById(tableId);

        if (optionalTable.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(orderRepository.findAllByRestaurantTable(optionalTable.get()), HttpStatus.OK);
    }

    public ResponseEntity<Order> addOrder(OrderRequestBody orderRequestBody) {
        Optional<Product> optionalProduct = productRepository.findById(orderRequestBody.getProductId());
        if (optionalProduct.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Optional<RestaurantTable> optionalTable = tableRepository.findById(orderRequestBody.getTableId());
        if (optionalTable.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Order newOrder = Order
                .builder()
                .product(optionalProduct.get())
                .restaurantTable(optionalTable.get())
                .build();
        return new ResponseEntity<>(orderRepository.save(newOrder), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteOrder(Integer orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isEmpty()) {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }

        orderRepository.delete(orderOptional.get());
        return new ResponseEntity<>("Order deleted", HttpStatus.OK);
    }

}
