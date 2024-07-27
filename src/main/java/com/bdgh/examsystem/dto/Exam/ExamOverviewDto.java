package com.bdgh.examsystem.dto.Exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamOverviewDto {
    private Long soCauHoi;
    private Long soNguoiThamGia;
    private Long soLuotLamBai;
    private Double diemCaoNhat;
    private Double diemThapNhat;
    private Double diemTrungBinh;
}
