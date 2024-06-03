package com.racconworld.domain.upload.text;

import com.racconworld.domain.quizchoice.QuizChoice;
import com.racconworld.domain.quizquestion.QuizQuestion;
import com.racconworld.domain.quizquestion.QuizQuestionRepository;
import com.racconworld.domain.test.TestRepository;
import com.racconworld.global.exception.CustomException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class QuizUploadServiceTest {

    @Autowired
    private static final Logger log = LoggerFactory.getLogger(QuizUploadServiceTest.class);

    @Autowired
    private QuizUploadService quizUploadService;

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    @Autowired
    private TestRepository testRepository;

    @BeforeEach
    public void setUp() {
        testRepository.deleteAll();
    }

    //QuizUploadService.quiz_upload() 에서의 Test의 ID값이 만약 있는 값이라면 정상적으로 실행
    @Test
    public void quiz_upload_Test_정상저장() {

        // Given
        com.racconworld.domain.test.Test test = new com.racconworld.domain.test.Test(); // 테스트에 필요한 Test entity를 생성하여 저장
        com.racconworld.domain.test.Test test2 = new com.racconworld.domain.test.Test();
        // When

        testRepository.save(test);
        testRepository.save(test2);

        // Then
        log.info("현재 저장되어있는 Test의 수 : {}", testRepository.findAll().size());
        assertEquals(testRepository.findAll().size() , 2);

    }

    //QuizUploadService.quiz_upload() 에서의 Test의 ID값이 만약 없는 값이라면 예외 발생
    @Test
    public void quiz_upload_예외터지는지_확인 () {

        // Given
        Long non_existent = 999L;
        // Then
        assertThrows(CustomException.class, () -> {
            testRepository.findById(non_existent)
                    .orElseThrow(() -> new CustomException("해당하는 test가 존재하지 않습니다."));
        });
    }
    @Test
    public void quiz_upload() {

        // Given
        com.racconworld.domain.test.Test test = new com.racconworld.domain.test.Test();
        testRepository.save(test);

        List<QuizUploadDto> dtos = Arrays.asList(
                new QuizUploadDto("질문 1", Arrays.asList(
                        new QuizUploadDto.uploadQuizChoiceDto("선택지 1", 1),
                        new QuizUploadDto.uploadQuizChoiceDto("선택지 2", 0)
                )),
                new QuizUploadDto("질문 2", Arrays.asList(
                        new QuizUploadDto.uploadQuizChoiceDto("선택지 1", 0),
                        new QuizUploadDto.uploadQuizChoiceDto("선택지 2", 1)
                ))
        );

        // When
        quizUploadService.quiz_upload(dtos, test.getId()); // 실제 테스트 대상 메서드 실행

        // Then
        com.racconworld.domain.test.Test savedTest = testRepository.findById(test.getId()).orElse(null);
        assertNotNull(savedTest);

        // 각 질문에 대해 생성된 선택지들을 확인
        for (QuizUploadDto dto : dtos) {
            QuizQuestion quizQuestion = savedTest.getQuestions().stream()
                    .filter(q -> q.getQuiz_question().equals(dto.getQuiz_question()))
                    .findFirst().orElse(null);
            assertNotNull(quizQuestion);

            for (QuizUploadDto.uploadQuizChoiceDto choiceDto : dto.getChoices()) {
                QuizChoice quizChoice = quizQuestion.getChoices().stream()
                        .filter(c -> c.getChoice().equals(choiceDto.getChoice()))
                        .findFirst().orElse(null);
                assertNotNull(quizChoice);
                assertEquals(choiceDto.getChoice_score(), quizChoice.getChoice_score());
            }
        }
    }

    @Test
    public void Test삭제() {
        com.racconworld.domain.test.Test test = new com.racconworld.domain.test.Test();
        testRepository.save(test);
        com.racconworld.domain.test.Test test1 = new com.racconworld.domain.test.Test();
        testRepository.save(test1);

        List<QuizUploadDto> dtos = Arrays.asList(
                new QuizUploadDto("질문 1", Arrays.asList(
                        new QuizUploadDto.uploadQuizChoiceDto("선택지 1", 1),
                        new QuizUploadDto.uploadQuizChoiceDto("선택지 2", 0)
                )),
                new QuizUploadDto("질문 2", Arrays.asList(
                        new QuizUploadDto.uploadQuizChoiceDto("선택지 1", 0),
                        new QuizUploadDto.uploadQuizChoiceDto("선택지 2", 1)
                ))
        );
        quizUploadService.quiz_upload(dtos, test.getId());
        quizUploadService.quiz_upload(dtos, test1.getId());

        // When
        log.info("삭제하기 전 : {}", testRepository.findAll().size());
        assertEquals(2,testRepository.findAll().size());
        quizUploadService.remove_test(test.getId());

        // Then
        log.info("삭제하고 난 후 : {}", testRepository.findAll().size());
        assertEquals(1,testRepository.findAll().size());

        assertThrows( CustomException.class ,() ->{
            testRepository.findById(test.getId()).orElseThrow(() -> new CustomException("해당하는 test가 존재하지 않습니다."));
        });
    }

}