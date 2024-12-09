package com.example.hardware_store.repository;

import com.example.hardware_store.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(Long id);

    @Modifying
    @Query(value = "delete from cart where product_id=?1 and user_id=?2", nativeQuery = true)
    void deleteCartById(Long id, Long userId);

    @Modifying
    @Query(value = "call add_to_cart(?, ?, ?)", nativeQuery = true)
    void addToCart(Long productId, Integer quantity, Long userId);
}