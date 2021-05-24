package ru.itis.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String hashPassword;
    private String confirm_code;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    public enum State {
        CONFORMED, NOT_CONFIRMED
    }

    public enum Role {
        USER, ADMIN
    }

    public enum Status {
        ACTIVE, BANNED
    }

    public boolean isActive() {
        return this.status == Status.ACTIVE;
    }

    public boolean isBanned() {
        return this.status == Status.BANNED;
    }

    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }

    public boolean isUser() {
        return this.role == Role.USER;
    }
}
