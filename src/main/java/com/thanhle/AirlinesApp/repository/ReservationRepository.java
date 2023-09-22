package com.thanhle.AirlinesApp.repository;

import com.thanhle.AirlinesApp.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
}
