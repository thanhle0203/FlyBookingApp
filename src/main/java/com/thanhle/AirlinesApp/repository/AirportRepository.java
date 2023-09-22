package com.thanhle.AirlinesApp.repository;

import com.thanhle.AirlinesApp.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    // Additional custom query methods can be defined here if needed
}
