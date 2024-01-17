package com.racconworld.domain.test;


import com.racconworld.domain.quizchoice.QuizChoiceRepository;
import com.racconworld.domain.quizquestion.QuizQuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestService {


    private final TestRepository testRepository;
    private final QuizQuestionRepository quizQuestionRepository;
    private final QuizChoiceRepository quizChoiceRepository;

    //전체 리스트 보여주기 Entity -> Dto
    @Transactional
    public List<ShowTestDto> Test_list(){
        List<Test> testList = testRepository.findAll();
        List<ShowTestDto> list = testList.stream().map(t -> new ShowTestDto(t.getId(), t.getTestName(), t.getViews(), t.getImg_url()))
                .collect(Collectors.toList());
        return list;
    }

    //선택한 테스트 Entity -> Dto
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

    @Transactional
    public List<ShowTestDto> Test_list2(){
        PageRequest pageRequest = PageRequest.of(0,3, Sort.by(Sort.DEFAULT_DIRECTION, "page-number"));
        Slice<Test> page = testRepository.findAllOrderByViewsAsc(pageRequest);
        List<ShowTestDto> testlist = page.map( t -> new ShowTestDto(t.getId() , t.getTestName() ,t.getViews(), t.getImg_url())).stream().toList();

        return testlist;
    }


}

