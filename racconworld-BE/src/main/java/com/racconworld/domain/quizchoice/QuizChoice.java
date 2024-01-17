package com.racconworld.domain.quizchoice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.racconworld.domain.test.Test;
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
    @JoinColumn(name = "test_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Test choice_to_test;

    private String choice;


}
