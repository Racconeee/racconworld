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
     * 테스트 생성하는 메소드 Test O result X
     * filepath -> 이미지 파일 경로
     * filepath -> 테스트마다 메인 이미지 위치
     * 현재 저장되어있지 않은 새로운 테스트만 생성 가능하다
     * 저장되어 있다면 예외처리
     * 나중에 filename 제거하자 DB 자체에서 삭제
     * */
    @Transactional
    public void upload(MultipartFile file, int question_count, String test_name, Long test_id) throws Exception {

        String filepath = fileDir + test_id;

        String filepath_main = filepath + "/main";

        Optional<Test> byId = testRepository.findByFilepath(filepath_main);
        if(byId.isPresent()){
            throw new CustomException("현재 저장되어 있는 이미지가 존재합니다.");
        }else{
            Test test = new Test(test_name, 4 , "이 컬럼 삭제해야됨" , filepath_main);
            testRepository.save(test);
        }

        //  아래 링크된 파일에 저장을 하게된다


        //부모 파일이 없다면 부모 파일 생성
        createDir(filepath);
//        //파일 저장하는 메소드
        savefile(file,"main", filepath);

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
