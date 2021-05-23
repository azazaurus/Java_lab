package ru.itis.restsemestrovka.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import ru.itis.restsemestrovka.dto.*;
import ru.itis.restsemestrovka.models.*;
import ru.itis.restsemestrovka.repositories.*;

import java.util.*;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Collection<UserDto> getAll() {
		return UserDto.from(repository.findAll());
	}

	public UserDto create(UserCreateForm userForm) {
		var passwordHash = passwordEncoder.encode(userForm.getPassword());
		var user = new User(
			null,
			userForm.getEmail(),
			passwordHash,
			User.State.NOT_CONFIRMED,
			User.Role.USER,
			User.Status.ACTIVE);

		repository.save(user);

		return UserDto.from(user);
	}

	public boolean changePassword(Long userId, UserPasswordChangeForm passwordChangeForm) {
		if (passwordChangeForm.getOldPassword().equals(passwordChangeForm.getNewPassword()))
			return false;

		var userResult = repository.findById(userId);
		if (userResult.isEmpty())
			return false;

		var user = userResult.get();
		if (!passwordEncoder.matches(passwordChangeForm.getOldPassword(), user.getPasswordHash()))
			return false;

		var passwordHash = passwordEncoder.encode(passwordChangeForm.getNewPassword());
		user.setPasswordHash(passwordHash);

		repository.save(user);

		return true;
	}

	public boolean ban(Long userId) {
		throw new UnsupportedOperationException();
	}
}
