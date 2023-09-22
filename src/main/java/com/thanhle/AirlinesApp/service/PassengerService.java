package com.thanhle.AirlinesApp.service;

import java.util.List;
import java.util.Optional;

import com.thanhle.AirlinesApp.domain.Passenger;

public interface PassengerService {
	public List<Passenger> getAllPassengers();
	public Optional<Passenger> getPassengerById(Long id);
	public Passenger savePassenger(Passenger passenger);
	public void deletePassenger(Long id);

}
