package com.racconworld.domain.upload.text;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Data
public class QuizUploadDto {

    @NotBlank
    private String quiz_question;
    @NotBlank
    private List<uploadQuizChoiceDto> choices;

    public QuizUploadDto(String quiz_question, List<uploadQuizChoiceDto> choices) {
        this.quiz_question = quiz_question;
        this.choices = choices;
    }

    @Data
    public static class uploadQuizChoiceDto {
        @NotBlank
        private String choice;
        @NotBlank
        private Integer choice_score;

        public uploadQuizChoiceDto(String choice, Integer choice_score) {
            this.choice = choice;
            this.choice_score = choice_score;
        }
    }
}
