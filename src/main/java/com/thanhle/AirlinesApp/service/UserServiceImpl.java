package com.thanhle.AirlinesApp.service;

import com.thanhle.AirlinesApp.domain.User;
import com.thanhle.AirlinesApp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan
public class UserServiceImpl implements UserService {
	
	//@Autowired UserRepository userRepository;
	//@Autowired BCryptPasswordEncoder passwordEncoder;
	
	private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    

    @Override
    public User save(User user) {
    	String encryptedPassword = passwordEncoder.encode(user.getPassword());
    	user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public boolean checkPassword(User user, String password) {
		return passwordEncoder.matches(password, user.getPassword());
	}
	






}
