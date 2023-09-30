package com.thanhle.AirlinesApp.repository;

import com.thanhle.AirlinesApp.domain.Passenger;
import com.thanhle.AirlinesApp.domain.User;

import jakarta.transaction.Transactional;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);
 
    

}
