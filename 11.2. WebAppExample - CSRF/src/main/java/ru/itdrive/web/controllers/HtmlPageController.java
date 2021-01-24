package ru.itdrive.web.controllers;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class HtmlPageController {
    @RequestMapping(value = "/search")
    public String getPage() {
        return "search";
    }
}
