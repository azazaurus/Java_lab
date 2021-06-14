package ru.itis.restsemestrovka.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {
	private String email;
	private String password;
}
