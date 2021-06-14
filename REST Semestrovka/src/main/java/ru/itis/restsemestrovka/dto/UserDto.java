package ru.itis.restsemestrovka.dto;

import lombok.*;
import ru.itis.restsemestrovka.models.*;

import java.util.*;
import java.util.stream.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private Long id;
	private String email;

	private User.State state;
	private User.Role role;
	private User.Status status;

	public static UserDto from(User user) {
		return new UserDto(
			user.getId(),
			user.getEmail(),
			user.getState(),
			user.getRole(),
			user.getStatus());
	}

	public static List<UserDto> from(Collection<User> users) {
		return users.stream().map(UserDto::from).collect(Collectors.toList());
	}
}
