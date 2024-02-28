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
        return ResponseEntity.ok(quizUploadService.quiz_upload(dto ,test_id));
    }
}
