package com.racconworld.domain.quiz;

import com.racconworld.domain.quizchoice.QuizChoice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    @GetMapping("/{test_id}")
    public ResponseEntity<ShowQuizDto> show_quiz(@PathVariable Long test_id) throws Exception {
        return ResponseEntity.ok(quizService.show_quiz(test_id));

    }

}
