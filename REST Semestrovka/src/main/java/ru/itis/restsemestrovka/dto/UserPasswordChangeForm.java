package ru.itis.restsemestrovka.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordChangeForm {
	private String oldPassword;
	private String newPassword;
}
