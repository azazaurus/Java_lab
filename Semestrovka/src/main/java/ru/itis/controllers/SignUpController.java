package ru.itis.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.*;
import ru.itis.services.*;

import javax.naming.*;
import javax.validation.*;

@Controller
public class SignUpController {
    @Autowired
    private SignUpService signUpService;

    @GetMapping("/signUp")
    public String getSignUpPage(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "sign_up_page";
    }

    @GetMapping("/success")
    public String getSuccessPage() {
        return "success_signup";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid UserForm user, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            signUpService.signUp(user);
            return "redirect:/success";
        } else {
            model.addAttribute("userForm", user);
            return "sign_up_page";
        }

    }
}
