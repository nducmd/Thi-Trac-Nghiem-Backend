package com.bdgh.examsystem.dto.Exam;

import com.bdgh.examsystem.dto.Teacher.TeacherBasicDto;
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
public class ExamSummaryDto {
    private Long id;
    private String ten;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngayBatDau;
    private String gioBatDau;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngayKetThuc;
    private String password;
    private String gioKetThuc;
    private ExamType examType;
    private TeacherBasicDto teacher;
}
