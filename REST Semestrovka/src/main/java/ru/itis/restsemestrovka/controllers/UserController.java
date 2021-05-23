package ru.itis.restsemestrovka.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.restsemestrovka.dto.*;
import ru.itis.restsemestrovka.services.*;

@RestController
public class UserController {
	@Autowired
	private UserService service;

	@GetMapping("/users")
	public ResponseEntity<UserDto[]> get() {
		UserDto[] users = service.getAll().toArray(new UserDto[0]);
		return ResponseEntity.ok(users);
	}

	@PostMapping("/users")
	public ResponseEntity<UserDto> create(@RequestBody UserCreateForm userForm) {
		var user = service.create(userForm);
		return ResponseEntity.ok(user);
	}

	@PutMapping(value = "/users/{user-id}/change-password", consumes = "text/plain")
	public ResponseEntity<?> changePassword(
			@PathVariable("user-id") Long userId,
			@RequestBody String newPassword) {
		if (!service.changePassword(userId, newPassword))
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/users/{user-id}")
	public ResponseEntity<?> ban(@PathVariable("user-id") Long userId) {
		if (!service.ban(userId))
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok().build();
	}
}
