package ru.itis.springbootdemo.models;

import lombok.*;

/**
 * 10.02.2021
 * spring-boot-demo
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String email;
    private String password;
    private State state;
    private String confirm_code;

    public enum State {
        CONFIRMED, NOT_CONFIRMED
    }
}
