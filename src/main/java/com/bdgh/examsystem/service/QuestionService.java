package com.bdgh.examsystem.service;

import com.bdgh.examsystem.dto.Question.QuestionDetailsDto;
import com.bdgh.examsystem.dto.Question.QuestionSummaryDto;

import java.util.List;

public interface QuestionService {
    List<QuestionDetailsDto> findALL();
    QuestionDetailsDto findById(Long id);
    QuestionDetailsDto save(QuestionDetailsDto questionDetailsDto);
    void deleteById(Long id);
    List<QuestionSummaryDto> findAllByExamId(Long id);
}