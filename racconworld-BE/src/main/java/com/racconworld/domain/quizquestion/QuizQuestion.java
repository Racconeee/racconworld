package com.racconworld.domain.quizquestion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.racconworld.domain.quizchoice.QuizChoice;
import com.racconworld.domain.test.Test;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class QuizQuestion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quizquestion_id")
    private Long id;

    @JsonIgnore
    @JoinColumn(name = "test_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Test question_to_test;

    @JsonIgnore
    @OneToMany(mappedBy = "choice_to_quizquestion",fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<QuizChoice> choices = new ArrayList<>();
    private String quiz_question;     //퀴즈 질문 EX)1번 친구만나는데 지갑을 두고온 나는 ?

    public String getQuiz_question() {
        return quiz_question;
    }

    public QuizQuestion( Test testEntity,String quiz_question) {
        this.question_to_test = testEntity;
        this.quiz_question = quiz_question;
    }

    public void addChoice(QuizChoice choice){
        choices.add(choice);
    }
}
