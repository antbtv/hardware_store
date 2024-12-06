package com.example.hardware_store.service.entity.implementation;

import com.example.hardware_store.entity.Product;
import com.example.hardware_store.entity.Role;
import com.example.hardware_store.entity.User;
import com.example.hardware_store.repository.ProductRepository;
import com.example.hardware_store.repository.RoleRepository;
import com.example.hardware_store.repository.UserRepository;
import com.example.hardware_store.service.entity.ProductService;
import com.example.hardware_store.service.entity.RoleService;
import com.example.hardware_store.service.entity.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.saveAndFlush(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}