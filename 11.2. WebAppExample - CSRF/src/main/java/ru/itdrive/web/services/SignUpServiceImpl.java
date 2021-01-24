package ru.itdrive.web.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import ru.itdrive.web.models.*;
import ru.itdrive.web.repositories.*;

import java.util.*;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private EmailsService emailsService;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void signUp(User user) {
        String confirmCode = UUID.randomUUID().toString();

        user.setConfirmCode(confirmCode);

        String url = "https://itdrive.pro/confirm/" + confirmCode;

        usersRepository.save(user);
        emailsService.sendMail("Подтверждение регистрации", url, user.getEmail());
    }
}
