package com.dadingcoding.web.service;

import com.dadingcoding.web.controller.dto.request.AddApplicationRequestDto;
import com.dadingcoding.web.controller.dto.request.AddQuestionRequestDto;
import com.dadingcoding.web.controller.dto.response.AnswerDto;
import com.dadingcoding.web.controller.dto.response.QuestionDto;
import com.dadingcoding.web.domain.Application;
import com.dadingcoding.web.domain.Member;
import com.dadingcoding.web.domain.QnA.Answer;
import com.dadingcoding.web.domain.QnA.Question;
import com.dadingcoding.web.repository.AnswerRepository;
import com.dadingcoding.web.repository.ApplicationRepository;
import com.dadingcoding.web.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service @RequiredArgsConstructor
public class MenteeService {

    private final ApplicationRepository applicationRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public void addApplication(Member member, AddApplicationRequestDto request) {
        Application application = request.toEntity(member);
        applicationRepository.save(application);
    }

    public void addQuestion(Member member, AddQuestionRequestDto request) {
        Question question = request.toEntity(member);
        questionRepository.save(question);
    }

    public List<QuestionDto> findAllQuestions(Long id) {
        List<Question> questions = questionRepository.findAllByMemberId(id);

        List<QuestionDto> dtos = new ArrayList<>();

        for (Question question : questions) {
            dtos.add(QuestionDto.toDto(question));
        }

        return dtos;
    }

    public Question findQuestionById(Long id) {
        return questionRepository.findById(id).get();
    }

    public List<AnswerDto> findAllAnswers(Long questionId) {
        List<Answer> answers = answerRepository.findAllByQuestionId(questionId);

        List<AnswerDto> dtos = new ArrayList<>();

        for (Answer answer : answers) {
            dtos.add(AnswerDto.toDto(answer));
        }

        return dtos;
    }
}