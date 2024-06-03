package com.racconworld.domain.quiz;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    @Operation(summary = "테스트선택시 해당 테스트 질문,선택지 조회  ",
            description =   "TestList에서 Quiz 선택했을떄 \n\n" +
                            "테스트 하나에 대한 내용과 퀴즈 질문 , 선택지에 대해서 조회\n\n")
    @GetMapping("/{test_id}")
    public ResponseEntity<ShowQuizDto> show_quiz(@PathVariable Long test_id) throws Exception {
        return ResponseEntity.ok(quizService.show_quiz(test_id));

    }

}
