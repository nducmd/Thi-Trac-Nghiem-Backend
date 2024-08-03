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
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String ho;
    String ten;
    String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate dob;
    @OneToOne
    User user;
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.REMOVE)
    List<Exam> examList;
}
