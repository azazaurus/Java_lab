package ru.itis.util;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

@Profile("dev")
@Component
public class EmailUtilFakeImpl implements EmailUtil {
    @Override
    public void sendMail(String to, String subject, String from, String text) {
        System.out.println(text);
    }
}
