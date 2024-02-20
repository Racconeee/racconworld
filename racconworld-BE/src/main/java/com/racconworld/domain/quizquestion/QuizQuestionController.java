package com.racconworld.domain.quizquestion;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/quiz")
public class QuizQuestionController {


    private final QuizQuestionService quizQuestionService;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<List<ShowQuizQuestionDto>> quiz(@PathVariable Long id) throws Exception {
//
//        return ResponseEntity.ok().body(quizQuestionService.quiz(id));
//    }

}
