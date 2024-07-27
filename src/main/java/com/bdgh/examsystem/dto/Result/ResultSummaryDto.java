package com.bdgh.examsystem.dto.Result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResultSummaryDto {
    private Long id;
    private Long soCauDung;
    private String batDau;
    private String nopBai;
    private String studentAnswer;
}
