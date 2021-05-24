package ru.itis.dto;

import lombok.*;
import ru.itis.validation.*;

import javax.validation.constraints.*;

@Data
public class UserForm {
	@Email(message = "{errors.incorrect.email}")
    private String email;
	@ValidPassword(message = "{errors.incorrect.password}")
    private String password;
}
