package com.thanhle.AirlinesApp.controller;

import com.thanhle.AirlinesApp.domain.Passenger;
import com.thanhle.AirlinesApp.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable Long id) {
        return passengerService.getPassengerById(id)
                .map(passenger -> ResponseEntity.ok(passenger))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Passenger createPassenger(@RequestBody Passenger passenger) {
        return passengerService.savePassenger(passenger);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passenger> updatePassenger(@PathVariable Long id, @RequestBody Passenger updatedPassenger) {
        if (!passengerService.getPassengerById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        updatedPassenger.setPassengerId(id); // Assuming there's a setPassengerId method
        return ResponseEntity.ok(passengerService.savePassenger(updatedPassenger));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long id) {
        if (!passengerService.getPassengerById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        passengerService.deletePassenger(id);
        return ResponseEntity.noContent().build();
    }
}
