package com.racconworld.domain.result;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/result")
public class ResultController {

    private final ResultService resultService;


    @Operation(summary = "테스트종료후 점수에 따라서 결과 반환  ",
            description =   "EX) result/1/80 이라면  1번째 테스트의 80에 해당하는 결과를 보여준다.")
    @GetMapping("/{test_id}/{score}")
    public ResponseEntity<ShowResultDto> show_result(@PathVariable Long test_id,
                                              @PathVariable String score){
        return ResponseEntity.ok(resultService.show_result(test_id , score));
    }

}
