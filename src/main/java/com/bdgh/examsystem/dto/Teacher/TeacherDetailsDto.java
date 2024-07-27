package com.bdgh.examsystem.dto.Teacher;

import com.bdgh.examsystem.dto.Exam.ExamBasicDto;
import com.bdgh.examsystem.dto.UserDto;
import com.bdgh.examsystem.entity.Exam;
import com.bdgh.examsystem.entity.User;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TeacherDetailsDto {
    private Long id;
    private String ho;
    private String ten;
    private String title;
    private String dob;
    private UserDto userDto;
    private List<ExamBasicDto> examList;
}
