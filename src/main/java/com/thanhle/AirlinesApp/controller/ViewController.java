package com.thanhle.AirlinesApp.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.thanhle.AirlinesApp.domain.Flight;
import com.thanhle.AirlinesApp.domain.Passenger;
import com.thanhle.AirlinesApp.domain.PassengerList;
import com.thanhle.AirlinesApp.domain.Reservation;
import com.thanhle.AirlinesApp.domain.Role;
import com.thanhle.AirlinesApp.domain.User;
import com.thanhle.AirlinesApp.dto.SignupForm;
import com.thanhle.AirlinesApp.service.FlightService;
import com.thanhle.AirlinesApp.service.ReservationService;
import com.thanhle.AirlinesApp.service.UserService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class ViewController {

	@Autowired
	private FlightService flightService;
	
	@Autowired
	private ReservationService reservationService;
	

	/*
	@PostMapping("/signup")
	public String signupSubmit(@ModelAttribute SignupForm signupForm, Model model) {
	    // Validate the form data, if necessary
	    // ...

	    // Create a new User and Role objects from the form data
	    User newUser = new User();
	    newUser.setUsername(signupForm.getUsername());
	    newUser.setPassword(signupForm.getPassword());  // You'll want to hash the password before saving it
	    newUser.setEmail(signupForm.getEmail());

	    Role newRole = new Role();
	    newRole.setRoleName(signupForm.getRole());
	    newUser.getRoles().add(newRole);

	    // Save the new User and Role to the database
	    userService.save(newUser);  // Assumes you have a saveUser method in your UserService

	    // Optionally, add a message to the model to be displayed in the view
	    model.addAttribute("message", "Signup successful");

	    // Return the name of the view to be displayed
	    return "signupForm";  // Assumes you have a signupResult.jsp view
	}
	*/

	
  
	
    @GetMapping("/")
    public String homepage() {
        return "homepage";
    }
    
    @GetMapping("/homepage")
    public String homepages() {
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
    
    
    @PostMapping("/completeBooking")
    public String completeBooking(@PathVariable Long flightId, @PathVariable("numOfPassengers") int numOfPassengers, @ModelAttribute("passengerForm") PassengerList passengerForm, Model model) {
        try {
            // Create a reservation using the provided flightId and numOfPassengers
        	Reservation savedReservation = reservationService.createReservation(flightId, numOfPassengers, passengerForm.getPassengers());

            // Add reservation to model
            model.addAttribute("reservation", savedReservation);

            // Redirect to booking details page
            return "bookingDetails";

        } catch (RuntimeException e) {
            // You might want to log the exception message to your server logs for debugging
            // Handle error, e.g., return an error view or message
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
    
    @PostMapping("/completeBooking/{flightId}/{numOfPassengers}")
    public String completeBookings(@PathVariable Long flightId, @PathVariable("numOfPassengers") int numOfPassengers, @ModelAttribute("passengerForm") PassengerList passengerForm, Model model) {
        try {
            // Create a reservation using the provided flightId and numOfPassengers
            Reservation savedReservation = reservationService.createReservation(flightId, numOfPassengers, passengerForm.getPassengers());

            // Add reservation to model
            model.addAttribute("reservation", savedReservation);

            // Redirect to booking details page
            return "bookingDetails";

        } catch (RuntimeException e) {
            // You might want to log the exception message to your server logs for debugging
            // Handle error, e.g., return an error view or message
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/bookingDetails/{flightId}")
    public String bookingDetails(@PathVariable Long flightId, Model model) {
        try {
            flightId = Long.valueOf(flightId);
        } catch (NumberFormatException e) {
            System.err.println("Invalid flightId: " + flightId);
            // handle error
            model.addAttribute("errorMessage", "Invalid flightId");
            return "error";  // Assuming you have an error.jsp page
        }

        Reservation reservation = getReservationDetails(flightId);  // Assume getReservationDetails is a method to fetch reservation details
        model.addAttribute("reservation", reservation);

        return "bookingDetails";  // bookingDetails.jsp
    }

	private Reservation getReservationDetails(Long flightId) {
		// TODO Auto-generated method stub
		return reservationService.getReservationByFlightId(flightId);
	}

    //private Reservation getReservationDetails(Long flightId) {
        // Implement this method to fetch reservation details based on flightId
        //return reservationService.getReservationByFlightId(flightId);
    //}
	
	@WebServlet("/bookFlight/*")
	public class BookFlightServlet extends HttpServlet {
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String pathInfo = request.getPathInfo();  // /{flightId}/{numOfPassengers}
	        String[] pathParts = pathInfo.split("/");
	        String flightId = pathParts[1];
	        String numOfPassengers = pathParts[2];

	        request.setAttribute("flightId", flightId);
	        request.setAttribute("numOfPassengers", numOfPassengers);

	        RequestDispatcher dispatcher = request.getRequestDispatcher("/booking.jsp");
	        dispatcher.forward(request, response);
	    }
	}



    
}
