package com.racconworld.domain.upload.img;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@RequestMapping("/img")
public class ImgController {

    private final ImgService imgService;



//
//    @GetMapping("/picture_list")
//    public ResponseEntity<List<String>> picture_list() throws Exception {
//        return ResponseEntity.ok(imgService.picture_list());
//    }


    //새로운 테스트를 업로드 하기 위해서는
    //먼저 admin의 email, pw 을 통해서 img_login 메소드가 정상적으로 예외없이 return 되어야 한다.
    //이후 로그인이 되었다면 이미지 업로드를 시작하게 된다.
    //DTO로 변환하고 vaild 로 검증 하기
    @PostMapping("/test/upload")
    public String test_upload(@RequestParam("file") MultipartFile file,
                         @RequestParam String admin_email,
                         @RequestParam String admin_pw,
                         @RequestParam int question_count,
                              @RequestParam String test_name,
                              @RequestParam Long file_number) throws Exception{
        imgService.img_login(admin_email , admin_pw);
        imgService.upload(file,question_count, test_name , file_number);
        return "test가 저장되었습니다.";
    }


    @PostMapping("result/upload")
    public String upload2(@RequestParam("file") MultipartFile file,
                          @RequestParam Long test_id,
                          @RequestParam String score,
                          @RequestParam String admin_email,
                          @RequestParam String admin_pw) throws Exception{
        imgService.img_login(admin_email , admin_pw);
        imgService.result_upload(file,test_id ,score);
        return "result가 저장되었습니다.";
    }
}
