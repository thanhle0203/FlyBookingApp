package com.thanhle.AirlinesApp.domain;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@JsonIdentityInfo(
	     generator = ObjectIdGenerators.PropertyGenerator.class, 
	     property = "roleId")
public class Role {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long roleId;
	
	@NotEmpty
	private String roleName;
	
	@ManyToMany(mappedBy="roles")
	//@JsonBackReference
	private Set<User> users = new HashSet<>();
	
}
