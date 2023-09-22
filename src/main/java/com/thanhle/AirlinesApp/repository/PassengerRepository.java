package com.thanhle.AirlinesApp.repository;

import com.thanhle.AirlinesApp.domain.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    // Additional custom query methods can be defined here if needed
}

