package ru.itis.springbootdemo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.*;
import org.springframework.context.annotation.*;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.ExecutorService;

/**
 * 15.02.2021
 * 19. spring-boot-demo
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Profile("master")
@Component
public class EmailUtilImpl implements EmailUtil {

    JavaMailSender javaMailSender;

    @Autowired
    private ExecutorService executorService;

    public EmailUtilImpl() {
        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
        Properties properties = new Properties();
        try {
            properties.load(Files.newBufferedReader(Paths.get("src/main/resources/application.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        javaMailSenderImpl.setJavaMailProperties(properties);
        javaMailSender = javaMailSenderImpl;
    }

    @Override
    public void sendMail(String to, String subject, String from, String text) {
        executorService.submit(() -> javaMailSender.send(mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(text, true);
        }));
    }
}
