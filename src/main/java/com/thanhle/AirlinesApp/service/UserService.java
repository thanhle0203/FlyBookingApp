package com.thanhle.AirlinesApp.service;

import com.thanhle.AirlinesApp.domain.Passenger;
import com.thanhle.AirlinesApp.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    User findByUsername(String username);
    User save(User user);
    void deleteById(Long id);
	User findByEmail(String email);


}
