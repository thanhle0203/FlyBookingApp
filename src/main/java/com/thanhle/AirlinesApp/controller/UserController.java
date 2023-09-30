package com.thanhle.AirlinesApp.controller;

import com.thanhle.AirlinesApp.domain.Passenger;
import com.thanhle.AirlinesApp.domain.Role;
import com.thanhle.AirlinesApp.domain.User;
import com.thanhle.AirlinesApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
   
    

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ((HeadersBuilder<?>) userService.findByUsername(username)).build();
    }
    
   

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        if (!userService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        user.setUserId(id);
        return ResponseEntity.ok(userService.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!userService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
  
    
    
}
