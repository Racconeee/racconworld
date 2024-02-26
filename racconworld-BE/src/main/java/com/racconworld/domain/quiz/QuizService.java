package com.racconworld.domain.quiz;

import com.racconworld.domain.admin.Admin;
import com.racconworld.domain.admin.AdminRepository;
import com.racconworld.domain.quizchoice.QuizChoice;
import com.racconworld.domain.quizchoice.QuizChoiceRepository;
import com.racconworld.domain.quizquestion.QuizQuestion;
import com.racconworld.domain.quizquestion.QuizQuestionRepository;
import com.racconworld.domain.test.Test;
import com.racconworld.domain.test.TestRepository;
import com.racconworld.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizService {


    private final TestRepository testRepository;
    private final QuizQuestionRepository quizQuestionRepository;
    private final QuizChoiceRepository quizChoiceRepository;

    //ToMany를 연속 2개를 하나의 쿼리로 가져올 수 없어서
    //Test qeury , Question + Choice Query로 2개로 쿼리를 나누어서 가져옴
    //사용시기 : 테스트 클릭후 테스트를 진행할때 전체적인 정보 얻음

    public ShowQuizDto show_quiz(Long id) throws Exception {
        Test test = find_test(id); //repository로 가져와서 조인패치로 성능 최적화 해보자
        List<QuizQuestion> questions = quizQuestionRepository.findQuestionsWithChoicesByTestId(test.getId());
//
        //빌더 자체를 Dto안에 넣음
//        List<ShowQuizDto.ShowQuizQuestionDto> quizQuestionDtos = questions.stream()
//                .map(question -> ShowQuizDto.ShowQuizQuestionDto.builder()
//                        .quiz_question(question.getQuiz_question())
//                        .choices(question.getChoices())
//                        .build())
//                .collect(Collectors.toList());
//
//
//        ShowQuizDto dto = ShowQuizDto.builder()
//                .testName(test.getTestName())
//                .filepath(test.getFilepath())
//                .views(test.getViews())
//                .question_count(test.getQuestion_count())
//                .quizQuestions(quizQuestionDtos)
//                .build();

        return ShowQuizDto.toDto(test, questions);

//    }
//        return dto;


    }

    private Test find_test(Long id) {
        Optional<Test> byId = testRepository.findById(id);
        Test test_id = byId.orElseThrow(() -> new CustomException("현재 존재하지않는 테스트입니다."));
        return test_id;
    }
}
