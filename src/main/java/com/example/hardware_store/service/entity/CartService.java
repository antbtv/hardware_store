package com.example.hardware_store.service.entity;

import com.example.hardware_store.entity.Cart;

import java.util.List;
import java.util.Optional;

public interface CartService {

    Optional<Cart> findCartById(Long id);

    List<Cart> findAllCarts();

    void saveCart(Cart cart);

    void updateCart(Cart cart);

    void deleteCartById(Long id);

    void addProductToCart(Long productId, Integer quantity, Long userId);
}
