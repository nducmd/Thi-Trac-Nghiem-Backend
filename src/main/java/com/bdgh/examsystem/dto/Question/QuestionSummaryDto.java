package com.bdgh.examsystem.dto.Question;

import com.bdgh.examsystem.dto.Exam.ExamDetailsDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionSummaryDto {
    Long id;
    String cauHoi;
    String dapAn1;
    String dapAn2;
    String dapAn3;
    String dapAn4;
    Long dapAnDung;
}
