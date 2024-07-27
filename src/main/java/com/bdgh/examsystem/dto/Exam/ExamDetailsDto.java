
package com.bdgh.examsystem.dto.Exam;

import com.bdgh.examsystem.dto.Question.QuestionDetailsDto;
import com.bdgh.examsystem.dto.Question.QuestionSummaryDto;
import com.bdgh.examsystem.dto.Result.ResultDetailsDto;
import com.bdgh.examsystem.dto.Result.ResultSummaryDto;
import com.bdgh.examsystem.dto.Teacher.TeacherBasicDto;
import com.bdgh.examsystem.dto.Teacher.TeacherDetailsDto;
import com.bdgh.examsystem.dto.Teacher.TeacherSummaryDto;
import com.bdgh.examsystem.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamDetailsDto {
    private Long id;
    private String ten;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngayBatDau;
    private String gioBatDau;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngayKetThuc;
    private String password;
    private String gioKetThuc;
    private ExamType examType;
    private TeacherBasicDto teacher;
    private List<ResultSummaryDto> resultList;
    private List<QuestionSummaryDto> questionList;
}

