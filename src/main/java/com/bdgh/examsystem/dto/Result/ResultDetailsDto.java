package com.bdgh.examsystem.dto.Result;

import com.bdgh.examsystem.dto.Exam.ExamBasicDto;
import com.bdgh.examsystem.dto.Exam.ExamDetailsDto;
import com.bdgh.examsystem.dto.Student.StudentBasicDto;
import com.bdgh.examsystem.dto.Student.StudentDetailsDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResultDetailsDto {
    Long id;
    Long soCauDung;
    String batDau;
    String nopBai;
    String studentAnswer;
    ExamBasicDto exam;
    StudentBasicDto student;
}
