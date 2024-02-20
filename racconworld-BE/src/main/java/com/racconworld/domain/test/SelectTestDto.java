package com.racconworld.domain.test;

import com.racconworld.domain.quizchoice.QuizChoice;
import com.racconworld.domain.quizquestion.QuizQuestion;
import jakarta.persistence.Column;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
public class SelectTestDto {

    private String testName;            //테스트이름
    private int question_count;        //질문 개수
    private String img_url;
    private String filename;
    private String filepath;
    private List<QuizQuestion> questions = new ArrayList<>();
    private List<QuizChoice> choices = new ArrayList<>();

    public static SelectTestDto toDto(Test entity){
        return SelectTestDto.builder()
                .testName(entity.getTestName())
                .question_count(entity.getQuestion_count())
                .filename(entity.getFilename())
                .filepath(entity.getFilepath())
                .questions(entity.getQuestions())
                .build();
    }
}
