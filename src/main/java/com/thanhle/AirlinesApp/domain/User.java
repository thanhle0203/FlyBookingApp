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
@Table(name = "users", uniqueConstraints = {
	    @UniqueConstraint(columnNames = "username"),
	    @UniqueConstraint(columnNames = "email")
	})
public class User {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long userId;
	
	@Column(nullable = false, unique = true)
	private String username;
	
	@NotEmpty
	private String password;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@JoinTable(
			name="user_role", 
			joinColumns=@JoinColumn(name="user_id"), 
			inverseJoinColumns=@JoinColumn(name="role_id"))
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	//@JsonManagedReference
	private Set<Role> roles = new HashSet<>();
	

	
}
