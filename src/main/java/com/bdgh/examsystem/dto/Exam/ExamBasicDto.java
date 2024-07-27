package com.bdgh.examsystem.dto.Exam;

import com.bdgh.examsystem.entity.ExamType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamBasicDto {
    private Long id;
    private String ten;
    private ExamType examType;
}
