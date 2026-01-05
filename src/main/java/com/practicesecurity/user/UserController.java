package com.practicesecurity.user;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    final private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }


    @PostMapping("/user")
    @GetMapping("/user")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterRequest req) {

        userService.register(req);
        return ResponseEntity.ok("User registered successfully");

    }


    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUser(@RequestParam String userName) {
        userService.delete(userName);
        return ResponseEntity.ok("User deleted successfully");
    }



}
