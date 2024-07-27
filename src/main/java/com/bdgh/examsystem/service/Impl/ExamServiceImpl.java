package com.bdgh.examsystem.service.Impl;

import com.bdgh.examsystem.Exception.NoContentException;
import com.bdgh.examsystem.Exception.NotFoundException;
import com.bdgh.examsystem.dto.Exam.ExamDetailsDto;
import com.bdgh.examsystem.dto.Exam.ExamOverviewDto;
import com.bdgh.examsystem.dto.Exam.ExamSummaryDto;
import com.bdgh.examsystem.entity.*;
import com.bdgh.examsystem.repository.ExamRepository;
import com.bdgh.examsystem.repository.ResultRepository;
import com.bdgh.examsystem.repository.StudentRepository;
import com.bdgh.examsystem.repository.TeacherRepository;
import com.bdgh.examsystem.service.ConvertToDtoService;
import com.bdgh.examsystem.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExamServiceImpl implements ExamService {
    @Override
    public ExamOverviewDto getOverview(Long id) {
        Exam exam = examRepository.findById(id).orElse(null);
        if (exam == null) throw new NotFoundException("Không tìm thấy kì thi với id: " + id);
        long diemCaoNhat = 0;
        long diemThapNhat = 10000;
        long diemTrungBinh = 0;
        List<Result> resultList = resultRepository.findByExamId(id);
        if (resultList.isEmpty()) throw new NoContentException("Kì thi không có bài làm nào");
        Set<Long> studentIdSet = new HashSet<>();
        long soCauHoi = exam.getQuestionList().size();
        if(soCauHoi == 0) throw new NoContentException("Kì thi không có câu hỏi nào");
        for (Result r : resultList) {
            if (r.getSoCauDung() > diemCaoNhat) diemCaoNhat = r.getSoCauDung();
            if (r.getSoCauDung() < diemThapNhat) diemThapNhat = r.getSoCauDung();
            diemTrungBinh += r.getSoCauDung();
            studentIdSet.add(r.getStudent().getId());
        }
        long soNguoiThamGia = studentIdSet.size();
        if(soNguoiThamGia == 0) throw new NoContentException("Kì thi không có sinh viên nào");
        long soLuotLamBai = resultList.size();
        if(soLuotLamBai == 0) throw new NoContentException("Kì thi không có lượt làm bài nào");
        return ExamOverviewDto.builder()
                .soNguoiThamGia(soNguoiThamGia)
                .soCauHoi(soCauHoi)
                .soLuotLamBai(soLuotLamBai)
                .diemCaoNhat((10.0/soCauHoi) * diemCaoNhat)
                .diemThapNhat((10.0/soCauHoi) * diemThapNhat)
                .diemTrungBinh((10.0/soCauHoi) * diemTrungBinh/soLuotLamBai)
                .build();
    }

    @Override
    public List<ExamSummaryDto> findALL() {
        return convertToDtoService.toExamSummaryDtoList(examRepository.findAll());
    }

//    @Override
//    public ExamDto findById(Long id) {
//        Exam exam = examRepository.findById(id).orElse(null);
//        if(exam == null){
//            return null;
//        }
//        return convertToDtoService.convertExamToDto(exam);
//    }


    @Override
    public ExamDetailsDto findById(Long id) {
        Exam exam = examRepository.findById(id).orElse(null);
        if(exam == null){
            return null;
        }
        return convertToDtoService.toExamDetailsDto(exam);
    }



    @Override
    public ExamSummaryDto save(ExamDetailsDto examDetailsDTO) {
        Teacher teacher = teacherRepository.findById(examDetailsDTO.getTeacher().getId()).orElse(null);
        if(teacher == null){
            return null;
        }
        Exam exam = Exam.builder()
                .id(examDetailsDTO.getId())
                .ten(examDetailsDTO.getTen())
                .gioKetThuc(examDetailsDTO.getGioKetThuc())
                .ngayBatDau(examDetailsDTO.getNgayBatDau())
                .gioBatDau(examDetailsDTO.getGioBatDau())
                .ngayKetThuc(examDetailsDTO.getNgayKetThuc())
                .examType(examDetailsDTO.getExamType())
                .password(examDetailsDTO.getPassword())
                .teacher(teacher)
                .build();
        return convertToDtoService.toExamSummaryDto(examRepository.save(exam));
    }

    @Override
    public void deleteById(Long id) {
        examRepository.deleteById(id);
    }

    @Override
    public Page<ExamSummaryDto> filterExam(ExamType type, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Exam> examList = examRepository.findByExamType(type, pageable);
        if (examList.isEmpty()) throw new NoContentException("Không tìm thấy kì thi nào");
        return convertToDtoService.toExamSummaryDtoPage(examList);
    }
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ConvertToDtoService convertToDtoService;

}