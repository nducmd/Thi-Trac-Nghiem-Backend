package com.bdgh.examsystem.service.Impl;

import com.bdgh.examsystem.config.JwtService;
import com.bdgh.examsystem.dto.AuthenticationRequest;
import com.bdgh.examsystem.dto.RegisterRequest;
import com.bdgh.examsystem.entity.Role;
import com.bdgh.examsystem.entity.Student;
import com.bdgh.examsystem.entity.Teacher;
import com.bdgh.examsystem.entity.User;
import com.bdgh.examsystem.dto.Token;
import com.bdgh.examsystem.repository.StudentRepository;
import com.bdgh.examsystem.repository.TeacherRepository;
import com.bdgh.examsystem.repository.UserRepository;
import com.bdgh.examsystem.service.AuthenticationService;
import com.bdgh.examsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;

    public Token register(RegisterRequest request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isEmpty())
        {
            var user = User.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .build();
            userRepository.save(user);
            User user1 = userService.findUserByEmail(request.getEmail());
            if (request.getRole().equals(Role.TEACHER)) {
                Teacher teacher = Teacher.builder()
                        .ho(request.getHo())
                        .ten(request.getTen())
                        .user(user1)
                        .build();
                teacherRepository.save(teacher);
            } else {
                Student student = Student.builder()
                        .ho(request.getHo())
                        .ten(request.getTen())
                        .user(user1)
                        .build();
                studentRepository.save(student);
            }
            return new Token(jwtService.generateToken(user), request.getRole().name());
        }
        return null;
    }

    public Token authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            // Xử lý ngoại lệ khi mật khẩu không đúng
            return null;
        }
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        String token = jwtService.generateToken(user);
        if (token != null) {
            return new Token(token, user.getRole().name());
        }
        return null;
    }
}