package com.thanhle.AirlinesApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SignupForm {

    @NotEmpty(message = "Username is required")
    private String username;

    @NotEmpty(message = "Password is required")
    private String password;

    @Email(message = "Invalid email format")
    @NotEmpty(message = "Email is required")
    private String email;

    @NotNull(message = "Role is required")
    private String role;
}
