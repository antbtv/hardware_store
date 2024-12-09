package com.example.hardware_store.controller;

import com.example.hardware_store.entity.*;
import com.example.hardware_store.repository.CharacteristicRepository;
import com.example.hardware_store.repository.OrderRepository;
import com.example.hardware_store.security.UserDetailss;
import com.example.hardware_store.service.entity.CharacteristicService;
import com.example.hardware_store.service.entity.implementation.*;
import com.example.hardware_store.util.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Value("${upload.path}")
    private String uploadPath;

    private final ProductValidator productValidator;

    private final CharacteristicRepository characteristicRepository;
    private final OrderRepository orderRepository;

    private final ProductServiceImpl productService;
    private final OrderServiceImpl orderService;
    private final UserServiceImpl userService;
    private final CharacteristicService characteristicService;
    private final PhotoServiceImpl photoService;

    @Autowired
    public AdminController(ProductValidator productValidator, ProductServiceImpl productService,
                           OrderRepository orderRepository, OrderServiceImpl orderService, UserServiceImpl userService,
                           CharacteristicRepository characteristicRepository, CharacteristicServiceImpl characteristicService,
                           PhotoServiceImpl photoService) {
        this.productValidator = productValidator;
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.userService = userService;
        this.characteristicRepository = characteristicRepository;
        this.characteristicService = characteristicService;
        this.photoService = photoService;
    }

    @GetMapping()
    public String admin(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailss userDetails = (UserDetailss) authentication.getPrincipal();

        String role = userDetails.getUser().getRole();

        if(role.equals("ROLE_USER")){
            return "redirect:/index";
        }
        if(role.equals("ROLE_MANAGER")){
            return "redirect:/manager";
        }
        model.addAttribute("products", productService.findAllProducts());
        model.addAttribute("orders", orderService.findAllOrders());
        return "admin/admin";
    }


// PRODUCT


    @GetMapping("/product/add")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("characteristics", characteristicRepository.findAll());
        return "product/addProduct";
    }

    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,
                             Model model) throws IOException {

        model.addAttribute("characteristics", characteristicRepository.findAll());
        model.addAttribute("product", product);
        productValidator.validate(product, bindingResult);
        if(bindingResult.hasErrors()){
            return "product/addProduct";
        }
        productService.saveProduct(product);
        return "redirect:/admin";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        productService.deleteProductById(id);
        return "redirect:/admin";
    }

    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model){
        model.addAttribute("editProduct", productService.findProductById(id).get());
        return "product/editProduct";
    }

    @PostMapping("/product/edit/{id}")
    public String editProduct(@ModelAttribute("editProduct") Product product, @PathVariable("id") Long id){
        productService.updateProduct(id, product);

        return "redirect:/admin";
    }


// ORDER


    @PostMapping("/orders/status/{id}")
    public String editOrderStatus(@ModelAttribute("editOrder") Order order, @PathVariable("id") Long id){
        Optional<Order> order_status = orderService.findOrderById(id);
        orderService.updateOrderStatus(order_status.get(), order_status.get().getStatus());
        return "redirect:/admin/order/info/{id}";
    }

    @GetMapping("/admin/order/edit/{id}")
    public String editOrder(@PathVariable("id") Long id, Model model) {
        model.addAttribute("order", orderService.findOrderById(id));
        return "order/editOrder";
    }


    // PERSON


    @GetMapping("/user")
    public String user(Model model){;
        model.addAttribute("users", userService.findAllUsers());
        return "admin/user";
    }

    @GetMapping("/user/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/addUser";
    }

    @PostMapping("/user/add")
    public String addUser(@ModelAttribute("user") User user, String role) {
        userService.register(user);
        userService.updateUserRole(role, user);
        return "redirect:/admin/user";
    }

    @GetMapping("/user/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id).get();
        model.addAttribute("editUser", user);
        return "admin/editUser";
    }

    @PostMapping("/user/edit/{id}")
    public String updateUser(@ModelAttribute("editUser") User user, String role) {
        String pass = userService.findUserByUsername(user.getUsername()).get().getPassword();
        user.setPassword(pass);
        userService.updateUserRole(role, user);
        return "redirect:/admin/user";
    }

    @GetMapping("/user/delete/{id}")
    public String deletePerson(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return "redirect:/admin/user";
    }
}