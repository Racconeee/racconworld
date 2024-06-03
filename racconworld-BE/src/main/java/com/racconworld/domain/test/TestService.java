package com.racconworld.domain.test;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestService {


    private final TestRepository testRepository;

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

        PageRequest pageRequest = PageRequest.of(pageNumber, 9, Sort.by(Sort.Direction.ASC, "id"));
        Slice<Test> page = testRepository.findAllOrderByViewsAsc(pageRequest);
        List<ShowTestDto> testlist = page.map( t -> new ShowTestDto(t.getId() , t.getTestName() ,t.getViews(), t.getFilepath() , t.getFiledownload())).stream().toList();

        return testlist;
    }


    @Transactional
    public List<ShowTestDto> getTestListByTop5(){

        List<Test> testList_top5 = testRepository.findTop5ByOrderByViewsDesc();
        List<ShowTestDto> dtoList = testList_top5.stream().map( t -> new ShowTestDto(t.getId() , t.getTestName() ,t.getViews(), t.getFilepath() , t.getFiledownload())).collect(Collectors.toList());

        return dtoList;
    }


}

