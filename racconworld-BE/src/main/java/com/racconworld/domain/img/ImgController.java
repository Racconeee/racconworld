package com.racconworld.domain.img;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
@RequestMapping("/img")
public class ImgController {

    private final ImgService imgService;

    //이미지 파일 업로드
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file,
                         @RequestParam String filename,
                         @RequestParam Long file_number) throws Exception{
        imgService.upload(file, filename , file_number);
        return "22";
    }

    @GetMapping("/files/{fileName:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) throws IOException {
        // 이미지 파일의 실제 경로를 구성
        String uploadPath = System.getProperty("user.dir") + "/src/main/resources/static/files";

        Path imagePath = Paths.get(uploadPath, fileName);

        // 해당 경로에 있는 이미지 파일을 읽어서 Resource로 만듦
        Resource resource = new UrlResource(imagePath.toUri());

        // 클라이언트로 이미지 전송
        return ResponseEntity.ok().body(resource);
    }
//
//    @GetMapping("/picture_list")
//    public ResponseEntity<List<String>> picture_list() throws Exception {
//        return ResponseEntity.ok(imgService.picture_list());
//    }


    //새로운 테스트를 업로드 하기 위해서는
    //먼저 admin의 email, pw 을 통해서 img_login 메소드가 정상적으로 예외없이 return 되어야 한다.
    //이후 로그인이 되었다면 이미지 업로드를 시작하게 된다.
    @PostMapping("/test/upload")
    public String test_upload(@RequestParam("file") MultipartFile file,
                         @RequestParam String admin_email,
                         @RequestParam String admin_pw,
                         @RequestParam String filename,
                         @RequestParam Long file_number) throws Exception{
        imgService.img_login(admin_email , admin_pw);
        imgService.upload(file, filename , file_number);
        return "22";
    }


}
