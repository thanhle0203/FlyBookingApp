package com.thanhle.AirlinesApp.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Airlines {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airlineId;
	
	private Long airlinesId;
	private String airlinesName;
	private String airlinesCode;
	

	@OneToMany(mappedBy = "operatingAirlines")
    @JsonManagedReference(value = "flight-airline")
	private List<Flight> flights = new ArrayList<>();
	
	

}
