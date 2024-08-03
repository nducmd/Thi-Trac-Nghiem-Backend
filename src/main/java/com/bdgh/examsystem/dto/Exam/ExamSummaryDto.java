package com.bdgh.examsystem.dto.Exam;

import com.bdgh.examsystem.dto.Teacher.TeacherBasicDto;
import com.bdgh.examsystem.entity.ExamType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExamSummaryDto {
    Long id;
    String ten;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate ngayBatDau;
    String gioBatDau;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate ngayKetThuc;
    String password;
    String gioKetThuc;
    ExamType examType;
    TeacherBasicDto teacher;
}
