package com.bdgh.examsystem.service.Impl;

import com.bdgh.examsystem.Exception.NoContentException;
import com.bdgh.examsystem.Exception.NotFoundException;
import com.bdgh.examsystem.dto.Student.StudentDetailsDto;
import com.bdgh.examsystem.dto.Student.StudentSummaryDto;
import com.bdgh.examsystem.entity.Student;
import com.bdgh.examsystem.entity.Teacher;
import com.bdgh.examsystem.repository.StudentRepository;
import com.bdgh.examsystem.service.ConvertToDtoService;
import com.bdgh.examsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ConvertToDtoService toDtoService;

    @Override
    public StudentSummaryDto editStudent(Long id, StudentDetailsDto studentDetailsDto) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) throw new NotFoundException("Không có sinh viên với id: " + id);
        student.setHo(studentDetailsDto.getHo());
        student.setTen(studentDetailsDto.getTen());
        student.setDob(studentDetailsDto.getDob());
        student.setLop(studentDetailsDto.getLop());
        student.setMsv(studentDetailsDto.getMsv());
        return toDtoService.toStudentSummaryDto(studentRepository.save(student));
    }

    @Override
    public Page<StudentSummaryDto> getPageStudentSummary(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        if (studentPage.isEmpty()) throw new NoContentException("Không tìm thấy sinh viên nào");
        return toDtoService.toStudentSummaryPage(studentPage);
    }

    @Override
    public Page<StudentSummaryDto> getStudentByKeyword(String keyword, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.searchByKeyword(keyword, pageable);
        if (studentPage.isEmpty()) throw new NoContentException("Không tìm thấy sinh viên nào");
        return toDtoService.toStudentSummaryPage(studentPage);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
}
