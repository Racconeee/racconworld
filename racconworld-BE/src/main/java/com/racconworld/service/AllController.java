package com.racconworld.service;

import com.racconworld.domain.test.SelectTestDto;
import com.racconworld.domain.test.ShowTestDto;
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
public class AllController {

    private final AllService allService;

    @GetMapping("/list")
    public ResponseEntity<List<ShowTestDto>> Test(){
        return ResponseEntity.ok().body(allService.Test_list());
    }


    @GetMapping("/{test_id}")
    public ResponseEntity<SelectTestDto> Select_Test(@PathVariable Long test_id) throws Exception {
        return ResponseEntity.ok().body(allService.Select_Test(test_id));
    }

}
