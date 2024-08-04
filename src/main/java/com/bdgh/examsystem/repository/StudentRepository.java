package com.bdgh.examsystem.repository;

import com.bdgh.examsystem.entity.Result;
import com.bdgh.examsystem.entity.Student;
import com.bdgh.examsystem.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findAll(Pageable pageable);
    Optional<Student> findByUser(User user);
    @Query("SELECT s FROM Student s WHERE lower(concat(s.ho, ' ', s.ten)) LIKE lower(concat('%', :keyword, '%')) OR lower(s.msv) LIKE lower(concat('%', :keyword, '%'))")
    Page<Student> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
