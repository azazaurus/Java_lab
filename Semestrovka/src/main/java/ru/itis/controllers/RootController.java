package ru.itis.controllers;

import org.springframework.security.core.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.*;

@Controller
@RequestMapping("/")
public class RootController {
    @PermitAll
    @GetMapping
    public String getRoot(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/profile";
        } else {
            return "redirect:/signIn";
        }
    }
}
