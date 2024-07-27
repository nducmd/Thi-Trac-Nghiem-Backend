package com.bdgh.examsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ho;
    private String ten;
    private String msv;
    private String lop;
    private double diemTrungBinh;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    @OneToOne
    private User user;
    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Result> results;
}
