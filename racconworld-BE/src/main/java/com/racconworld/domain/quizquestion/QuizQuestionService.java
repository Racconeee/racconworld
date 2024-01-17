package com.racconworld.domain.quizquestion;

import com.racconworld.domain.test.Test;
import com.racconworld.domain.test.TestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizQuestionService {

    private final QuizQuestionRepository quizQuestionRepository;
    private final TestRepository testRepository;


    @Transactional
    public List<ShowQuizQuestionDto> quiz(Long id){
        Optional<Test> testOptional = testRepository.findById(id);

        List<QuizQuestion> questions = testOptional.get().getQuestions();

        List<ShowQuizQuestionDto> dto = questions.stream()
                .map(quizQuestion -> ShowQuizQuestionDto.builder()
                        .quiz_question(quizQuestion.getQuiz_question())
                        // 다른 필드도 필요에 따라 매핑
                        .build())
                .collect(Collectors.toList());

        return dto;
    }

//    public List<ShowQuizQuestion> quiz(Long id) throws Exception {
//        log.info("id : {}", id);
//
//        try {
//            Optional<Test> byId = testRepository.findById(id);
//            log.info("byId : {}",byId);
//
//            List<QuizQuestion> list = quizQuestionRepository.findAllById(id);
//
//
//            log.info("list status : {}", list.isEmpty());
//            log.info("list : {}", list.size());
//            log.info("list 내용 : {}", list.get(0).getQuiz_question());
//            if (!list.isEmpty()) {
//                List<ShowQuizQuestion> dto = new ArrayList<>();
//
//                log.info("선택된 퀘스트의 질문 수  : {}", list.size());
//                for (QuizQuestion question : list) {
//                    dto.add(ShowQuizQuestion.builder()
//                            .quiz_question(question.getQuiz_question())
//                            .build());
//                    log.info("select quiz_Q : {}", question.getQuiz_question());
//                }
//                return dto;
//            }
//        } catch (NullPointerException e) {
//            throw e;
//        }
//        return null;
//    }
}
