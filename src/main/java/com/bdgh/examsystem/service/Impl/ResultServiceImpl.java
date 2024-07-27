package com.bdgh.examsystem.service.Impl;

import com.bdgh.examsystem.Exception.NoContentException;
import com.bdgh.examsystem.Exception.NotFoundException;
import com.bdgh.examsystem.dto.Exam.ExamDetailsDto;
import com.bdgh.examsystem.dto.Question.QuestionDetailsDto;
import com.bdgh.examsystem.dto.Question.QuestionSummaryDto;
import com.bdgh.examsystem.dto.Result.ResultDetailsDto;
import com.bdgh.examsystem.dto.Result.ResultSummaryDto;
import com.bdgh.examsystem.entity.Exam;
import com.bdgh.examsystem.entity.Question;
import com.bdgh.examsystem.entity.Result;
import com.bdgh.examsystem.entity.Student;
import com.bdgh.examsystem.repository.ExamRepository;
import com.bdgh.examsystem.repository.ResultRepository;
import com.bdgh.examsystem.repository.StudentRepository;
import com.bdgh.examsystem.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private ConvertToDtoServiceImpl convertToDtoService;
    @Override
    public ResultDetailsDto editResult(Long id, ResultDetailsDto resultDetailsDto) {
        Result result = resultRepository.findById(id).orElse(null);
        if(result == null) return null;
        Student student = result.getStudent();
        Exam exam = result.getExam();
        int socauhoi = exam.getQuestionList().size();
        while (resultDetailsDto.getStudentAnswer().length() < socauhoi) {
            resultDetailsDto.setStudentAnswer(resultDetailsDto.getStudentAnswer() + "F");
        }
        long socaudung = countTrueAns(resultDetailsDto.getStudentAnswer(), exam);
        long socaudungtruoc = countTrueAns(result.getStudentAnswer(), exam);
        double diem = 0;
        if (socauhoi != 0) diem = (10 / socauhoi) * socaudung;
        double diemtruoc = 0;
        if (socauhoi != 0) diemtruoc = (10 / socauhoi) * socaudungtruoc;
        double tongphu = student.getResults().size() * student.getDiemTrungBinh();
        student.setDiemTrungBinh((tongphu + diem - diemtruoc) / (student.getResults().size() + 1));
        studentRepository.save(student);
        result.setSoCauDung(socaudung);
        result.setBatDau(LocalDateTime.parse(resultDetailsDto.getBatDau()));
        result.setNopBai(LocalDateTime.parse(resultDetailsDto.getNopBai()));
        result.setStudentAnswer(resultDetailsDto.getStudentAnswer());
        return convertToDtoService.toResultDetailsDto(resultRepository.save(result));
    }
    @Override
    public ResultDetailsDto createResult(ResultDetailsDto resultDetailsDto) {
        Student student = studentRepository.findById(resultDetailsDto.getStudent().getId()).orElse(null);
        if(student == null){
            throw new NotFoundException("Nộp bài không thành công do không tồn tại sinh viên");
        }
        Exam exam = examRepository.findById(resultDetailsDto.getExam().getId()).orElse(null);
        if (exam == null) {
            throw new NotFoundException("Nộp bài không thành công do không tồn tại kì thi");
        }
        int socauhoi = exam.getQuestionList().size();
        while (resultDetailsDto.getStudentAnswer().length() < socauhoi) {
            resultDetailsDto.setStudentAnswer(resultDetailsDto.getStudentAnswer() + "F");
        }
        long socaudung = countTrueAns(resultDetailsDto.getStudentAnswer(), exam);
        double diem = 0;
        if (socauhoi != 0) diem = (10 / socauhoi) * socaudung;
        double tongphu = student.getResults().size() * student.getDiemTrungBinh();
        student.setDiemTrungBinh((tongphu + diem) / (student.getResults().size() + 1));
        studentRepository.save(student);
        Result result = new Result();
        result.setSoCauDung(socaudung);
        result.setBatDau(LocalDateTime.parse(resultDetailsDto.getBatDau()));
        result.setNopBai(LocalDateTime.parse(resultDetailsDto.getNopBai()));
        result.setStudentAnswer(resultDetailsDto.getStudentAnswer());
        result.setExam(exam);
        result.setStudent(student);
        return convertToDtoService.toResultDetailsDto(resultRepository.save(result));
    }

    @Override
    public List<ResultSummaryDto> findAllResult() {
        List<Result> resultList = resultRepository.findAll();
        return convertToDtoService.toResultSummaryDtoList(resultList);
    }

    @Override
    public ResultDetailsDto findById(Long id) {
        Result result = resultRepository.findById(id).orElse(null);
        if(result == null){
            return null;
        }
        return convertToDtoService.toResultDetailsDto(result);

    }

    @Override
    public void deleteResultById(Long id) {
        resultRepository.deleteById(id);
    }

    @Override
    public void updateSoCauDung(ResultDetailsDto resultDetailsDto, ExamDetailsDto examDetailsDto) {
        long count = 0;
        String studentAnswer = resultDetailsDto.getStudentAnswer();
        char[] studentAnswers = studentAnswer.toCharArray();

        List<QuestionSummaryDto> questions = examDetailsDto.getQuestionList();

        for (int i = 0; i < questions.size(); i++) {
            Long dapAnDungSo = questions.get(i).getDapAnDung();
            char dapAnDungChu = (char) ('A' + dapAnDungSo - 1);
            if (studentAnswers[i] == dapAnDungChu) {
                count++;
            }
        }

        resultDetailsDto.setSoCauDung(count);
    }

    private long countTrueAns(String student, Exam exam) {
        String ans="" ;
        long cnt = 0;
        for (Question q : exam.getQuestionList()) {
            ans += (char)(65 + (q.getDapAnDung() - 1));
        }
        int i = 0;
        while (i<ans.length())
        {
            cnt += (ans.charAt(i) == student.charAt(i)) ? 1 : 0;
            i++;
        }
        return cnt;
    }

    @Override
    public List<ResultSummaryDto> getResultsByExamId(Long examId) {
        List<Result> results = resultRepository.findByExamId(examId);
        return convertToDtoService.toResultSummaryDtoList(results);
    }

    @Override
    public List<ResultSummaryDto> getResultsByStudentId(Long studentId) {
        List<Result> results = resultRepository.findByStudentId(studentId);
        return convertToDtoService.toResultSummaryDtoList(results);
    }
}
