package com.bdgh.examsystem.service;

import com.bdgh.examsystem.entity.User;
import jakarta.mail.MessagingException;

public interface GmailSenderService {
    void sendEmailResetPassword(User user) throws MessagingException;
}
