package com.bdgh.examsystem.controller;

import com.bdgh.examsystem.Exception.NoContentException;
import com.bdgh.examsystem.Exception.NotFoundException;
import com.bdgh.examsystem.dto.Result.ResultDetailsDto;
import com.bdgh.examsystem.dto.Student.StudentDetailsDto;
import com.bdgh.examsystem.dto.Student.StudentSummaryDto;
import com.bdgh.examsystem.dto.Teacher.TeacherDetailsDto;
import com.bdgh.examsystem.dto.UserDto;
import com.bdgh.examsystem.entity.ResponseObject;
import com.bdgh.examsystem.entity.Student;
import com.bdgh.examsystem.entity.User;
import com.bdgh.examsystem.service.StudentService;
import com.bdgh.examsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/find")
    ResponseEntity<ResponseObject> find(
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page) {
        try{
            Page<StudentSummaryDto> summaryDtoPage  = studentService.getStudentByKeyword(keyword, page);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Tìm sinh viên thành công", summaryDtoPage)
            );
        } catch (NoContentException ex) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", ex.getMessage(), null)
            );
        }
    }

    @GetMapping("/all")
    ResponseEntity<ResponseObject> getAllStudent(
            @RequestParam(value = "page", defaultValue = "0") int page) {
        try{
            Page<StudentSummaryDto> summaryDtoPage  =
                    studentService.getPageStudentSummary(page, 10);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Lấy danh sách sinh viên thành công", summaryDtoPage)
            );
        } catch (NoContentException ex) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", ex.getMessage(), null)
            );
        }
    }
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> editTeacher(@RequestBody StudentDetailsDto studentDetailsDto,
                                               @PathVariable Long id) {
        try{
            StudentSummaryDto studentSummaryDto = studentService.editStudent(id, studentDetailsDto);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Sửa thông tin sinh viên thành công", studentSummaryDto)
            );
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", ex.getMessage(), null)
            );
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteStudent(@PathVariable("id") Long id) {
        Student student = studentService.findById(id);
        if(student != null){
            studentService.delete(id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Xoá sinh viên thành công", null)
        );
    }

}
