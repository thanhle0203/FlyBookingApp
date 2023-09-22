package com.thanhle.AirlinesApp.domain;

import java.time.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
public class Flight {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long flightId;
	
	private String flightNum;
	
	@NotEmpty
	private String departureCity;
	
	@NotEmpty
	private String arrivalCity;
	
	private Double ticketPrice;
	
	private int capacity;
	
	private int booked;
	
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate departureDate;
	
	@DateTimeFormat(iso=DateTimeFormat.ISO.TIME)
	private LocalTime departureTime;
	
	@ManyToOne
	@JoinColumn(name = "airlineId")
    @JsonBackReference(value = "flight-airline")
	private Airlines operatingAirlines;
	
	@ManyToOne
    @JsonBackReference(value = "flight-arrivalAirport")
    private Airport arrivalAirport;

    @ManyToOne
    @JsonBackReference(value = "flight-departureAirport")
    private Airport departureAirport;
	

}
