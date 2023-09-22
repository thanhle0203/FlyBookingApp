package com.thanhle.AirlinesApp.service;

import java.util.List;
import java.util.Optional;

import com.thanhle.AirlinesApp.domain.Airport;

public interface AirportService {
	public List<Airport> getAllAirports();
	public Optional<Airport> getAirportById(Long id);
	public Airport saveAirport(Airport airport);
	public void deleteAirport(Long id);

}
