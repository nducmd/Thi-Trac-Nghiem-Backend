package com.bdgh.examsystem.dto.Student;

import com.bdgh.examsystem.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentBasicDto {
    private Long id;
    private String ho;
    private String ten;
    private String msv;
}
