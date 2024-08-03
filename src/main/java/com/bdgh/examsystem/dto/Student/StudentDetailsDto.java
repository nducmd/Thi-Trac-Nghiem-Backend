package com.bdgh.examsystem.dto.Student;

import com.bdgh.examsystem.dto.Result.ResultSummaryDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentDetailsDto {
    Long id;
    String ho;
    String ten;
    String msv;
    String lop;
    double diemTrungBinh;
    LocalDate dob;
    List<ResultSummaryDto> results;
}
