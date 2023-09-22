package com.thanhle.AirlinesApp.controller;

import com.thanhle.AirlinesApp.domain.Flight;
import com.thanhle.AirlinesApp.service.FlightService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
//@Controller
@RequestMapping("api/flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }

    @PostMapping
    public Flight createFlight(@RequestBody Flight flight) {
        return flightService.saveFlight(flight);
    }

    @GetMapping("/search")
    public List<Flight> searchFlights(
    		@RequestParam String departureCity,
    		@RequestParam String arrivalCity,
    		@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate departureDate,
    		@RequestParam (defaultValue = "1") Integer numOfPassengers
    ) {
    	return flightService.searchFlights(departureCity, arrivalCity, departureDate, numOfPassengers);
    }
    
    
}
