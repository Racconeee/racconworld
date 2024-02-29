package com.racconworld.domain.upload.text;


import com.racconworld.domain.upload.img.ImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/text")
public class QuizUploadController {

    private final QuizUploadService quizUploadService;
    private final ImgService imgService;

    //question -> test 연관관계 매핑 + 성능 최적화
    @PostMapping("/quiz/upload")
    public ResponseEntity<String> quiz_upload(@RequestBody List<QuizUploadDto> dto,
                                              @RequestParam Long test_id,
                                              @RequestParam String admin_email,
                                              @RequestParam String admin_pw) throws Exception {
        imgService.img_login(admin_email , admin_pw);
        System.out.println(test_id);
        return ResponseEntity.ok(quizUploadService.quiz_upload(dto ,test_id));
    }



    //Test삼아서 만든 Test 삭제 나중에 변경해서 파일 구조 다시 잡아야함
    @DeleteMapping("/test/remove/{test_id}")
    public ResponseEntity<String> quiz_remove(@PathVariable Long test_id){
        return ResponseEntity.ok(quizUploadService.remove_test(test_id));
    }
}
