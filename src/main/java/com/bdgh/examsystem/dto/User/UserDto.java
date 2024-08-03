package com.bdgh.examsystem.dto.User;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    Long id;
    String email;
    String password;
    String ho;
    String ten;
    String title;
    String msv;
    String lop;
    String role;
    LocalDate dob;
}
