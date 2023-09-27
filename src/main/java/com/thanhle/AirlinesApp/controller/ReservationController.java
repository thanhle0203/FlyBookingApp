package com.thanhle.AirlinesApp.controller;

import com.thanhle.AirlinesApp.domain.Flight;
import com.thanhle.AirlinesApp.domain.Passenger;
import com.thanhle.AirlinesApp.domain.Reservation;
import com.thanhle.AirlinesApp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

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

 // Add a reservation
    @PostMapping
    public ResponseEntity<Reservation> addReservation(
        @RequestParam Long flightId,
        @RequestParam int numOfPassengers
    ) {
        try {
            Reservation savedReservation = reservationService.createReservation(flightId, numOfPassengers);
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
