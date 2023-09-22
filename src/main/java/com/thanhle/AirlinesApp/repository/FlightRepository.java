package com.thanhle.AirlinesApp.repository;

import com.thanhle.AirlinesApp.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    // Additional custom query methods can be defined here if needed
}
