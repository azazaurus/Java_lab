package ru.itis.controllers;

import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class PingController {

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void ping() {
		System.out.println("Ping!");
	}

}
