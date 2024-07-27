package com.bdgh.examsystem.dto.Student;

import com.bdgh.examsystem.dto.Result.ResultSummaryDto;
import com.bdgh.examsystem.dto.UserDto;
import com.bdgh.examsystem.entity.Result;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentDetailsDto {
    private Long id;
    private String ho;
    private String ten;
    private String msv;
    private String lop;
    private double diemTrungBinh;
    private LocalDate dob;
    private List<ResultSummaryDto> results;
}
