package com.bdgh.examsystem.controller;

import com.bdgh.examsystem.dto.Question.QuestionDetailsDto;
import com.bdgh.examsystem.dto.Question.QuestionSummaryDto;
import com.bdgh.examsystem.entity.ResponseObject;
import com.bdgh.examsystem.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/all/{id}")
    public ResponseEntity<ResponseObject> getAllQuestions(@PathVariable("id") Long id) {
        // Thêm kiểm tra nếu không có exam với id
        List<QuestionSummaryDto> questions = questionService.findAllByExamId(id);
        if (questions == null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", "Không tồn tại kì thi: " + id, null)
            );
        }
        if(questions.size() == 0){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", "Không tìm thấy câu hỏi nào trong kì thi: " + id, null)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Tìm danh sách câu hỏi thành công", questions)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getQuestionById(@PathVariable("id") Long id) {
        QuestionDetailsDto questionDetailsDto = questionService.findById(id);
        if(questionDetailsDto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("error", "Không có câu hỏi với id: " + id, null)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Tìm câu hỏi thành công", questionDetailsDto)
        );
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createQuestion(@RequestBody QuestionDetailsDto questionDetailsDTO) {
        if(questionDetailsDTO.getExam().getId() == null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", "Câu hỏi cần thuộc một kì thi nào đó", null)
            );
        }
        QuestionDetailsDto questionDetailsDto1 = questionService.save(questionDetailsDTO);
        if(questionDetailsDto1 == null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", "Tạo câu hỏi không thành công", null)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Tạo câu hỏi thành công", questionDetailsDto1)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateQuestion(@PathVariable("id") Long id, @RequestBody QuestionDetailsDto updatedQuestionDetailsDto) {
        QuestionDetailsDto questionDetailsDto = questionService.findById(id);
        if (questionDetailsDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("error", "Không tìm thấy câu hỏi với ID: " + id, null)
            );
        }
        updatedQuestionDetailsDto.setId(id);
        QuestionDetailsDto updatedQuestion = questionService.save(updatedQuestionDetailsDto);
        if (updatedQuestion == null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", "Cập nhật không thành công", null)
            );
        }
        return ResponseEntity.ok(
                new ResponseObject("ok", "Cập nhật thành công", updatedQuestion)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteQuestion(@PathVariable("id") Long id) {
        QuestionDetailsDto questionDetailsDto = questionService.findById(id);
        // Không có = đã xoá
//        if(questionDto == null){
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("error", "Không có câu hỏi có id là:" + id, null)
//            );
//        }
        if (questionDetailsDto != null) {
            questionService.deleteById(id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Xóa thành công", null)
        );
    }
}