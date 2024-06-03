package com.racconworld.domain.quiz;

import com.racconworld.domain.quizchoice.QuizChoice;
import com.racconworld.domain.quizquestion.QuizQuestion;
import com.racconworld.domain.test.Test;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ShowQuizDto {

    private String testName;            //테스트이름
    private String filepath;
    private Long views;                 //조회수
    private int question_count;        //질문 개수
    private List<ShowQuizQuestionDto> quizQuestions;     //퀴즈 질문 EX)1번 친구만나는데 지갑을 두고온 나는 ?


    @Data
    @Builder
    @Valid
    public static class ShowQuizQuestionDto{
        private String quiz_question;
        private List<QuizChoice> choices;
    }

    public static ShowQuizDto toDto(Test test , List<QuizQuestion> questions){
        return ShowQuizDto.builder()
                .testName(test.getTestName())
                .filepath(test.getFilepath())
                .views(test.getViews())
                .question_count(test.getQuestion_count())
                .quizQuestions(questions.stream()
                        .map(question -> ShowQuizDto.ShowQuizQuestionDto.builder()
                                .quiz_question(question.getQuiz_question())
                                .choices(question.getChoices())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

}
