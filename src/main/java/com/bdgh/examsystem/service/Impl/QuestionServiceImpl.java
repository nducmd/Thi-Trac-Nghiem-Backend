package com.bdgh.examsystem.service.Impl;

import com.bdgh.examsystem.dto.Question.QuestionDetailsDto;
import com.bdgh.examsystem.dto.Question.QuestionSummaryDto;
import com.bdgh.examsystem.entity.Exam;
import com.bdgh.examsystem.entity.Question;
import com.bdgh.examsystem.repository.ExamRepository;
import com.bdgh.examsystem.repository.QuestionRepository;
import com.bdgh.examsystem.service.ConvertToDtoService;
import com.bdgh.examsystem.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ConvertToDtoService convertToDtoService;

    @Override
    public List<QuestionDetailsDto> findALL() {
        return convertToDtoService.convertQuestionListToDto(questionRepository.findAll());
    }

    @Override
    public List<QuestionSummaryDto> findAllByExamId(Long id) {
        Exam exam = examRepository.findById(id).orElse(null);
        if(exam == null){
            return  null;
        }
        return convertToDtoService.toQuestionSummaryDtoList(exam.getQuestionList());
    }

    @Override
    public QuestionDetailsDto findById(Long id) {
        Question question = questionRepository.findById(id).orElse(null);
        if(question == null){
            return null;
        }
        return convertToDtoService.convertQuestionToDto(question);
    }

    @Override
    public QuestionDetailsDto save(QuestionDetailsDto questionDetailsDTO) {
        Exam exam = examRepository.findById(questionDetailsDTO.getExam().getId()).orElse(null);
        if(exam == null){
            return null;
        }
        Question question = Question.builder()
                .id(questionDetailsDTO.getId())
                .cauHoi(questionDetailsDTO.getCauHoi())
                .dapAn1(questionDetailsDTO.getDapAn1())
                .dapAn2(questionDetailsDTO.getDapAn2())
                .dapAn3(questionDetailsDTO.getDapAn3())
                .dapAn4(questionDetailsDTO.getDapAn4())
                .dapAnDung(questionDetailsDTO.getDapAnDung())
                .exam(exam)
                .build();
        return convertToDtoService.convertQuestionToDto(questionRepository.save(question));
    }

    @Override
    public void deleteById(Long id) {
        questionRepository.deleteById(id);
    }
}
