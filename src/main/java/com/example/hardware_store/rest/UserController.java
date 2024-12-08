//package com.example.hardware_store.rest;
//
//import com.example.hardware_store.entity.User;
//import com.example.hardware_store.service.entity.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.security.Principal;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/user")
//public class UserController {
//
//    private final UserService userService;
//
//    @GetMapping("/info")
//    public User getUser(Principal principal) {
//        return userService.findUserByUsername(principal.getName())
//                .orElseThrow(() -> new UsernameNotFoundException("User " + principal.getName() + " not found!"));
//    }
//
//    @GetMapping
//    public ResponseEntity<User> getUserById(@RequestParam("id") Long id) {
//        User user = userService.findUserById(id)
//                .orElseThrow(() -> new UsernameNotFoundException("User " + id + " not found!"));
//        if (user != null) {
//            return ResponseEntity.ok(user);
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @GetMapping("/roles")
//    public ResponseEntity<List<String>> getUserRoles(Authentication authentication) {
//        return ResponseEntity.ok(authentication
//                .getAuthorities()
//                .stream()
//                .map(GrantedAuthority::getAuthority)
//                .toList());
//    }
//
//    @GetMapping("/users")
//    public ResponseEntity<List<User>> getAllUsers() {
//        return ResponseEntity.ok(userService.findAllUsers());
//    }
//
//    @PostMapping
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        userService.saveUser(user);
//        return ResponseEntity.ok(user);
//    }
//
//    @PutMapping
//    public ResponseEntity<User> updateUser(@RequestParam("id") Long id, @RequestBody User user) {
//        userService.updateUser(id, user);
//        return ResponseEntity.ok(user);
//    }
//
//    @DeleteMapping
//    public ResponseEntity<Void> deleteUser(@RequestParam("id") Long id) {
//        userService.deleteUserById(id);
//        return ResponseEntity.noContent().build();
//    }
//}