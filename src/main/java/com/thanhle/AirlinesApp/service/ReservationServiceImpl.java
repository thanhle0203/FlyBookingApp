package com.thanhle.AirlinesApp.service;

import com.thanhle.AirlinesApp.domain.Flight;
import com.thanhle.AirlinesApp.domain.Passenger;
import com.thanhle.AirlinesApp.domain.Reservation;
import com.thanhle.AirlinesApp.exception.ReservationNotFoundException;
import com.thanhle.AirlinesApp.repository.FlightRepository;
import com.thanhle.AirlinesApp.repository.PassengerRepository;
import com.thanhle.AirlinesApp.repository.ReservationRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private FlightRepository flightRepository;
    
    @Autowired
    private PassengerRepository passengerRepository;
    

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findById(Long id) {
        Optional<Reservation> result = reservationRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("Reservation not found for id - " + id);
        }
    }

    /*
    @Override
    @Transactional 
    public Reservation createReservation(Flight flight, List<Passenger> passengers) {
    	// ensure the flight is persisted
    	Flight persistedFlight = flightRepository.save(flight);
    	
    	// ensure all passengers are persisted
    	List<Passenger> persistedPassengers = passengerRepository.saveAll(passengers);
    	
    	// Create a new Reservation entity
    	Reservation reservation = new Reservation();
    	reservation.setFlight(persistedFlight);
    	reservation.setPassengers(persistedPassengers);
    	
    	return reservationRepository.save(reservation);
    	
    }
    */
    
    /*
    
    @Override
    @Transactional 
    public Reservation createReservation(Long flightId, int numOfPassengers) {
        // Look up the flight using the flightId
        Optional<Flight> optionalFlight = flightRepository.findById(flightId);
        if (!optionalFlight.isPresent()) {
            throw new RuntimeException("Flight not found for id - " + flightId);  // Consider using a custom exception
        }
        Flight persistedFlight = optionalFlight.get();
        
        // Create Passenger objects (assuming a simple Passenger object for this example)
        List<Passenger> passengers = new ArrayList<>();
        for (int i = 0; i < numOfPassengers; i++) {
            Passenger passenger = new Passenger();
            // Set any necessary passenger information
            passenger.setFname(null);
            passengers.add(passenger);
        }
        
        // Ensure all passengers are persisted
        List<Passenger> persistedPassengers = passengerRepository.saveAll(passengers);

        // Create a new Reservation entity
        Reservation reservation = new Reservation();
        reservation.setFlight(persistedFlight);
        reservation.setPassengers(persistedPassengers);
        
        return reservationRepository.save(reservation);
    }
    */
    
    @Override
    @Transactional 
    public Reservation createReservation(Long flightId, int numOfPassengers, List<Passenger> passengerForm) {
        // Look up the flight using the flightId
        Optional<Flight> optionalFlight = flightRepository.findById(flightId);
        if (!optionalFlight.isPresent()) {
            throw new RuntimeException("Flight not found for id - " + flightId);  // Consider using a custom exception
        }
        Flight persistedFlight = optionalFlight.get();
        
        // Create Passenger objects (assuming a simple Passenger object for this example)
        List<Passenger> passengers = new ArrayList<>();
        for (Passenger info : passengerForm) {
            Passenger passenger = new Passenger();
            passenger.setFname(info.getFname());
            passenger.setLname(info.getLname());
            passenger.setEmail(info.getEmail());
            passenger.setPhone(info.getPhone());
            passenger.setGender(info.getGender());
            passenger.setDateOfBirth(info.getDateOfBirth());
            passenger.setIdType(info.getIdType());
            passengers.add(passenger);
        }
        
        // Ensure all passengers are persisted
        List<Passenger> persistedPassengers = passengerRepository.saveAll(passengers);
        
        // Create a new Reservation entity
        Reservation reservation = new Reservation();
        reservation.setFlight(persistedFlight);
        reservation.setPassengers(persistedPassengers);
        
        return reservationRepository.save(reservation);
    }

    
    
    @Override
    public Reservation getReservationByFlightId(Long flightId) {
        return reservationRepository.findByFlightId(flightId);
    }

    

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }

	@Override
	@Transactional
	public Reservation updateReservation(Reservation reservation) {
		Optional<Reservation> existingReservation = reservationRepository.findById(reservation.getReservationId());
		if (existingReservation.isPresent()) {
			Reservation updatedReservation = existingReservation.get();
			
			// Assume setters are present to update fields
			updatedReservation.setFlight(reservation.getFlight());
			updatedReservation.setPassengers(reservation.getPassengers());
			
			return reservationRepository.save(updatedReservation);
		}
		else {
			throw new ReservationNotFoundException("Reservation not found for id - " + reservation.getReservationId());
		}
	}


}
