package ru.itdrive.web.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import ru.itdrive.web.models.*;
import ru.itdrive.web.services.*;

import java.util.*;

@Controller
public class UsersController {
    @Autowired
    private UsersService service;

    @RequestMapping(value = "/users")
    public ModelAndView getPage(@RequestParam("userId") Long userId) {
        Optional<User> userOptional = service.getUserById(userId);

        ModelAndView modelAndView = new ModelAndView();
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            modelAndView.addObject("user", user);
            modelAndView.setViewName("usersPage");
        } else {
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
            modelAndView.setViewName("errorPage");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String deleteUser(@RequestParam("userId") Long userId, @RequestParam("action") String action) {
        if (action != null && action.equals("delete"))
            service.deleteUserById(userId);

        return "redirect:/profile";
    }
}
