package ru.itis.util;

public interface MailsGenerator {
    String getMailForConfirm(String serverUrl, String code);
}
