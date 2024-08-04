package com.bdgh.examsystem.service;

import com.bdgh.examsystem.dto.Exam.ExamDetailsDto;
import com.bdgh.examsystem.dto.Exam.ExamOverviewDto;
import com.bdgh.examsystem.dto.Exam.ExamSummaryDto;
import com.bdgh.examsystem.entity.ExamType;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface ExamService {
    List<ExamSummaryDto> findALL();
    ExamDetailsDto findById(Long id);

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    ExamSummaryDto add(ExamDetailsDto examDetailsDTO);

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    ExamSummaryDto update(Long id, ExamDetailsDto examDetailsDTO);

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    void deleteById(Long id);

    Page<ExamSummaryDto> filterExam(ExamType type, int page);

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    ExamOverviewDto getOverview(Long id);
}