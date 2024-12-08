package com.example.hardware_store.controller;

import com.example.hardware_store.security.UserDetailss;
import com.example.hardware_store.service.entity.implementation.OrderServiceImpl;
import com.example.hardware_store.service.entity.implementation.ProductServiceImpl;
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

        // Получае объект аутентификации - > c помощью SecurityContextHolder обращаемся к контексту и на нем вызываем метод аутентификации. По сути из потока для текущего пользователя мы получаем объект, который был положен в сессию после аутентификации пользователя
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Преобразовываем объект аутентификации в специальный объект класса по работе с пользователями
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
        orderService.updateOrderStatus(orderService.findOrderById(id).get(), status); // Обновляем статус заказа
        return "redirect:/manager"; // Перенаправление обратно к списку заказов
    }
}
