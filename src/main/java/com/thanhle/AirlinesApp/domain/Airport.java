package com.thanhle.AirlinesApp.domain;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Airport {
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Id
	private Long airportId;
	
	private String airportCity;
	
	private String airportCode;
	
	@OneToMany(mappedBy = "arrivalAirport")
	@JsonManagedReference(value = "flight-arrivalAirport")
	private List<Flight> arrivalFlights = new ArrayList<>();
	
	@OneToMany(mappedBy = "departureAirport")
	@JsonManagedReference(value = "flight-departureAirport")
	private List<Flight> departureFlights = new ArrayList<>();

}
