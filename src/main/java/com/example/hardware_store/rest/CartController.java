//package com.example.hardware_store.rest;
//
//import com.example.hardware_store.entity.Cart;
//import com.example.hardware_store.service.entity.CartService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/carts")
//@RequiredArgsConstructor
//public class CartController {
//
//    private final CartService cartService;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
//        return cartService.findCartById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Cart>> getAllCarts() {
//        List<Cart> carts = cartService.findAllCarts();
//        if (carts.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(carts);
//    }
//
//    @PostMapping
//    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
//        cartService.saveCart(cart);
//        return ResponseEntity.ok(cart);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart cart) {
//        cart.setId(id);
//        cartService.updateCart(cart);
//        return ResponseEntity.ok(cart);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
//        cartService.deleteCartById(id);
//        return ResponseEntity.noContent().build();
//    }
//}
