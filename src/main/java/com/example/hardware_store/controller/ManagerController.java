package com.example.hardware_store.controller;

import com.example.hardware_store.security.UserDetailss;
import com.example.hardware_store.service.entity.implementation.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    private final OrderServiceImpl orderService;

    @Autowired
    public ManagerController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public String manager(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetailss userDetails = (UserDetailss) authentication.getPrincipal();

        String role = userDetails.getUser().getRole();
        if (role.equals("ROLE_ADMIN")) {
            return "redirect:/admin";
        }
        if (role.equals("ROLE_USER")) {
            return "redirect:/index";
        }
        model.addAttribute("orders", orderService.findAllOrders());
        return "manager/manager";
    }

    @PostMapping("/order/update/{id}")
    public String updateOrderStatus(@PathVariable("id") Long id, @RequestParam("status") String status) {
        orderService.updateOrderStatus(orderService.findOrderById(id).get(), status);
        return "redirect:/manager";
    }
}
