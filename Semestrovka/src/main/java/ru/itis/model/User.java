package ru.itis.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String confirm_code;

    @Enumerated(value = EnumType.STRING)
    private State state;

    public enum State {
        CONFORMED, NOT_CONFIRMED
    }
}
