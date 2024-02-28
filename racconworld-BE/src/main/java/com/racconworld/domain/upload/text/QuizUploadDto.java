package com.racconworld.domain.upload.text;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Data
@Builder
public class QuizUploadDto {

    private String quiz_question;
    private List<uploadQuizChoiceDto> choices;

    @Data
    @Builder
    public static class uploadQuizChoiceDto{
        private String choice;
        private Integer choice_score;
    }


}
