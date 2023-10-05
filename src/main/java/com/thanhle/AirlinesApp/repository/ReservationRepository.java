package com.thanhle.AirlinesApp.repository;

import com.thanhle.AirlinesApp.domain.Reservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	//Reservation findByFlightId(Long flightId);
	@Query("SELECT r FROM Reservation r WHERE r.flight.id = :flightId")
    Reservation findByFlightId(@Param("flightId") Long flightId);
    
	@Query("SELECT r FROM Reservation r JOIN r.passengers p WHERE r.user.email = :userEmail AND p.email = :passengerEmail")
    List<Reservation> findByUserEmailAndPassengerEmail(@Param("userEmail") String userEmail, @Param("passengerEmail") String passengerEmail);

}
