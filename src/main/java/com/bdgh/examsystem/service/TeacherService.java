package com.bdgh.examsystem.service;

import com.bdgh.examsystem.dto.Teacher.TeacherDetailsDto;
import com.bdgh.examsystem.dto.Teacher.TeacherSummaryDto;
import com.bdgh.examsystem.entity.Teacher;
import org.springframework.data.domain.Page;

public interface TeacherService {
    TeacherSummaryDto editTeacher(Long id, TeacherDetailsDto teacherDetailsDto);

    Page<TeacherSummaryDto> getPageTeacherSummary(int page, int size);

    Teacher findById(Long id);

    void delete(Long id);
}
