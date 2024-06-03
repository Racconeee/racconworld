package com.racconworld.domain.quizchoice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.racconworld.domain.quizquestion.QuizQuestion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class QuizChoice {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quizchoice_id")
    private Long id;


    @JsonIgnore
    @JoinColumn(name = "quizquestion_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private QuizQuestion choice_to_quizquestion;

    private String choice;
    private int choice_score;

    public QuizChoice( QuizQuestion question ,String choice , int choice_score) {
        this.choice_to_quizquestion = question;
        this.choice = choice;
        this.choice_score = choice_score;
    }

}
