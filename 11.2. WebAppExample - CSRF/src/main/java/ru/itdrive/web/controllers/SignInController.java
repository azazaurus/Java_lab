package ru.itdrive.web.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import ru.itdrive.web.services.*;

import javax.servlet.http.*;
import java.io.*;

import static ru.itdrive.web.filters.ResponseUtil.*;

@Controller
public class SignInController {
    @Autowired
    private SignInService service;

    @RequestMapping(value = "/signIn")
    public String getPage(@RequestParam(value = "redirect", required = false) String redirectUrl) {
        return "signInPage";
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public void login(
        @RequestParam("email") String email,
        @RequestParam("password") String password,
        @RequestParam(value = "redirect", required = false) String redirectUrl,
        HttpServletRequest request,
        HttpServletResponse response) throws IOException {
        if (service.authenticate(email, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("authenticated", true);
            if (redirectUrl == null)
                response.sendRedirect("/profile");
            else
                response.sendRedirect(redirectUrl);
        } else
            sendForbidden(request, response);
    }
}
