package com.bdgh.examsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cauHoi;
    private String dapAn1;
    private String dapAn2;
    private String dapAn3;
    private String dapAn4;
    private Long dapAnDung;
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;
}
