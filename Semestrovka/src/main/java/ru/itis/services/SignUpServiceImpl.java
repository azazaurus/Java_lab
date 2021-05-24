package ru.itis.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import ru.itis.dto.*;
import ru.itis.model.*;
import ru.itis.repositories.*;
import ru.itis.util.*;

import java.util.*;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MailsGenerator mailsGenerator;

    @Autowired
    private EmailUtil emailUtil;

    @Value("${server.url}")
    private String serverUrl;

    @Value("${mail.user}")
    private String from;

    @Value("${mail.subject}")
    private String subject;

    @Override
    public void signUp(UserForm userForm) {
        User newUser = User.builder()
                .email(userForm.getEmail())
                .hashPassword(passwordEncoder.encode(userForm.getPassword()))
                .role(User.Role.USER)
                .state(User.State.CONFORMED)
                .status(User.Status.ACTIVE)
                .confirm_code(UUID.randomUUID().toString())
                .build();

        usersRepository.save(newUser);

//        String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, newUser.getConfirm_code());
//
//        emailUtil.sendMail(newUser.getEmail(), subject, from, confirmMail);
    }
}
