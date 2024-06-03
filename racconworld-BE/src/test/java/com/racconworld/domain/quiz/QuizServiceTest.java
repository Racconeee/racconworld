package com.racconworld.domain.quiz;

import static org.junit.jupiter.api.Assertions.*;

import com.racconworld.domain.quizchoice.QuizChoice;
import com.racconworld.domain.quizquestion.QuizQuestion;
import com.racconworld.domain.quizquestion.QuizQuestionRepository;
import com.racconworld.domain.test.Test;
import com.racconworld.domain.test.TestRepository;
import com.racconworld.domain.upload.text.QuizUploadDto;
import com.racconworld.domain.upload.text.QuizUploadService;
import com.racconworld.global.exception.CustomException;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@SpringBootTest
@Transactional
class QuizServiceTest {

    @Autowired
    private static final Logger log = LoggerFactory.getLogger(QuizServiceTest.class);

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private QuizUploadService quizUploadService;



    @BeforeEach
    public void setUp() {

        quizQuestionRepository.deleteAll();
        testRepository.deleteAll();

    }


    @org.junit.jupiter.api.Test
    @DisplayName("퀴즈 리스트조회 테스트")
    public void show_quiz() {
        //given
        Test test = new Test("여자친구 생길 확률은 ? " , 3, "racconworld_data" , "/var/www");
        testRepository.save(test);

        List<QuizUploadDto> dtos = Arrays.asList(
                new QuizUploadDto("질문 1", Arrays.asList(
                        new QuizUploadDto.uploadQuizChoiceDto("선택지 1", 1),
                        new QuizUploadDto.uploadQuizChoiceDto("선택지 2", 0)
                )),
                new QuizUploadDto("질문 2", Arrays.asList(
                        new QuizUploadDto.uploadQuizChoiceDto("선택지 3", 0),
                        new QuizUploadDto.uploadQuizChoiceDto("선택지 4", 1)
                ))
        );

        quizUploadService.quiz_upload(dtos, test.getId()); // 실제 테스트 대상 메서드 실행

        //When
        List<QuizQuestion> questions = quizQuestionRepository.findQuestionsWithChoicesByTestId(test.getId());
        ShowQuizDto dto = ShowQuizDto.toDto(test, questions);


        //Then
        for (ShowQuizDto.ShowQuizQuestionDto questionDto : dto.getQuizQuestions()) {
            System.out.println(questionDto.getQuiz_question());
            log.info(" {} :  {} ",questionDto.getQuiz_question(),
                    questionDto.getChoices().stream().map(QuizChoice::getChoice).collect(Collectors.toList()));

            log.info(" {} : 선택지 갯수 {} ",questionDto.getQuiz_question(),questionDto.getChoices().size());
            //선택지 갯수 테스트
            assertEquals(2, questionDto.getChoices().size());
        }
        //질문지 갯수 테스트
        assertEquals(2, questions.size());

    }


    @org.junit.jupiter.api.Test
    @DisplayName("테스트 조회수_테스트")
    @Transactional
    public void updateTestByViews() {
        //given
        Test test = new Test( "여자친구 생길 확률은 ? " , 3, "racconworld_data" , "/var/www");
        testRepository.save(test);

        //When

        testRepository.updateTestByViews(test.getId());
        testRepository.updateTestByViews(test.getId());
        entityManager.refresh(test);

        log.info("Test getId : {} " , test.getId());
        log.info("Test Views : {} " , test.getViews());


        //Then
        assertEquals(2 , test.getViews());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("퀴즈 없을때 확인")
    public void find_test() {
        //given
        Test test = new Test("여자친구 생길 확률은 ? " , 3, "racconworld_data" , "/var/www");
        testRepository.save(test);

        //When
        Optional<Test> byId = testRepository.findById(test.getId());
        Test test_id = byId.orElseThrow(() -> new CustomException("현재 존재하지 않는 테스트입니다."));

        //Then
        assertThrows( CustomException.class , () -> {
            testRepository.findById(999L).orElseThrow(() -> new CustomException("현재 존재하지않는 테스트입니다."));
        });
    }

}
