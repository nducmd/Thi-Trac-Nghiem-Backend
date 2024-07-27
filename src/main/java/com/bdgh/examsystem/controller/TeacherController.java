package com.bdgh.examsystem.controller;

import com.bdgh.examsystem.Exception.NoContentException;
import com.bdgh.examsystem.Exception.NotFoundException;
import com.bdgh.examsystem.dto.Student.StudentSummaryDto;
import com.bdgh.examsystem.dto.Teacher.TeacherDetailsDto;
import com.bdgh.examsystem.dto.Teacher.TeacherSummaryDto;
import com.bdgh.examsystem.entity.ResponseObject;
import com.bdgh.examsystem.entity.Student;
import com.bdgh.examsystem.entity.Teacher;
import com.bdgh.examsystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherController {
      @Autowired
      private TeacherService teacherService;

      @GetMapping("/all")
      ResponseEntity<ResponseObject> getAllTeacher(
              @RequestParam(value = "page", defaultValue = "0") int page) {
            try{
                  Page<TeacherSummaryDto> summaryDtoPage  =
                          teacherService.getPageTeacherSummary(page, 10);
                  return ResponseEntity.status(HttpStatus.OK).body(
                          new ResponseObject("ok", "Lấy danh sách giáo viên thành công", summaryDtoPage)
                  );
            } catch (NoContentException ex) {
                  return ResponseEntity.status(HttpStatus.OK).body(
                          new ResponseObject("error", ex.getMessage(), null)
                  );
            }
      }

      @PutMapping("/{id}")
      ResponseEntity<ResponseObject> editTeacher(@RequestBody TeacherDetailsDto teacherDetailsDto,
                                                 @PathVariable Long id) {
            try {
                  TeacherSummaryDto teacherSummaryDto = teacherService.editTeacher(id, teacherDetailsDto);
                  return ResponseEntity.status(HttpStatus.OK).body(
                          new ResponseObject("ok", "Sửa thông tin giáo viên thành công", teacherSummaryDto)
                  );
            } catch (NotFoundException ex) {
                  return ResponseEntity.status(HttpStatus.OK).body(
                          new ResponseObject("error", ex.getMessage(), null)
                  );
            }
      }

      @DeleteMapping("/{id}")
      public ResponseEntity<ResponseObject> deleteTeacher(@PathVariable("id") Long id) {
            Teacher teacher = teacherService.findById(id);
            if(teacher != null){
                  teacherService.delete(id);
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Xoá sinh viên thành công", null)
            );
      }

}
