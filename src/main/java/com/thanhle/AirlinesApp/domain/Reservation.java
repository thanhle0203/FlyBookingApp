package com.thanhle.AirlinesApp.domain;

import java.time.LocalDateTime;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "reservationId")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reservationId;
	
	@ManyToMany
    @JoinTable(
        name = "reservation_passenger",
        joinColumns = @JoinColumn(name = "reservation_id"),
        inverseJoinColumns = @JoinColumn(name = "passenger_id")
    )
    private List<Passenger> passengers = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "flight_id")
	private Flight flight;
	
	private LocalDateTime dateOfBooking = LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	

}
