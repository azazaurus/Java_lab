package ru.itis.restsemestrovka.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import ru.itis.restsemestrovka.repositories.*;

@Service
public class JwtBlackListService {

	@Autowired
	public BlackListRepository blackListRepository;

	public void add(String token) {
		blackListRepository.save(token);
	}

	public boolean exists(String token) {
		return blackListRepository.exists(token);
	}
}
