package com.bdgh.examsystem.repository;

import com.bdgh.examsystem.entity.Student;
import com.bdgh.examsystem.entity.Teacher;
import com.bdgh.examsystem.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Page<Teacher> findAll(Pageable pageable);
    Optional<Teacher> findByUser(User user);
}
