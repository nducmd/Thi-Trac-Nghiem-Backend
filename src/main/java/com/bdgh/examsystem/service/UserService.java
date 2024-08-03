package com.bdgh.examsystem.service;

import com.bdgh.examsystem.entity.User;
import jakarta.mail.MessagingException;

import java.util.Map;

public interface UserService {
    User findUserByEmail(String email);
    void resetPassword(Map<String, String> resetInfo);
    void forgotPassword(String email) throws MessagingException;
}
