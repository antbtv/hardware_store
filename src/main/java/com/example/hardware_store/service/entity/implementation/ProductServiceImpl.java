package com.example.hardware_store.service.entity.implementation;

import com.example.hardware_store.entity.Product;
import com.example.hardware_store.repository.ProductRepository;
import com.example.hardware_store.service.entity.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final JdbcTemplate jdbcTemplate;

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

    public Long getProductId(Product product) {
        return product.getId();
    }

    @Override
    public void updateProduct(Long id, Product product) {
        product.setId(id);
        productRepository.saveAndFlush(product);
    }

    @Override
    public void deleteProductById(Long id) {
        String sql = "DELETE FROM product WHERE product_id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Product  findProductByName(Product product){
        Optional<Product> product_db = productRepository.findByName(product.getName());
        return product_db.orElse(null);
    }
}