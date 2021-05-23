package ru.itis.restsemestrovka.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import ru.itis.restsemestrovka.dto.*;
import ru.itis.restsemestrovka.repositories.*;

import java.util.*;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;

	public Collection<UserDto> getAll() {
		return UserDto.from(repository.findAll());
	}

	public UserDto create(UserCreateForm userForm) {
		throw new UnsupportedOperationException();
	}

	public boolean changePassword(Long userId, String newPassword) {
		throw new UnsupportedOperationException();
	}

	public boolean ban(Long userId) {
		throw new UnsupportedOperationException();
	}
}
