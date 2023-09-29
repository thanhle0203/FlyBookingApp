package com.thanhle.AirlinesApp.domain;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class User {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long userId;
	
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String password;
	
	@JoinTable(
			name="user_role", 
			joinColumns=@JoinColumn(name="user_id"), 
			inverseJoinColumns=@JoinColumn(name="role_id"))
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	//@JsonManagedReference
	private List<Role> roles = new ArrayList<>();
	
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
	private Passenger passenger;
	
	
}
