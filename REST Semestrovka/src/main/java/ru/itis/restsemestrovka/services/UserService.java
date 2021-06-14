package ru.itis.restsemestrovka.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import ru.itis.restsemestrovka.dto.*;
import ru.itis.restsemestrovka.models.*;
import ru.itis.restsemestrovka.redis.repository.*;
import ru.itis.restsemestrovka.redis.service.*;
import ru.itis.restsemestrovka.repositories.*;

import java.util.*;

@Service
public class UserService {
	@Autowired
	private RedisUsersService redisUsersService;

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Collection<UserDto> getAll() {
		return UserDto.from(repository.findAll());
	}

	public UserDto create(UserForm userForm) {
		var passwordHash = passwordEncoder.encode(userForm.getPassword());
		var user = new User(
			null,
			userForm.getEmail(),
			passwordHash,
			null,
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
		if (!passwordEncoder.matches(passwordChangeForm.getOldPassword(), user.getHashPassword()))
			return false;

		var passwordHash = passwordEncoder.encode(passwordChangeForm.getNewPassword());
		user.setHashPassword(passwordHash);

		repository.save(user);

		return true;
	}

	public boolean ban(Long userId) {
		var userResult = repository.findById(userId);
		if (userResult.isEmpty())
			return false;

		var user = userResult.get();
		user.setStatus(User.Status.BANNED);

		repository.save(user);

		return true;
	}

	public boolean banAll() {
		var users = repository.findAll();
		if (users.isEmpty())
			return false;

		for (var user : users)
			if (!user.isAdmin())
				user.setStatus(User.Status.BANNED);

		repository.saveAll(users);

		return true;
	}

	public void blockUser(Long userId) {
		User user = repository.findById(userId).orElseThrow(IllegalArgumentException::new);
		redisUsersService.addAllTokenToBlackList(user);
	}
}
