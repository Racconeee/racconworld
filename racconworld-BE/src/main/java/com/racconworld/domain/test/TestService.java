package com.racconworld.domain.test;


import com.racconworld.domain.quizchoice.QuizChoiceRepository;
import com.racconworld.domain.quizquestion.QuizQuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.jdbc.batch.spi.Batch;
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
        List<ShowTestDto> list = testList.stream().map(t -> new ShowTestDto(t.getId(), t.getTestName(), t.getViews(), t.getFilepath() , t.getFiledownload()))
                .collect(Collectors.toList());
        return list;
    }

    @Transactional
    public List<ShowTestDto> getTestListByPage(int pageNumber){
        int pageSize = (pageNumber == 0 ) ? 12 : 6 ; // 페이지당 아이템 수

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        Slice<Test> page = testRepository.findAllOrderByViewsAsc(pageRequest);
        List<ShowTestDto> testlist = page.map( t -> new ShowTestDto(t.getId() , t.getTestName() ,t.getViews(), t.getFilepath() , t.getFiledownload())).stream().toList();

        return testlist;
    }



}

