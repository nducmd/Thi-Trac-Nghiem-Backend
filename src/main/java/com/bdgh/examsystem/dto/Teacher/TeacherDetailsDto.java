package com.bdgh.examsystem.dto.Teacher;

import com.bdgh.examsystem.dto.Exam.ExamBasicDto;
import com.bdgh.examsystem.dto.User.UserDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherDetailsDto {
    Long id;
    String ho;
    String ten;
    String title;
    String dob;
    UserDto userDto;
    List<ExamBasicDto> examList;
}
