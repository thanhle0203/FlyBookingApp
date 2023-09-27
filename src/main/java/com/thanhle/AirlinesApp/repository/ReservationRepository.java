package com.thanhle.AirlinesApp.repository;

import com.thanhle.AirlinesApp.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	//Reservation findByFlightId(Long flightId);
	@Query("SELECT r FROM Reservation r WHERE r.flight.id = :flightId")
    Reservation findByFlightId(@Param("flightId") Long flightId);
    
}
