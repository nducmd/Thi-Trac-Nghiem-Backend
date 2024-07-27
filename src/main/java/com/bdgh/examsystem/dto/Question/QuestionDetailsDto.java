package com.bdgh.examsystem.dto.Question;

import com.bdgh.examsystem.dto.Exam.ExamBasicDto;
import com.bdgh.examsystem.dto.Exam.ExamDetailsDto;
import com.bdgh.examsystem.dto.Exam.ExamSummaryDto;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDetailsDto {
    private Long id;
    private String cauHoi;
    private String dapAn1;
    private String dapAn2;
    private String dapAn3;
    private String dapAn4;
    private Long dapAnDung;
    private ExamBasicDto exam;
}
