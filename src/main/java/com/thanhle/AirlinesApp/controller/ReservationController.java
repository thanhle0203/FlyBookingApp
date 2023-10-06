package com.thanhle.AirlinesApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thanhle.AirlinesApp.domain.Flight;
import com.thanhle.AirlinesApp.domain.Passenger;
import com.thanhle.AirlinesApp.domain.PassengerList;
import com.thanhle.AirlinesApp.domain.Reservation;
import com.thanhle.AirlinesApp.domain.User;
import com.thanhle.AirlinesApp.service.ReservationService;
import com.thanhle.AirlinesApp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    
    @Autowired
    private UserService userService;

    // Get all reservations
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.findAll();
    }

    // Get reservation by id
    @GetMapping("/{reservationId}")
    public ResponseEntity<Reservation> getReservation(@PathVariable Long reservationId) {
        Reservation reservation = reservationService.findById(reservationId);
        if (reservation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }
    

    @GetMapping("/byUserEmail")
    public ResponseEntity<List<Reservation>> getReservationsByUserEmail(Principal principal) {
        if (principal == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String userEmail = principal.getName();
        List<Reservation> reservations = reservationService.findByUserEmail(userEmail);
        if (reservations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }


 // Add a reservation
    @PostMapping
    public ResponseEntity<Reservation> addReservation(
        @RequestParam Long flightId,
        @RequestParam int numOfPassengers,
        @RequestBody PassengerList passengerList,
        Principal principal

    ) {
        try {
        	User authenticatedUser = userService.findByEmail(principal.getName());
        	// Log the incoming data
            System.out.println("flightId: " + flightId);
            System.out.println("numOfPassengers: " + numOfPassengers);
            System.out.println("passengerForm: " + passengerList);
            System.out.println("User email: " + authenticatedUser);
                
            List<Passenger> passengers = passengerList.getPassengers();
 
            
            Reservation savedReservation = reservationService.createReservation(flightId, numOfPassengers, passengers, authenticatedUser);
            return new ResponseEntity<>(savedReservation, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);  // handle exception appropriately
        }
    }


    // Update a reservation
    @PutMapping
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation) {
        if (reservationService.findById(reservation.getReservationId()) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
     // Extract flight and passengers from the reservation object
    	//Flight flight = reservation.getFlight();
    	//List<Passenger> passengers = reservation.getPassengers();
    	
        reservationService.updateReservation(reservation);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    // Delete a reservation
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId) {
        if (reservationService.findById(reservationId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        reservationService.deleteById(reservationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
