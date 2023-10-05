package com.thanhle.AirlinesApp.controller;

import com.thanhle.AirlinesApp.domain.Passenger;
import com.thanhle.AirlinesApp.domain.Role;
import com.thanhle.AirlinesApp.domain.User;
import com.thanhle.AirlinesApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
@Controller
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody User user) {
        if (userService.findByEmail(user.getEmail()) != null) {
            // User with this email already exists
            return ResponseEntity.badRequest().body(null);
        }
        // Set default role or roles for the user, if any (assuming you have a Role class)
        Role defaultRole = new Role(); 
        defaultRole.setRoleName("USER"); // Example: Setting the default role as "USER"
        user.setRoles((Set<Role>) Arrays.asList(defaultRole));

        User savedUser = userService.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/signin")
    public ResponseEntity<User> signIn(@RequestBody User user) {
        User existingUser = userService.findByEmail(user.getEmail());
        if (existingUser == null) {
            // User with this email does not exist
            return ResponseEntity.notFound().build();
        }
        if (!userService.checkPassword(existingUser, user.getPassword())) {
            // Invalid password
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(existingUser);
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
