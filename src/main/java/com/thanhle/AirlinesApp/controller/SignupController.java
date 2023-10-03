package com.thanhle.AirlinesApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thanhle.AirlinesApp.domain.Role;
import com.thanhle.AirlinesApp.domain.User;
import com.thanhle.AirlinesApp.dto.SignupForm;
import com.thanhle.AirlinesApp.service.UserService;
import com.thanhle.AirlinesApp.validator.SignupFormValidator;

import jakarta.validation.Valid;

// other imports...

@Controller
public class SignupController {

    @Autowired
    private SignupFormValidator signupFormValidator;

    @InitBinder("signupForm")
    protected void initBinder(final WebDataBinder binder) {
        binder.addValidators(signupFormValidator);
    }
    
    @Autowired
    private UserService userService;
	
	
	@GetMapping("/signup")
	public String signup(Model model) {
	    model.addAttribute("signupForm", new SignupForm());  // Create a new SignupForm object for the form
	    return "signupForm";
	}


    @PostMapping("/signup")
    public String signupSubmit(
            @Valid @ModelAttribute SignupForm signupForm, 
            BindingResult result, 
            Model model, 
            RedirectAttributes redirectAttributes) {
    	if (result.hasErrors()) {
            return "signupForm";  // return to the signup form if there are validation errors
        }
    	
    	// Create a new User and Role objects from the form data
	    User newUser = new User();
	    newUser.setUsername(signupForm.getUsername());
	    newUser.setPassword(signupForm.getPassword());  // You'll want to hash the password before saving it
	    newUser.setEmail(signupForm.getEmail());

	    Role newRole = new Role();
	    newRole.setRoleName(signupForm.getRole());
	    newUser.getRoles().add(newRole);

	    // Save the new User and Role to the database
	    userService.save(newUser);  // Assumes you have a saveUser method in your UserService

	    // Optionally, add a message to the model to be displayed in the view
	    model.addAttribute("message", "Signup successful");
    	
    	// If signup is successful, redirect to the login page
        redirectAttributes.addFlashAttribute("message", "Signup successful");
        return "redirect:/signin";
    }

    // ...
}

