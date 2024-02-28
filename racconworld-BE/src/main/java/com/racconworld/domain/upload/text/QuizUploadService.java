package com.racconworld.domain.upload.text;


import com.racconworld.domain.quizchoice.QuizChoice;
import com.racconworld.domain.quizchoice.QuizChoiceRepository;
import com.racconworld.domain.quizquestion.QuizQuestion;
import com.racconworld.domain.quizquestion.QuizQuestionRepository;
import com.racconworld.domain.test.Test;
import com.racconworld.domain.test.TestRepository;
import com.racconworld.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizUploadService {

    private final QuizQuestionRepository quizQuestionRepository;
    private final QuizChoiceRepository quizChoiceRepository;
    private final TestRepository testRepository;

    public String quiz_upload(List<QuizUploadDto> dtos ,Long test_id){

        Test byId = testRepository.findById(test_id).orElseThrow(() -> new CustomException("해당하는 test가 존재하지 않습니다."));

        for (QuizUploadDto dto : dtos) {
            // QuizQuestion 생성 및 저장
            QuizQuestion quizQuestion = new QuizQuestion( byId , dto.getQuiz_question());

            byId.addQuiz_question(quizQuestion);
            quizQuestionRepository.save(quizQuestion);

            for (QuizUploadDto.uploadQuizChoiceDto choiceDto : dto.getChoices()) {
                // QuizChoice 생성 및 저장
                QuizChoice quizChoice = new QuizChoice(quizQuestion, choiceDto.getChoice(), choiceDto.getChoice_score());

                // QuizQuestion과 QuizChoice의 양방향 연관관계 설정
                quizQuestion.addChoice(quizChoice);

                quizChoiceRepository.save(quizChoice);
            }
        }

        return "Quiz 질문과 선택지가 성공적으로 저장 되었습니다.";
    }

}
