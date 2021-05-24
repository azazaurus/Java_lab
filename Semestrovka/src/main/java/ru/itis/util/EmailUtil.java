package ru.itis.util;

import org.springframework.stereotype.*;

@Component
public interface EmailUtil {
    void sendMail(String to, String subject, String from, String text);
}
