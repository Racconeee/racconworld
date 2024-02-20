package com.racconworld.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RestController
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
@RequestMapping("/test")
public class AllController {

    private final AllService allService;

//    @GetMapping("/list")
//    public ResponseEntity<List<ShowTestDto>> Test(){
//        return ResponseEntity.ok().body(allService.Test_list());
//    }
//
//
//    @GetMapping("/{test_id}")
//    public ResponseEntity<SelectTestDto> Select_Test(@PathVariable Long test_id) throws Exception {
//        return ResponseEntity.ok().body(allService.Select_Test(test_id));
//    }
//
//    @PostMapping("/upload")
//    public String upload(@RequestParam("file") MultipartFile file) throws Exception{
//        System.out.println(file.getOriginalFilename());
//        allService.upload(file);
//        return "22";
//    }
//
//    @GetMapping("/files/{fileName:.+}")
//    public ResponseEntity<Resource> getImage(@PathVariable String fileName) throws IOException {
//        // 이미지 파일의 실제 경로를 구성
//        String uploadPath = System.getProperty("user.dir") + "/src/main/resources/static/files";
//
//        Path imagePath = Paths.get(uploadPath, fileName);
//
//        // 해당 경로에 있는 이미지 파일을 읽어서 Resource로 만듦
//        Resource resource = new UrlResource(imagePath.toUri());
//
//        // 클라이언트로 이미지 전송
//        return ResponseEntity.ok().body(resource);
//    }
//
//    @GetMapping("/filess/{fileName:.+}")
//    public ResponseEntity<Resource> getImages(@PathVariable String fileName) throws IOException {
//        // 이미지 파일의 실제 경로를 구성
//        String uploadPath = System.getProperty("user.dir") + "/src/main/resources/static/files";
//
//        Path imagePath = Paths.get(uploadPath, fileName);
//
//        // 해당 경로에 있는 이미지 파일을 읽어서 Resource로 만듦
//        Resource resource = new UrlResource(imagePath.toUri());
//
//        // 클라이언트로 이미지 전송
//        return ResponseEntity.ok().body(resource);
//    }
//
//    @GetMapping("/picture_list")
//    public ResponseEntity<List<String>> picture_list() throws Exception {
//        return ResponseEntity.ok(allService.picture_list());
//    }

}
