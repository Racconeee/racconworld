package com.racconworld.domain.quizquestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Long> {

    List<QuizQuestion> findAllById(Long id);
    @Query("SELECT q FROM QuizQuestion q JOIN FETCH q.choices WHERE q.question_to_test.id = :testId")
    List<QuizQuestion> findQuestionsWithChoicesByTestId(@Param("testId") Long testId);






}
