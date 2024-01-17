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

    @GetMapping("/list")
    public ResponseEntity<List<ShowTestDto>> Test(){
        return ResponseEntity.ok().body(testService.Test_list());
    }

    @GetMapping("/list2")
    public ResponseEntity<List<ShowTestDto>> Test2(){
        return ResponseEntity.ok().body(testService.Test_list2());
    }


    @GetMapping("/{test_id}")
    public ResponseEntity<SelectTestDto> Select_Test(@PathVariable Long test_id) throws Exception {
        return ResponseEntity.ok().body(testService.Select_Test(test_id));
    }
}
