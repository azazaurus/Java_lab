package ru.itis.restsemestrovka.redis.repository;

import org.springframework.data.keyvalue.repository.*;
import ru.itis.restsemestrovka.redis.models.*;

public interface RedisUsersRepository extends KeyValueRepository<RedisUser, String> {
}
