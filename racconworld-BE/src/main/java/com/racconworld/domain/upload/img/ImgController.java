package com.racconworld.domain.upload.img;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/img")
@Validated
public class ImgController {

    private final ImgService imgService;


    //새로운 테스트를 업로드 하기 위해서는
    //먼저 admin의 email, pw 을 통해서 img_login 메소드가 정상적으로 예외없이 return 되어야 한다.
    //이후 로그인이 되었다면 이미지 업로드를 시작하게 된다.
    //DTO로 변환하고 vaild 로 검증 하기 -> RequestParam,RequestBody 같이 받는것이 안됨
    //따라서 RequestParam으로 구현
    @Operation(summary = "Test image 업로드 ",
            description = "Test를 저장하는 api\n\n" +
                          "파일 이미지 경로는 racconworld_data/test_id/main 으로 저장됨 \n\n" +
                          "@RequestParam MultipartFile file : 파일 \n\n" +
                          "@RequestParam int question_count : 3\n\n" +
                          "@RequestParam String testName : 당신의 성향은?\n\n" )
    @PostMapping("/test/upload")
    public String test_upload(@RequestParam MultipartFile file,
                              @RequestParam @Min(1) int question_count,
                              @RequestParam @NotBlank(message = "testName 값이 없습니다.") String testName) throws Exception{
        imgService.test_upload(file, question_count, testName);

        return "test가 저장되었습니다.";
    }


    @Operation(summary = "Test image 업로드 ",
            description = "Test에 대한 결과를 저장하는 api\n\n" +
                          "파일 이미지 경로는 racconworld_data/test_id/score 으로 저장됨 \n\n" +
                          "@RequestParam MultipartFile file : 파일\n\n" +
                          "@RequestParam int test_id : 3\n\n" +
                          "@RequestParam String score : 80")
    @PostMapping("result/upload")
    public String result_upload(@RequestParam("file") MultipartFile file,
                          @RequestParam @Min(1) Long test_id,
                          @RequestParam @NotBlank String score) throws Exception{
        imgService.result_upload(file, test_id, score);

        return "result가 저장되었습니다.";
    }
}
