package com.bdgh.examsystem.service;


import com.bdgh.examsystem.dto.AuthenticationRequest;
import com.bdgh.examsystem.dto.RegisterRequest;
import com.bdgh.examsystem.dto.Token;

public interface AuthenticationService {
    Token register(RegisterRequest request);
    Token authenticate(AuthenticationRequest request);
}
