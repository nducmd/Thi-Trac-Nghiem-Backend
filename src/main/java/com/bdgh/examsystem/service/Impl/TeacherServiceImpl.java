package com.bdgh.examsystem.service.Impl;

import com.bdgh.examsystem.Exception.NoContentException;
import com.bdgh.examsystem.Exception.NotFoundException;
import com.bdgh.examsystem.dto.Teacher.TeacherDetailsDto;
import com.bdgh.examsystem.dto.Teacher.TeacherSummaryDto;
import com.bdgh.examsystem.entity.Student;
import com.bdgh.examsystem.entity.Teacher;
import com.bdgh.examsystem.repository.TeacherRepository;
import com.bdgh.examsystem.service.ConvertToDtoService;
import com.bdgh.examsystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ConvertToDtoService convertToDtoService;
    @Override
    public TeacherSummaryDto editTeacher(Long id, TeacherDetailsDto teacherDetailsDto) {
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        if (teacher == null) throw new NotFoundException("Không tồn tại giáo viên với id: " + id);
        teacher.setHo(teacherDetailsDto.getHo());
        teacher.setTen(teacherDetailsDto.getTen());
        teacher.setDob(LocalDate.parse(teacherDetailsDto.getDob()));
        teacher.setTitle(teacherDetailsDto.getTitle());
        return convertToDtoService.toTeacherSummaryDto(teacherRepository.save(teacher));
    }

    @Override
    public Page<TeacherSummaryDto> getPageTeacherSummary(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Teacher> teacherPage = teacherRepository.findAll(pageable);
        if (teacherPage.isEmpty()) throw new NoContentException("Không tìm thấy giáo viên nào");
        return convertToDtoService.toTeacherSummaryPage(teacherPage);
    }

    @Override
    public Teacher findById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }
}
