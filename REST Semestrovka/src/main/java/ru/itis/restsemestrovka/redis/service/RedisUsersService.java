package ru.itis.restsemestrovka.redis.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import ru.itis.restsemestrovka.models.*;
import ru.itis.restsemestrovka.redis.models.*;
import ru.itis.restsemestrovka.redis.repository.*;
import ru.itis.restsemestrovka.repositories.*;
import ru.itis.restsemestrovka.services.*;

import java.util.*;

@Service
public class RedisUsersService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RedisUsersRepository redisUsersRepository;

	@Autowired
	private JwtBlackListService blackListService;

	public void addTokenToUser(User user, String token) {
		String redisId = user.getRedisId();

		RedisUser redisUser;
		if (redisId != null) {
			redisUser = redisUsersRepository.findById(redisId)
				.orElseThrow(IllegalArgumentException::new);

			if (redisUser.getTokens() == null) {
				redisUser.setTokens(new ArrayList<>());
			}
			redisUser.getTokens().add(token);
		} else {
			redisUser = RedisUser.builder()
				.userId(user.getId())
				.tokens(Collections.singletonList(token))
				.build();
		}
		redisUsersRepository.save(redisUser);
		user.setRedisId(redisUser.getId());
		userRepository.save(user);
	}

	public void addAllTokenToBlackList(User user) {
		if (user.getRedisId() != null) {
			RedisUser redisUser = redisUsersRepository.findById(user.getRedisId())
				.orElseThrow(IllegalArgumentException::new);

			List<String> tokens = redisUser.getTokens();
			for (String token : tokens) {
				blackListService.add(token);
			}
			redisUser.getTokens().clear();
			redisUsersRepository.save(redisUser);
		}
	}
}
