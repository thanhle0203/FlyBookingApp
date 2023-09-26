package com.thanhle.AirlinesApp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thanhle.AirlinesApp.domain.Flight;
import com.thanhle.AirlinesApp.service.FlightService;

@Controller
public class ViewController {

	@Autowired
	private FlightService flightService;
	
    @GetMapping("/")
    public String homepage() {
        return "homepage";
    }

    @GetMapping("/searchFlights")
    public String searchFlights(
        @RequestParam String departureCity,
        @RequestParam String arrivalCity,
        @RequestParam LocalDate departureDate,
        @RequestParam Integer numOfPassengers,
        Model model
    ) {
        List<Flight> flights = flightService.searchFlights(departureCity, arrivalCity, departureDate, numOfPassengers);
        model.addAttribute("flights", flights);
        model.addAttribute("numOfPassengers", numOfPassengers); 
        
        return "listFlights";  // Ensure you're returning the name of the correct view
    }
    
    @RequestMapping("/showFlights")
    public String showFlights(Model model) {
    	List<Flight> flights = flightService.getAllFlights();
    	model.addAttribute("flights", flights);
    	return "flightFlights";
    }
    
  
    
    @GetMapping("/bookFlight/{flightId}/{numOfPassengers}")
    public String bookFlight(
        @PathVariable Long flightId,
        @PathVariable Integer numOfPassengers,
        Model model
    ) {
    	//List<Flight> flights = flightService.getAllFlights();
    	//model.addAttribute("flights", flights);
    	Flight flight = flightService.getFlightById(flightId);
    	model.addAttribute("flight", flight);
    	
        model.addAttribute("flightId", flightId);
        model.addAttribute("numOfPassengers", numOfPassengers);
        
        return "booking";  // This view should be your booking form
    }
    
    
}
