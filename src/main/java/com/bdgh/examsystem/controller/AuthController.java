package com.bdgh.examsystem.controller;

import com.bdgh.examsystem.dto.Auth.AuthenticationRequest;
import com.bdgh.examsystem.dto.Auth.RegisterRequest;
import com.bdgh.examsystem.dto.Token.Token;
import com.bdgh.examsystem.exception.NotFoundException;
import com.bdgh.examsystem.entity.ResponseObject;
import com.bdgh.examsystem.service.AuthenticationService;
import com.bdgh.examsystem.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(
            @RequestBody @Valid RegisterRequest request) {

        Token token = authenticationService.register(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Đăng ký thành công!", token)
        );
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseObject> register(
            @RequestBody @Valid AuthenticationRequest request) {

        Token token = authenticationService.authenticate(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Đăng nhập thành công!", token)
        );
    }

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


}
