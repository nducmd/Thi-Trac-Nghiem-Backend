package com.bdgh.examsystem.dto.Result;

import com.bdgh.examsystem.dto.Exam.ExamBasicDto;
import com.bdgh.examsystem.dto.Exam.ExamDetailsDto;
import com.bdgh.examsystem.dto.Student.StudentBasicDto;
import com.bdgh.examsystem.dto.Student.StudentDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResultDetailsDto {
    private Long id;
    private Long soCauDung;
    private String batDau;
    private String nopBai;
    private String studentAnswer;
    private ExamBasicDto exam;
    private StudentBasicDto student;
}
