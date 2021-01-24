package ru.itdrive.web.controllers;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;

@Controller
public class ImagesController {
    @RequestMapping(value = "/image")
    public void getImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("fileUploaded")) {
                File file = new File("C://files/" + cookie.getValue());

                response.setContentType("image/jpeg");
                response.setContentLength((int)file.length());
                response.setHeader("Content-Disposition", "filename=\"" + cookie.getValue() + "\"");

                Files.copy(file.toPath(), response.getOutputStream());
                response.flushBuffer();
                return;
            }
        }
    }
}
