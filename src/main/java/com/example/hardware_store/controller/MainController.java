package com.example.hardware_store.controller;

import com.example.hardware_store.service.entity.implementation.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class MainController {
    private final ProductServiceImpl productService;

    @Autowired
    public MainController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String getAllProduct(Model model){
        model.addAttribute("products", productService.findAllProducts());
        return "product/product";
    }

    @GetMapping("/info/{id}")
    public String infoProduct(@PathVariable("id") Long id, Model model){
        model.addAttribute("product", productService.findProductById(id));
        return "product/infoProduct";
    }
}