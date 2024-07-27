package com.bdgh.examsystem.controller;
import com.bdgh.examsystem.Exception.NoContentException;
import com.bdgh.examsystem.Exception.NotFoundException;
import com.bdgh.examsystem.dto.Exam.ExamDetailsDto;
import com.bdgh.examsystem.dto.Exam.ExamOverviewDto;
import com.bdgh.examsystem.dto.Exam.ExamSummaryDto;
import com.bdgh.examsystem.entity.ExamType;
import com.bdgh.examsystem.entity.ResponseObject;
import com.bdgh.examsystem.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/exams")
public class ExamController {

    @GetMapping("/statistic/{id}")
    public ResponseEntity<ResponseObject> getOverview(
            @PathVariable("id") Long id) {
        try {
            ExamOverviewDto examOverviewDto = examService.getOverview(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Lấy thống kê kì thi thành công", examOverviewDto)
            );
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", ex.getMessage(), null)
            );
        } catch (NoContentException ex) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", ex.getMessage(), null)
            );
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<ResponseObject> filterExam(
            @RequestParam(value = "type", defaultValue = "CUOIKY") ExamType type,
            @RequestParam(value = "page", defaultValue = "0") int page) {
        try{
            Page<ExamSummaryDto> examSummaryDtoList = examService.filterExam(type, page);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Lấy danh sách kì thi thành công", examSummaryDtoList)
            );
        } catch (NoContentException ex) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", ex.getMessage(), null)
            );
        }
    }
    @GetMapping("/all")
    public ResponseEntity<ResponseObject> getAllExams() {
        List<ExamSummaryDto> exams = examService.findALL();
        if(exams.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("error", "Không tìm thấy kì thi nào", null)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Tạo thành công", exams)
        );
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ResponseObject> getExamById(@PathVariable("id") Long id) {
//        ExamDto examDto = examService.findById(id);
//        if(examDto == null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("error", "Không thấy examDTO co id: " + id, null)
//            );
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("ok", "Tạo thành công", examDto)
//        );
//    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getExamById(@PathVariable("id") Long id) {
        ExamDetailsDto examDetailsDto = examService.findById(id);
        if(examDetailsDto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("error", "Không thấy kì thi có id: " + id, null)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Tìm kì thi thành công", examDetailsDto)
        );
    }


    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createExam(@RequestBody ExamDetailsDto examDetailsDTO) {
        if(examDetailsDTO.getTeacher().getId() == null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", "Tạo kì thi mới không thành công", null)
            );
        }
        ExamSummaryDto examSummaryDto = examService.save(examDetailsDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Tạo kì thi mới thành công", examSummaryDto)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateExam(@PathVariable("id") Long id, @RequestBody ExamDetailsDto updatedExamDetailsDto) {
        ExamDetailsDto examDetailsDto = examService.findById(id);
        if (examDetailsDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("error", "Không tìm thấy kì thi với ID: " + id, null)
            );
        }
        updatedExamDetailsDto.setId(id);
        ExamSummaryDto updatedExam = examService.save(updatedExamDetailsDto);
        if (updatedExam == null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", "Cập nhật không thành công", null)
            );
        }
        return ResponseEntity.ok(
                new ResponseObject("ok", "Cập nhật thành công", updatedExam)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteExam(@PathVariable("id") Long id) {
        ExamDetailsDto examDetailsDto = examService.findById(id);
        // Không có tức là đã xoá rồi
//        if(examDto == null){
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("error", " co exam co id la:" + id, null)
//            );
//        }
        if(examDetailsDto != null){
            examService.deleteById(id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Xoá kì thi thành công", null)
        );
    }
    @Autowired
    private ExamService examService;

    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }
}