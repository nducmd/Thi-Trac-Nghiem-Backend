package com.bdgh.examsystem.service.Impl;

import com.bdgh.examsystem.Exception.NotFoundException;
import com.bdgh.examsystem.dto.UserDto;
import com.bdgh.examsystem.entity.Role;
import com.bdgh.examsystem.entity.User;
import com.bdgh.examsystem.repository.UserRepository;
import com.bdgh.examsystem.service.GmailSenderService;
import com.bdgh.examsystem.service.StudentService;
import com.bdgh.examsystem.service.TeacherService;
import com.bdgh.examsystem.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

import static com.bdgh.examsystem.entity.Role.STUDENT;
import static com.bdgh.examsystem.entity.Role.TEACHER;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private GmailSenderService gmailSenderService;


    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public void resetPassword(Map<String, String> resetInfo) {
        String token = resetInfo.get("token");
        String newPassword = resetInfo.get("newPassword");
        User user = userRepository.findByResetPasswordToken(token);
        if (user == null) throw new NotFoundException("Token không tồn tại hoặc đã hết hạn");
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetPasswordToken(null);
        userRepository.save(user);
    }

    @Override
    public void forgotPassword(String email) throws MessagingException {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) throw new NotFoundException("Địa chỉ email không tồn tại");
        String token = UUID.randomUUID().toString();
        user.setResetPasswordToken(token);
        User user1 = userRepository.save(user);
        gmailSenderService.sendEmailResetPassword(user1);
    }
}
