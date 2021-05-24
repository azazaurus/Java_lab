package ru.itis.validation;

import javax.validation.*;
import java.lang.annotation.*;

@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "invalid password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
