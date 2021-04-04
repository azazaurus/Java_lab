package ru.itis.util;

import org.springframework.beans.factory.annotation.*;
import org.springframework.mail.javamail.*;

import java.util.*;
import java.util.concurrent.*;

public class EmailUtilImpl implements EmailUtil {

    JavaMailSender javaMailSender;

    @Autowired
    private ExecutorService executorService;

    public EmailUtilImpl(Properties mailProperties) {
        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
        javaMailSenderImpl.setHost(mailProperties.getProperty("mail.host"));
        javaMailSenderImpl.setPort(Integer.parseInt(mailProperties.getProperty("mail.port")));
        javaMailSenderImpl.setUsername(mailProperties.getProperty("mail.user"));
        javaMailSenderImpl.setPassword(mailProperties.getProperty("mail.password"));
        javaMailSenderImpl.setDefaultEncoding("UTF-8");
        javaMailSenderImpl.setJavaMailProperties(mailProperties);

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
