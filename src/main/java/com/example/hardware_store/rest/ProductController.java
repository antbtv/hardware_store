package com.example.hardware_store.rest;

import com.example.hardware_store.service.entity.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
}
