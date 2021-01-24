package ru.itdrive.web.controllers;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.*;

import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;

@Controller
@MultipartConfig
public class FilesUploadController {
    @RequestMapping(value = "/files")
    public String getPage() {
        return "fileUpload";
    }

    @RequestMapping(value = "/files", method = RequestMethod.POST)
    public ModelAndView addFile(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestParam("files") MultipartFile file) {
        System.out.print(file.getOriginalFilename() + " ");
        System.out.print(file.getContentType() + " ");
        System.out.println(file.getSize());

        try {
            Files.copy(file.getInputStream(), Paths.get("C://files/" + file.getOriginalFilename()));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        Cookie cookie = new Cookie("fileUploaded", file.getOriginalFilename());
        response.addCookie(cookie);

        return new ModelAndView("redirect:/files");
    }
}
