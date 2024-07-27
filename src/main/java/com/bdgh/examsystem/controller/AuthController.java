package com.bdgh.examsystem.controller;

import com.bdgh.examsystem.Exception.NotFoundException;
import com.bdgh.examsystem.config.JwtService;
import com.bdgh.examsystem.dto.*;
import com.bdgh.examsystem.entity.ResponseObject;
import com.bdgh.examsystem.entity.Role;
import com.bdgh.examsystem.entity.User;
import com.bdgh.examsystem.service.AuthenticationService;
import com.bdgh.examsystem.service.StudentService;
import com.bdgh.examsystem.service.TeacherService;
import com.bdgh.examsystem.service.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    @Autowired
    private UserService userService;
    @PostMapping("/forgotPassword")
    public ResponseEntity<ResponseObject> forgotPassword(@RequestBody Map<String, String> emailMap) throws MessagingException {
        String email = emailMap.get("email");
        try {
            userService.forgotPassword(email);
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", ex.getMessage(), null)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Gửi email đặt lại mật khẩu thành công", "")
        );
    }
    @PostMapping("/resetPassword")
    public ResponseEntity<ResponseObject> resetPassword(@RequestBody Map<String, String> resetInfo) {
        try {
            userService.resetPassword(resetInfo);
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", ex.getMessage(), null)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Đặt lại mật khẩu thành công", "")
        );
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(
            @RequestBody RegisterRequest request
    ) {
        Token token = authenticationService.register(request);
        if (token != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Đăng ký thành công!", token)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("error", "Tài khoản đã tồn tại!", token)
        );
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseObject> register(
            @RequestBody AuthenticationRequest request
    ) {
        Token token = authenticationService.authenticate(request);
        if (token != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Đăng nhập thành công!", token)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("error", "Tài khoản, mật khẩu không đúng!", token)
        );
    }
}
