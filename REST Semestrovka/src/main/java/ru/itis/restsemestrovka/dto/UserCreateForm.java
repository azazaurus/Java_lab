package ru.itis.restsemestrovka.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateForm {
	private String email;
	private String password;
}
