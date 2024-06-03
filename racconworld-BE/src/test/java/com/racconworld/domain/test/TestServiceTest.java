package com.racconworld.domain.test;

import static org.junit.jupiter.api.Assertions.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Slf4j
class TestServiceTest {


    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TestService testService;

    @BeforeEach
    public void setUp(){
        Test test1 = new Test("여자친구 생길 확률은 ?", 3, "racconworld_data", "/var/www");
        Test test2 = new Test("남자친구 생길 확률은 ?", 2, "racconworld_data", "/var/www");
        Test test3 = new Test("무인도에서 살아남을 확률은 ?", 4, "racconworld_data", "/var/www");
        Test test4 = new Test("성향테스트 ?", 4, "racconworld_data", "/var/www");
        Test test5 = new Test("남자친구 생길 확률은 ?", 2, "racconworld_data", "/var/www");
        Test test6 = new Test("무인도에서 살아남을 확률은 ?", 4, "racconworld_data", "/var/www");
        Test test7 = new Test("성향테스트 ?", 4, "racconworld_data", "/var/www");
        Test test8 = new Test("남자친구 생길 확률은 ?", 2, "racconworld_data", "/var/www");
        Test test9 = new Test("무인도에서 살아남을 확률은1 ?", 4, "racconworld_data", "/var/www");
        Test test10 = new Test("무인도에서 살아남을 확률은2 ?", 4, "racconworld_data", "/var/www");
        Test test11 = new Test("무인도에서 살아남을 확률은3 ?", 4, "racconworld_data", "/var/www");
        Test test12 = new Test("무인도에서 살아남을 확률은4 ?", 4, "racconworld_data", "/var/www");
        Test test13 = new Test("무인도에서 살아남을 확률은5 ?", 4, "racconworld_data", "/var/www");
        Test test14 = new Test("무인도에서 살아남을 확률은6 ?", 4, "racconworld_data", "/var/www");
        Test test15 = new Test("무인도에서 살아남을 확률은7 ?", 4, "racconworld_data", "/var/www");
        Test test16 = new Test("무인도에서 살아남을 확률은8 ?", 4, "racconworld_data", "/var/www");
        Test test17 = new Test("무인도에서 살아남을 확률은9 ?", 4, "racconworld_data", "/var/www");
        Test test18 = new Test("무인도에서 살아남을 확률은10 ?", 4, "racconworld_data", "/var/www");
        Test test19 = new Test("무인도에서 살아남을 확률은11 ?", 4, "racconworld_data", "/var/www");

        testRepository.save(test1);
        testRepository.save(test2);
        testRepository.save(test3);
        testRepository.save(test4);
        testRepository.save(test5);
        testRepository.save(test6);
        testRepository.save(test7);
        testRepository.save(test8);
        testRepository.save(test9);
        testRepository.save(test10);
        testRepository.save(test11);
        testRepository.save(test12);
        testRepository.save(test13);
        testRepository.save(test14);
        testRepository.save(test15);
        testRepository.save(test16);
        testRepository.save(test17);
        testRepository.save(test18);
        testRepository.save(test19);
    }

//
//    @Transactional
//    public List<ShowTestDto> getTestListByPage(int pageNumber){
//        int pageSize = (pageNumber == 0 ) ? 12 : 6 ; // 페이지당 아이템 수
//
//        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "id"));
//        Slice<Test> page = testRepository.findAllOrderByViewsAsc(pageRequest);
//        List<ShowTestDto> testlist = page.map( t -> new ShowTestDto(t.getId() , t.getTestName() ,t.getViews(), t.getFilepath() , t.getFiledownload())).stream().toList();
//
//        return testlist;
//    }


    @org.junit.jupiter.api.Test
    public void 테스트리스트_Slice보기_zero() {
        //given
        int pageNumber = 0; // 페이지 번호를 0으로 설정
        int pageSize = 9; // 페이지 크기를 12로 설정
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "id"));

        //When
        Slice<Test> page = testRepository.findAllOrderByViewsAsc(pageRequest);
        List<ShowTestDto> testlist = page.map( t -> new ShowTestDto(t.getId() , t.getTestName() ,t.getViews(), t.getFilepath() , t.getFiledownload())).stream().toList();

        //Then
        //13개의 테스트를 넣어 놓고 처음이니 12개를 조회 조회해보면
        //무인도에서 살아남을 확률은 5는 빼고 12개의 테스트가 조회된것을 볼 수 있다.
        log.info("Test_DB 존재하는 Test : {} " ,testRepository.findAll().stream().map(Test::getTestName).collect(Collectors.toList()));
        log.info("Test_DB 존재하는 Test.size() : {} " ,testRepository.findAll().size());
        log.info("-------------------처음 조회하는 Testlist------------------------------");
        for (ShowTestDto dto : testlist) {
            log.info("test.id : {} , test.Name : {} , test.view : {} ", dto.getTest_id() ,dto.getTestName() , dto.getViews());
        }
        log.info("test.size : {} " , testlist.size());
        assertEquals(9, testlist.size());
    }

    @org.junit.jupiter.api.Test
    public void 테스트리스트_Slice보기() {
        //given
        int pageNumber = 1; // 페이지 번호를 1으로 설정
        int pageSize = 9;
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "id"));

        //When
        Slice<Test> page = testRepository.findAllOrderByViewsAsc(pageRequest);
        List<ShowTestDto> testlist = page.map( t -> new ShowTestDto(t.getId() , t.getTestName() ,t.getViews(), t.getFilepath() , t.getFiledownload())).stream().toList();

        //Then

        log.info("Test_DB 존재하는 Test : {} " ,testRepository.findAll().stream().map(Test::getTestName).collect(Collectors.toList()));
        log.info("Test_DB 존재하는 Test.size() : {} " ,testRepository.findAll().size());
        log.info("-------------------처음 조회하는 Testlist------------------------------");
        for (ShowTestDto dto : testlist) {
            log.info("test.id : {} , test.Name : {} , test.view : {} ", dto.getTest_id() ,dto.getTestName() , dto.getViews());
        }
        log.info("test.size : {} " , testlist.size());
        assertEquals(9, testlist.size());

    }

}