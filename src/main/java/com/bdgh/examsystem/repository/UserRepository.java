package com.bdgh.examsystem.repository;

import com.bdgh.examsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findByResetPasswordToken(String resetPasswordToken);
}
