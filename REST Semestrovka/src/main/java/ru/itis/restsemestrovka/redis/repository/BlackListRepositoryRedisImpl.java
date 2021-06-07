package ru.itis.restsemestrovka.redis.repository;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.*;
import ru.itis.restsemestrovka.repositories.*;

@Repository
public class BlackListRepositoryRedisImpl implements BlackListRepository {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public void save(String token) {
		redisTemplate.opsForValue().set(token, "");
	}

	@Override
	public boolean exists(String token) {
		Boolean hasToken = redisTemplate.hasKey(token);
		return hasToken != null && hasToken;
	}
}
