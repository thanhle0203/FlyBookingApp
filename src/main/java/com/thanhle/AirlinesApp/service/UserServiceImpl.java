package com.thanhle.AirlinesApp.service;

import com.thanhle.AirlinesApp.domain.Passenger;
import com.thanhle.AirlinesApp.domain.Role;
import com.thanhle.AirlinesApp.domain.User;
import com.thanhle.AirlinesApp.repository.PassengerRepository;
import com.thanhle.AirlinesApp.repository.RoleRepository;
import com.thanhle.AirlinesApp.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired 
	private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    public PassengerRepository passengerRepository;

    @Autowired
    public RoleRepository roleRepository;

    @Autowired
    public UserRepository userRepository;
    
    @Autowired
    private EntityManager entityManager;

  

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
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
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
