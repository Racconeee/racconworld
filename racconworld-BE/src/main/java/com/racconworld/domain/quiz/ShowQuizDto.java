package com.racconworld.domain.quiz;

import com.racconworld.domain.quizchoice.QuizChoice;
import com.racconworld.domain.quizquestion.QuizQuestion;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ShowQuizDto {

    private String testName;            //테스트이름
    private Long views;                 //조회수
    private int question_count;        //질문 개수
    private List<ShowQuizQuestionDto> quizQuestions;     //퀴즈 질문 EX)1번 친구만나는데 지갑을 두고온 나는 ?


    @Data
    @Builder
    public static class ShowQuizQuestionDto{
        private String quiz_question;
        private List<QuizChoice> choices;
    }
}
