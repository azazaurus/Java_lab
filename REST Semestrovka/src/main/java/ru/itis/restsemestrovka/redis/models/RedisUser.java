package ru.itis.restsemestrovka.redis.models;

import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.redis.core.*;

import javax.persistence.*;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("user")
public class RedisUser {
	@Id
	private String id;
	private List<String> tokens;
	private Long userId;
}
