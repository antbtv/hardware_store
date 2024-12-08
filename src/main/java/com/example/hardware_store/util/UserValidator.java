package com.example.hardware_store.util;


import com.example.hardware_store.entity.User;
import com.example.hardware_store.service.entity.UserService;
import com.example.hardware_store.service.entity.implementation.UserServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private final UserServiceImpl userService;

    public UserValidator(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if(userService.findUserByUsername(user.getUsername()).isPresent()){
            errors.rejectValue("login", "", "Логин занят");
        }
    }

    public void findUser(Object target, Errors errors) {
        User user = (User) target;
        if(userService.findUserByUsername(user.getUsername()).isEmpty()){
            errors.rejectValue("login", "", "Пользователь c таким логином не найден.");
        }
    }
}