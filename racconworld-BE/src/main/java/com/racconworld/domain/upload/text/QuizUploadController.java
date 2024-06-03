package com.racconworld.domain.upload.text;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/text")
public class QuizUploadController {

    private final QuizUploadService quizUploadService;

    //question -> test 연관관계 매핑 + 성능 최적화
    @Operation(summary = "퀴즈, 선택지 upload  ",
            description =   "사과 나무에 달리는 조회수 많은 상위 5개 종목들이다. \n\n" +
                            "@RequestBody List<QuizUploadDto> dto,\n\n" +
                            "@RequestParam Long test_id \n\n" +
                            "[\n\n" +
                            "    {\n\n" +
                            "        \"quiz_question\": \"나는 얼마나 자주 스트레스를 느끼나요?\",\n\n" +
                            "        \"choices\": [\n\n" +
                            "        {\"choice\": \"거의 매일\", \"choice_score\": 10},\n\n" +
                            "        {\"choice\": \"가끔\", \"choice_score\": 5}\n" +
                            "      ]\n" +
                            "    },\n" +
                            "    {\n" +
                            "      \"quiz_question\": \"나의 주말 계획은?\",\n\n" +
                            "      \"choices\": [\n\n" +
                            "        {\"choice\": \"외출하기\", \"choice_score\": 5},\n\n" +
                            "        {\"choice\": \"집에서 휴식\", \"choice_score\": 10}\n\n" +
                            "      ]\n\n" +
                            "    },")
    @PostMapping("/quiz/upload")
    public ResponseEntity<String> quiz_upload(@RequestBody @NotBlank List<QuizUploadDto> dto,
                                              @RequestParam @NotBlank Long test_id) throws Exception {
        return ResponseEntity.ok(quizUploadService.quiz_upload(dto ,test_id));
    }



    //Test삼아서 만든 Test 삭제 나중에 변경해서 파일 구조 다시 잡아야함
    @Operation(summary = "Test 삭제  ",
            description =   "Test에 해당하는 질문지와 선택지 모두 삭제된다.")
    @DeleteMapping("/test/remove/{test_id}")
    public ResponseEntity<String> quiz_remove(@PathVariable Long test_id){
        return ResponseEntity.ok(quizUploadService.remove_test(test_id));
    }
}
