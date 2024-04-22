package com.backend.backend.services;

import com.backend.backend.models.Product;
import com.backend.backend.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Product> addProduct(Product product) {
        Optional<Product> optionalProduct = productRepository.findByProductName(product.getProductName());

        if (optionalProduct.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productRepository.save(product), HttpStatus.OK);
    }

    public ResponseEntity<Product> updateProduct(Product product, Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Product productToUpdate = optionalProduct.get();
        Optional<Product> productOptional = productRepository.findByProductName(product.getProductName());
        if (productOptional.isPresent()) {
            if (!productToUpdate.getProductId().equals(productOptional.get().getProductId())) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
        }

        productToUpdate.setProductName(product.getProductName());
        productToUpdate.setPrice(product.getPrice());



        return new ResponseEntity<>(productRepository.save(productToUpdate), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteProduct(Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isEmpty()) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        productRepository.delete(optionalProduct.get());
        return new ResponseEntity<>("Product deleted", HttpStatus.OK);
    }
}
