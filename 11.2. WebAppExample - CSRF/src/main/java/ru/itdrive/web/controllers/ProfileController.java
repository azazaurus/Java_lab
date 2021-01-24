package ru.itdrive.web.controllers;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.io.*;

@Controller
public class ProfileController {
    @RequestMapping(value = "/profile")
    public void getPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        Cookie[] cookies = request.getCookies();
        String fileName = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("fileUploaded")) {
                fileName = cookie.getValue();
            }
        }

        writer.println("<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Hello, User</h1>" +
                "<h1>" + fileName + "</h1>\n" +
                "<img src=\"/image\">\n" +
                "</body>\n" +
                "</html>");
    }
}
