package com.racconworld.domain.img;

import com.racconworld.domain.admin.Admin;
import com.racconworld.domain.admin.AdminRepository;
import com.racconworld.domain.result.Result;
import com.racconworld.domain.result.ResultRepository;
import com.racconworld.domain.test.Test;
import com.racconworld.domain.test.TestRepository;
import com.racconworld.global.exception.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImgService {

    private final TestRepository testRepository;
    private final ResultRepository resultRepository;
    private final AdminRepository adminRepository;
    //
    @Value("${file.dir}")
    private String fileDir;


    //동작원리
    /*

     *
     * */
    @Transactional
    public void upload(MultipartFile file, String filename, Long file_number) throws Exception {
        //  아래 링크된 파일에 저장을 하게된다
        String filepath = fileDir + file_number;

//        String fileName = file.getOriginalFilename(); 전송된 파일의 이름

        //부모 파일이 없다면 부모 파일 생성
//        createDir(filepath);
//        //파일 저장하는 메소드
//        savefile(file, filename, filepath);


        Optional<Test> byId = testRepository.findById(file_number);
        if (byId.isPresent()) {
            // 이미 같은 projectPath가 DB에 존재하는지 확인
//            if (resultRepository.existsByFilepath(filepath)) {
//                // 이미 존재한다면 예외 발생
//                throw new Exception("Duplicate projectPath: " + filepath);
//            }

            Result result = new Result(byId.get(), filepath, filename);
            resultRepository.save(result);
        } else {
            // 파일이 비어있을 때 예외 처리
            throw new Exception("Uploaded file is empty.");
        }
    }

    // 저장 방식
    // filepath : spring/img
    // filename : 1
    // filepath + filename 형식으로 저장된다 -> spring/img/1
    private static void savefile(MultipartFile file, String filename, String filepath) throws IOException {
        File saveFile = new File(filepath, filename);
        file.transferTo(saveFile);
    }

    //filepath에 해당하는 파일이 없다면 파일을 생성해준다.
    private static void createDir(String filepath) throws IOException {
        Path imagePath = Paths.get(filepath);
        if (!Files.exists(imagePath)) {
            Files.createDirectories(imagePath);
        }
    }
//
//    @Transactional
//    public List<String> picture_list() throws Exception {
//        Optional<Test> byId = testRepository.findById(1L);
//
//        List<Result> results = byId.get().getResults();
//        List<String> picutre_lists = new ArrayList<>();
//
//        for ( Result resulte : results ){
//            picutre_lists.add(resulte.getFilepath());
//        }
//        return picutre_lists;
//    }


    //test 저장하기
    @Transactional
    public void Test_upload(MultipartFile file, String filename, Long file_number) throws Exception {
        //  아래 링크된 파일에 저장을 하게된다
        String filepath = fileDir + file_number;

//        Optional<Test> byId = testRepository.findById(file_number);
//        if (byId.isPresent()) {
        Test test = new Test("mbti", 0L, 0, filename, filepath);
        testRepository.save(test);
//        } else {
//            // 파일이 비어있을 때 예외 처리
//            throw new Exception("Uploaded file is empty.");
//        }
    }

    //로그인에 대한 에러
    // 1.이메일이 존재하지않을떄
    // 2.비밀번호가 맞지않을때
    public void img_login(String email, String pw) throws Exception {

        if(email == null || pw ==null){
            throw new CustomException("Email , pw 값이 입력되지 않았습니다.");
        }

        Optional<Admin> byEmail = adminRepository.findByEmail(email);
        //아이디가 맞지않는다면 예외 발생
        Admin admin = byEmail.orElseThrow(() -> new CustomException("유효하지않은 이메일입니다."));

        System.out.println(byEmail);
        System.out.println(admin);
        //Http api 로 들어온 PW 값과 DB의 pw 의 값을 비교해서
        //같지않는다면 비밀번호가 맞지않는다는 예와
        if (admin.getPw().equals(pw)) {
            return;
        } else {
            throw new MemberException(MemberErrorCode.PASSWORD_MISMATCH_ERROR);
        }
    }
}
