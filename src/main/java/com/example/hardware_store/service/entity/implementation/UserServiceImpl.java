package com.example.hardware_store.service.entity.implementation;

import com.example.hardware_store.entity.Role;
import com.example.hardware_store.entity.User;
import com.example.hardware_store.repository.RoleRepository;
import com.example.hardware_store.repository.UserRepository;
import com.example.hardware_store.service.entity.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        if (user.getId() == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(checkAndSetAdminRole(user));
            userRepository.saveAndFlush(user);
        } else {
            updateUser(user.getId(), user);
        }
    }

    @Override
    public void updateUser(Long id, User user) {
        User newUser = userRepository.findById(id).orElse(null);

        if (newUser != null && id.equals(newUser.getId())) {
            newUser.setUsername(user.getUsername());
            newUser.setRoles(checkAndSetAdminRole(user));

            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            userRepository.saveAndFlush(newUser);
        }
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    private List<Role> checkAndSetAdminRole(User user) {

        List<String> roleNames = user.getRoles().stream()
                .map(Role::getName)
                .toList();

        List<Role> roles = roleRepository.findRolesByName(roleNames).orElse(new ArrayList<>());

        boolean hasAdminRole = roles.stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"));
        if (hasAdminRole) {
            Role userRole = roleRepository.findRoleByName("ROLE_USER").orElse(null);
            if (userRole != null && !roles.contains(userRole)) {
                roles.add(userRole);
            }
        }

        return roles;
    }
}