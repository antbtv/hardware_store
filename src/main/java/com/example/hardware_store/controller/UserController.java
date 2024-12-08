package com.example.hardware_store.controller;

import com.example.hardware_store.entity.Cart;
import com.example.hardware_store.entity.Order;
import com.example.hardware_store.entity.Product;
import com.example.hardware_store.repository.CartRepository;
import com.example.hardware_store.repository.OrderRepository;
import com.example.hardware_store.repository.ProductRepository;
import com.example.hardware_store.security.UserDetailss;
import com.example.hardware_store.service.entity.CartService;
import com.example.hardware_store.service.entity.implementation.CartServiceImpl;
import com.example.hardware_store.service.entity.implementation.CharacteristicServiceImpl;
import com.example.hardware_store.service.entity.implementation.OrderServiceImpl;
import com.example.hardware_store.service.entity.implementation.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private final OrderRepository orderRepository;

    private final CartRepository cartRepository;
    private final CartServiceImpl cartService;
    private final ProductServiceImpl productService;
    private final ProductRepository productRepository;
    private final OrderServiceImpl orderService;
    private final CharacteristicServiceImpl characteristicService;

    @Autowired
    public UserController(OrderRepository orderRepository, CartRepository cartRepository,
                          ProductServiceImpl productService, ProductRepository productRepository,
                          OrderServiceImpl orderService, CharacteristicServiceImpl characteristicService,
                          CartServiceImpl cartService) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.productRepository = productRepository;
        this.orderService = orderService;
        this.characteristicService = characteristicService;
        this.cartService = cartService;
    }

    @GetMapping("/index")
    public String index(Model model){
        // Получае объект аутентификации - > c помощью SecurityContextHolder обращаемся к контексту и на нем вызываем метод аутентификации. По сути из потока для текущего пользователя мы получаем объект, который был положен в сессию после аутентификации пользователя
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Преобразовываем объект аутентификации в специальный объект класса по работе с пользователями
        UserDetailss userDetails = (UserDetailss) authentication.getPrincipal();
        System.out.println("person == anonymousUser ? " + (authentication.getPrincipal() == "anonymousUser"));

        String role = userDetails.getUser().getRole();

        if(role.equals("ROLE_ADMIN")){
            return "redirect:/admin";
        }
        // addseller
        if(role.equals("ROLE_MANAGER")){
            return "redirect:/manager";
        }
        // addseller end
        model.addAttribute("products", productService.findAllProducts());
        return "user/index";
    }

    // Добавить товар в корзину
    @GetMapping("/cart/add/{id}")
    public String addProductInCart(@PathVariable("id") Long id, Model model){
        Optional<Product> product = productService.findProductById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailss userDetails = (UserDetailss) authentication.getPrincipal();
        Long id_person = userDetails.getUser().getId();
//        Cart cart = new Cart(id_person, product.get().getId());
//        cartRepository.save(cart);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String cart(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailss userDetails = (UserDetailss) authentication.getPrincipal();
        Long id_person = userDetails.getUser().getId();
        List<Cart> cartList = cartRepository.findByUserId(id_person);
        List<Product> productList = new ArrayList<>();
        for (Cart cart: cartList) {
            productList.add(productService.findProductById(cart.getProduct().getId()).get());
        }

        float price = 0;
        for (Product product: productList) {
            price += product.getPrice();
        }

        model.addAttribute("price", price);
//        model.addAttribute("text_to_order", text_to_order);
        model.addAttribute("cart_product", productList);
        return "user/cart";
    }

//    @GetMapping("/user/product/info/{id}")
//    public String infoProduct(@PathVariable("id") Long id, Model model){
//        model.addAttribute("product", productService.findProductById(id));
//        return "user/infoProduct";
//    }

    @GetMapping("/cart/delete/{id}")
    public String deleteProductFromCart(Model model, @PathVariable("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailss userDetails = (UserDetailss) authentication.getPrincipal();
        Long id_person = userDetails.getUser().getId();
        cartRepository.deleteCartById(id, id_person);
        return "redirect:/cart";
    }

    @GetMapping("/orders")
    public String ordersUser(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailss userDetails = (UserDetailss) authentication.getPrincipal();
        List<Order> orderList = orderRepository.findByUser(userDetails.getUser());
        model.addAttribute("orders", orderList);
        return "/user/orders";
    }


    @PostMapping("/orders/cancel/{id}")
    public String editOrderCancel(@ModelAttribute("editOrder") Order order, @PathVariable("id") Long id){
        Optional<Order> order_status = orderService.findOrderById(id);
        orderService.updateOrderStatus(order, order_status.get().getStatus());

        return "redirect:/orders";
    }

    //CHARACT

    @GetMapping("/user/product/info/{id}")
    public String showProductInfo(@PathVariable("id") Long id, Model model) {
        Product product = productService.findProductById(id).get();
        model.addAttribute("product", product);
        model.addAttribute("characteristics",
                characteristicService.findAllCharacteristicByProductId(id));
        return "user/productInfo";
    }

    //CART

    @PostMapping("/cart/delete/{id}")
    public String deleteFromCart(@PathVariable("id") Long productId, Principal principal) {
        // Получаем ID пользователя (например, через Principal)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailss userDetails = (UserDetailss) authentication.getPrincipal();
        Long id = userDetails.getUser().getId();
        cartService.removeFromCartByUserId(productId, id);

        return "redirect:/cart"; // Перенаправление обратно в корзину
    }

    @PostMapping("cart/add/{id}")
    public String addProductToCart(@PathVariable("id") Long productId, Principal principal) {
        // Получаем ID пользователя (можно использовать метод, который вы уже реализовали)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailss userDetails = (UserDetailss) authentication.getPrincipal();
        Long id = userDetails.getUser().getId();
        cartService.addProductToCart(productId, 1, id);

        return "redirect:/cart"; // Перенаправление обратно в корзину
    }
}