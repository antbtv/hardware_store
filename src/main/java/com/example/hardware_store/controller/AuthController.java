package com.example.hardware_store.controller;

import com.example.hardware_store.entity.User;
import com.example.hardware_store.security.UserDetailss;
import com.example.hardware_store.service.entity.implementation.UserServiceImpl;
import com.example.hardware_store.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserValidator userValidator;

    private final UserServiceImpl userService;

    @Autowired
    public AuthController(UserValidator userValidator, UserServiceImpl userService) {
        this.userValidator = userValidator;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(){
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(object instanceof UserDetailss){
            object = ((UserDetailss) object).getUser().getUsername();
        } else{
        }
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String resultRegistration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        userValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors()){
            return "registration";
        }
        userService.register(user);
        return "redirect:/index";
    }

//    @GetMapping("/password/changepersonal")
//    public String changePasswordPersonal(Model model){
//        model.addAttribute("person", new User());
//        model.addAttribute("login", SecurityContextHolder.getContext().getAuthentication().getName());
//        return "passwordpersonal";
//    }
//
//    @PostMapping("/password/changepersonal")
//    public String changePasswordPersonal(@ModelAttribute("person") @Valid User user, BindingResult bindingResult, Model model){
//        userValidator.findUser(user, bindingResult);
//
//        Optional<User> user_db = userService.findUserByUsername(user.getUsername());
//        Long id = user_db.get().getId();
//        String password = user.getPassword();
//        userService.(id, password);
//
//        return "redirect:/index";
//    }

}