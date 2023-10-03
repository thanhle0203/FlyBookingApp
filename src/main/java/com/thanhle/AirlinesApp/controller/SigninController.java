package com.thanhle.AirlinesApp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thanhle.AirlinesApp.domain.User;
import com.thanhle.AirlinesApp.dto.SigninForm;
import com.thanhle.AirlinesApp.service.UserService;



@Controller
@RequestMapping("/signin")
public class SigninController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String signIn(Model model) {
        model.addAttribute("signInForm", new SigninForm());
        return "signinForm";  // Name of your JSP page
    }

    @PostMapping
    public String signInSubmit(
        @ModelAttribute SigninForm signInForm,
        RedirectAttributes redirectAttributes) {

        User user = userService.findByEmail(signInForm.getEmail());

        if (user != null && userService.checkPassword(user, signInForm.getPassword())) {
            // Optionally, add a message to the model to be displayed in the view
            redirectAttributes.addFlashAttribute("message", "Signin successful");
            return "redirect:/homepage";  // Redirect to home page
        }

        redirectAttributes.addFlashAttribute("error", "Invalid email or password");
        return "redirect:/signin";  // Redirect back to the sign-in page on failure
    }
}
