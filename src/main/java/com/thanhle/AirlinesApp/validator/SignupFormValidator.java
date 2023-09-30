package com.thanhle.AirlinesApp.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.thanhle.AirlinesApp.domain.User;
import com.thanhle.AirlinesApp.dto.SignupForm;
import com.thanhle.AirlinesApp.service.UserService;

@Component
public class SignupFormValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return SignupForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignupForm signupForm = (SignupForm) target;

        User userByUsername = userService.findByUsername(signupForm.getUsername());
        if (userByUsername != null) {
            errors.rejectValue("username", "username.alreadyExists", "The username is already taken");
        }

        User userByEmail = userService.findByEmail(signupForm.getEmail());
        if (userByEmail != null) {
            errors.rejectValue("email", "email.alreadyExists", "The email is already registered");
        }
    }
}
