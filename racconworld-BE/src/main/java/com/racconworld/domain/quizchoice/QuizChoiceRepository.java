package com.racconworld.domain.quizchoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizChoiceRepository extends JpaRepository<QuizChoice , Long> {
}
