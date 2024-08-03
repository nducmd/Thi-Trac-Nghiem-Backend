package com.bdgh.examsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String ho;
    String ten;
    String msv;
    String lop;
    double diemTrungBinh;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate dob;
    @OneToOne
    User user;
    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    List<Result> results;
}
