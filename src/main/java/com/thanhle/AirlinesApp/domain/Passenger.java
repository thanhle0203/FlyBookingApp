package com.thanhle.AirlinesApp.domain;

import java.time.LocalDate;
import java.util.*;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Passenger {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long passengerId;
	
	private String fname; 	// first name
	private String lname;	// last name
	
	private String email;
	private String phone;
	private String gender;
	
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate dateOfBirth;
	
	@Enumerated(EnumType.STRING)
	private IdentificationType idType;
	
	
	@ManyToMany(mappedBy = "passengers")
	private List<Reservation> reservations = new ArrayList<>();

	


}
