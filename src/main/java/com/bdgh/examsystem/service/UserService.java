package com.bdgh.examsystem.service;

import com.bdgh.examsystem.dto.AuthenticationRequest;
import com.bdgh.examsystem.dto.Token;
import com.bdgh.examsystem.dto.UserDto;
import com.bdgh.examsystem.entity.User;
import jakarta.mail.MessagingException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;

public interface UserService {
    User findUserByEmail(String email);
    void resetPassword(Map<String, String> resetInfo);
    void forgotPassword(String email) throws MessagingException;
}
