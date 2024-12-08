package com.example.hardware_store.service.entity.implementation;

import com.example.hardware_store.entity.Cart;
import com.example.hardware_store.repository.CartRepository;
import com.example.hardware_store.service.entity.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public Optional<Cart> findCartById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public List<Cart> findAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void updateCart(Cart cart) {
        cartRepository.saveAndFlush(cart);
    }

    @Override
    public void deleteCartById(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public void addProductToCart(Long productId, Integer quantity, Long userId) {
        cartRepository.addToCart(productId, quantity, userId);
    }

    @Transactional
    public void removeFromCartByUserId(Long productId,Long userId) {
        cartRepository.deleteCartById(productId, userId);
    }


}
