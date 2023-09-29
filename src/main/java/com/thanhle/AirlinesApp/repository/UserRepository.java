package com.thanhle.AirlinesApp.repository;

import com.thanhle.AirlinesApp.domain.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    
    @Query("SELECT u FROM User u WHERE u.passenger.email = :email")
    User findUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.passenger.phone = :phone")
    User findUserByPhone(@Param("phone") String phone);

}
