package com.bdgh.examsystem.service;

import com.bdgh.examsystem.dto.Exam.ExamDetailsDto;
import com.bdgh.examsystem.dto.Result.ResultDetailsDto;
import com.bdgh.examsystem.dto.Result.ResultSummaryDto;

import java.util.List;

public interface ResultService {
    ResultDetailsDto editResult(Long id, ResultDetailsDto resultDetailsDto);
    ResultDetailsDto createResult(ResultDetailsDto resultDetailsDto);
    List<ResultSummaryDto> findAllResult();
    ResultDetailsDto findById(Long id);
    void deleteResultById(Long id);
    void updateSoCauDung(ResultDetailsDto resultDetailsDto, ExamDetailsDto examDetailsDto);

    List<ResultSummaryDto> getResultsByExamId(Long examId);

    List<ResultSummaryDto> getResultsByStudentId(Long studentId);
}
