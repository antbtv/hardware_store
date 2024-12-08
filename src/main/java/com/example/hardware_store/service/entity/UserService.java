package com.example.hardware_store.service.entity;

import com.example.hardware_store.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findUserById(Long id);

    Optional<User> findUserByUsername(String username);

    void deleteUserById(Long id);

    List<User> findAllUsers();
}