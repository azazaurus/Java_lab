package ru.itis.springbootdemo.util;

import org.springframework.context.annotation.*;

@Profile("dev")
public class EmailUtilFakeImpl implements EmailUtil{
    @Override
    public void sendMail(String to, String subject, String from, String text) {
        System.out.println(text);
    }
}
