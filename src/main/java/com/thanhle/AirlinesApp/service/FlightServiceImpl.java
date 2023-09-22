package com.thanhle.AirlinesApp.service;

import com.thanhle.AirlinesApp.domain.Flight;
import com.thanhle.AirlinesApp.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

	@Override
	public List<Flight> searchFlights(String departureCity, String arrivalCity, LocalDate departureDate,
			Integer numOfPassengers) {
		return getAllFlights().stream()
				.filter(flight -> flight.getDepartureCity().equalsIgnoreCase(departureCity))
				.filter(flight -> flight.getArrivalCity().equalsIgnoreCase(arrivalCity))
				.filter(flight -> flight.getDepartureDate().equals(departureDate))
				.filter(flight -> (flight.getCapacity() - flight.getBooked()) >= numOfPassengers)
				.collect(Collectors.toList());
				
	}
	
	


    //... other CRUD methods or custom methods
}
