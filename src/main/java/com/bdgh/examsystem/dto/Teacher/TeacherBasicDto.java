package com.bdgh.examsystem.dto.Teacher;

import com.bdgh.examsystem.dto.Exam.ExamBasicDto;
import com.bdgh.examsystem.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TeacherBasicDto {
    private Long id;
    private String ho;
    private String ten;
    private String title;
}
