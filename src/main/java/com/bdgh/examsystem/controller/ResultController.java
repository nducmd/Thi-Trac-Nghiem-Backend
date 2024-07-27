package com.bdgh.examsystem.controller;

import com.bdgh.examsystem.Exception.NotFoundException;
import com.bdgh.examsystem.dto.Result.ResultDetailsDto;
import com.bdgh.examsystem.dto.Result.ResultSummaryDto;
import com.bdgh.examsystem.entity.ResponseObject;
import com.bdgh.examsystem.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/result")
public class ResultController {
    @Autowired
    private ResultService resultService;

// Sửa result
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> editResult(@RequestBody ResultDetailsDto resultDetailsDto, @PathVariable Long id){
        ResultDetailsDto resultDetailsDto1 = resultService.editResult(id, resultDetailsDto);
        if(resultDetailsDto1 == null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", "Không tồn tại bài làm", null)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Sửa bài làm thành công", resultDetailsDto1)
        );
    }

    // THêm result
    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createResult (@RequestBody ResultDetailsDto resultDetailsDto){
        try {
            ResultDetailsDto resultDetailsDto1 = resultService.createResult(resultDetailsDto);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Nộp bài thành công", resultDetailsDto1)
            );
        }catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", ex.getMessage(), null)
            );
        }

    }

    @GetMapping("/all")
    public ResponseEntity<ResponseObject> getAllResult(){
        List<ResultSummaryDto> resultDetailsDtoList = resultService.findAllResult();
        if(resultDetailsDtoList.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("error", "Không có kết quả làm bài nào", null)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Lấy danh sách bài làm thành công", resultDetailsDtoList)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getResultById(@PathVariable("id") Long id) {
        ResultDetailsDto resultDetailsDto = resultService.findById(id);
        if(resultDetailsDto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("error", "Không thấy kết quả có id: " + id, null)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Tìm thấy kết quả có id: " + id, resultDetailsDto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteResult(@PathVariable("id") Long id) {
        ResultDetailsDto resultDetailsDto = resultService.findById(id);
        if(resultDetailsDto != null){
            resultService.deleteResultById(id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Xoá thành công", null)
        );
    }

    @GetMapping("/exam/{examId}")
    public ResponseEntity<ResponseObject> getResultsByExam(@PathVariable Long examId) {
        List<ResultSummaryDto> results = resultService.getResultsByExamId(examId);
        if (results.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("error", "Không tìm thấy bài làm nào cho bài thi: " + examId, null)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Lấy kết quả làm bài thành công", results)
        );
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<ResponseObject> getResultsByStudent(@PathVariable Long studentId) {
        List<ResultSummaryDto> results = resultService.getResultsByStudentId(studentId);
        if (results.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("error", "Không tìm thấy kết quả nào của sinh viên có id: " + studentId, null)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Lấy danh sách kết quả thành công", results)
        );
    }
}

