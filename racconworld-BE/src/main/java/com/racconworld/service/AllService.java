package com.racconworld.service;

import com.racconworld.domain.quizchoice.QuizChoiceRepository;
import com.racconworld.domain.quizquestion.QuizQuestionRepository;
import com.racconworld.domain.test.SelectTestDto;
import com.racconworld.domain.test.ShowTestDto;
import com.racconworld.domain.test.Test;
import com.racconworld.domain.test.TestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AllService {

    private final TestRepository testRepository;
    private final QuizQuestionRepository quizQuestionRepository;
    private final QuizChoiceRepository quizChoiceRepository;

    @Transactional
    public List<ShowTestDto> Test_list(){
        List<Test> testList = testRepository.findAll();
        List<ShowTestDto> list = testList.stream().map(t -> new ShowTestDto(t.getId(), t.getTestName(), t.getViews(), t.getImg_url()))
                .collect(Collectors.toList());
        return list;
    }

    @Transactional
    public SelectTestDto Select_Test(Long id) throws Exception {
        Optional<Test> optionalTest = testRepository.findTestWithQuestionsAndChoicesById(id);

        if(optionalTest.isPresent()){
            SelectTestDto dto = SelectTestDto.toDto(optionalTest.get());

            return dto;
        }else {
            throw new Exception("Test가 없습니다.");
        }
    }
}
