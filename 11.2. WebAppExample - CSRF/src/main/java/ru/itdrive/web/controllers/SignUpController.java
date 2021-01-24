package ru.itdrive.web.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import ru.itdrive.web.models.*;
import ru.itdrive.web.services.*;

@Controller
public class SignUpController {
    @Autowired
    private SignUpService service;

    @RequestMapping(value = "/signUp")
    public String getPage() {
        System.out.println(Thread.currentThread().getName());
        return "signUp";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String signUp(
        @RequestParam("email") String email,
        @RequestParam("password") String password) {
        User user = User.builder()
                .email(email)
                .password(password)
                .build();

        service.signUp(user);

        return "redirect:/signIn";
    }
}
