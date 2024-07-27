package com.bdgh.examsystem.repository;

import com.bdgh.examsystem.entity.Exam;
import com.bdgh.examsystem.entity.ExamType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    Page<Exam> findByExamType(ExamType examType, Pageable pageable);
}

