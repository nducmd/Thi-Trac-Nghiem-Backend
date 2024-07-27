package com.bdgh.examsystem.service;

import com.bdgh.examsystem.dto.Result.ResultDetailsDto;
import com.bdgh.examsystem.dto.Student.StudentDetailsDto;
import com.bdgh.examsystem.dto.Student.StudentSummaryDto;
import com.bdgh.examsystem.entity.Student;
import org.springframework.data.domain.Page;

public interface StudentService {
    StudentSummaryDto editStudent(Long id, StudentDetailsDto studentDetailsDto);

    Page<StudentSummaryDto> getPageStudentSummary(int page, int size);

    Page<StudentSummaryDto> getStudentByKeyword(String keyword, int page);

    void delete(Long id);

    Student findById(Long id);
}
