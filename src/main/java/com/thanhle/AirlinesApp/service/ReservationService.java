package com.thanhle.AirlinesApp.service;

import com.thanhle.AirlinesApp.domain.Flight;
import com.thanhle.AirlinesApp.domain.Passenger;
import com.thanhle.AirlinesApp.domain.Reservation;
import java.util.List;

public interface ReservationService {
    List<Reservation> findAll();
    Reservation findById(Long id);
    //Reservation save(Reservation reservation);
    void deleteById(Long id);
	//Reservation createReservation(Flight flight, List<Passenger> passengers);
	//Reservation updateReservation(Reservation reservation);
	Reservation createReservation(Long flightId, int numOfPassengers);
	Reservation updateReservation(Reservation reservation);
	Reservation getReservationByFlightId(Long flightId);
}
