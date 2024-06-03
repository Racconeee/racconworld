package com.racconworld.domain.quizquestion;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ShowQuizQuestionDto {

    private String quiz_question;
    public String getQuiz_question() {
        return quiz_question;
    }

}
