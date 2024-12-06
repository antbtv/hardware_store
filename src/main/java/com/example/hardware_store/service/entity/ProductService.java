package com.example.hardware_store.service.entity;

import com.example.hardware_store.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<Product> findProductById(Long id);

    List<Product> findAllProducts();

    void saveProduct(Product product);

    void updateProduct(Product product);

    void deleteProductById(Long id);
}