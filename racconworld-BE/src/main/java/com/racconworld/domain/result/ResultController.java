package com.racconworld.domain.result;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/result")
public class ResultController {

    private final ResultService resultService;

    @GetMapping("/{test_id}/{score}")
    public ResponseEntity<ShowResultDto> show_result(@PathVariable Long test_id,
                                              @PathVariable String score){
        return ResponseEntity.ok(resultService.show_result(test_id , score));
    }

}
