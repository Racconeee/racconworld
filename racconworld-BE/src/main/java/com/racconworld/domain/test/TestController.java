package com.racconworld.domain.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    // page 기능 없이 한번에 조회하기
//    @GetMapping("/list")
//    public ResponseEntity<List<ShowTestDto>> Test(){
//        return ResponseEntity.ok().body(testService.Test_list());
//    }

//    @GetMapping("/list2")
//    public ResponseEntity<List<ShowTestDto>> Test2(){
//        return ResponseEntity.ok().body(testService.Test_list2());
//    }

    //page 기능사용해서 조회하기
    @GetMapping("/list")
    public ResponseEntity<List<ShowTestDto>> getTestList(@RequestParam(defaultValue = "0") int page) {
        List<ShowTestDto> testList = testService.getTestListByPage(page);
        return ResponseEntity.ok().body(testList);
    }

}
