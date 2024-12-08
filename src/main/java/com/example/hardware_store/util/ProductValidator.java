package com.example.hardware_store.util;

import com.example.hardware_store.entity.Product;
import com.example.hardware_store.service.entity.implementation.ProductServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {

    private final ProductServiceImpl productService;

    public ProductValidator(ProductServiceImpl productService) {
        this.productService = productService;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        if(productService.findProductByName(product) != null){
            errors.rejectValue("title", "","Данное наименование товара уже используется");
        }
    }
}