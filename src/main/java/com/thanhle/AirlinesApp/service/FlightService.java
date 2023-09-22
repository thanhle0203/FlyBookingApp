package com.thanhle.AirlinesApp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.thanhle.AirlinesApp.domain.Flight;

public interface FlightService {
	public List<Flight> getAllFlights();
	public Flight getFlightById(Long id);
	public Flight saveFlight(Flight flight);
	List<Flight> searchFlights(String departureCity, String arrivalCity, LocalDate departureDate,
			Integer numOfPassengers);
}
