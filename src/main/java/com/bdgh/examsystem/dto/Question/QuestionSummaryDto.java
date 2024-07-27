package com.bdgh.examsystem.dto.Question;

import com.bdgh.examsystem.dto.Exam.ExamDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionSummaryDto {
    private Long id;
    private String cauHoi;
    private String dapAn1;
    private String dapAn2;
    private String dapAn3;
    private String dapAn4;
    private Long dapAnDung;
}
