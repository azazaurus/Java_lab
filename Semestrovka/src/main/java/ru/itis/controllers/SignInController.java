package ru.itis.controllers;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import javax.annotation.security.*;

@Controller
public class SignInController {
    @PermitAll
    @GetMapping("/signIn")
    public String getSignInPage() {
        return "sign_in_page";
    }
}
