package com.bdgh.examsystem.service;

import com.bdgh.examsystem.dto.Exam.ExamDetailsDto;
import com.bdgh.examsystem.dto.Exam.ExamOverviewDto;
import com.bdgh.examsystem.dto.Exam.ExamSummaryDto;
import com.bdgh.examsystem.entity.ExamType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExamService {
    List<ExamSummaryDto> findALL();
    ExamDetailsDto findById(Long id);
    ExamSummaryDto save(ExamDetailsDto examDetailsDTO);
    void deleteById(Long id);

    Page<ExamSummaryDto> filterExam(ExamType type, int page);

    ExamOverviewDto getOverview(Long id);
}