package ru.itis.restsemestrovka.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.restsemestrovka.dto.*;
import ru.itis.restsemestrovka.services.*;

@RestController
public class UserController {
	@Autowired
	private UserService service;

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/users")
	public ResponseEntity<UserDto[]> get() {
		UserDto[] users = service.getAll().toArray(new UserDto[0]);
		return ResponseEntity.ok(users);
	}

	@PostMapping("/users")
	public ResponseEntity<UserDto> create(@RequestBody UserForm userForm) {
		var user = service.create(userForm);
		return ResponseEntity.ok(user);
	}

	@PutMapping("/users/{user-id}/change-password")
	public ResponseEntity<?> changePassword(
			@PathVariable("user-id") Long userId,
			@RequestBody UserPasswordChangeForm passwordChangeForm) {
		if (!service.changePassword(userId, passwordChangeForm))
			return ResponseEntity.badRequest().build();

		return ResponseEntity.ok().build();
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/users/{user-id}")
	public ResponseEntity<?> ban(@PathVariable("user-id") Long userId) {
		if (!service.ban(userId))
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/users")
	public ResponseEntity<?> ban() {
		if (!service.banAll())
			return ResponseEntity.badRequest().build();

		return ResponseEntity.ok().build();
	}

	@PostMapping("/users/{user_id}/block")
	public ResponseEntity<?> blockUser(@PathVariable("user_id") Long userId ) {
		service.blockUser(userId);
		return ResponseEntity.ok().build();

	}
}
