package com.bdgh.examsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String ho;
    private String ten;
    private String title;
    private String msv;
    private String lop;
    private String role;
    private LocalDate dob;
}
