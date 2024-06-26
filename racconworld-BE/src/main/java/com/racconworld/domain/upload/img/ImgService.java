package com.racconworld.domain.upload.img;

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
    //
    @Value("${image_file.dir}")
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
    public void test_upload(MultipartFile file, int question_count, String test_name) throws Exception {

        testRepository.findByTestName(test_name).ifPresent(test -> {
            throw new CustomException("이미 해당 테스트가 존재합니다.");
        });

        Test test = testRepository.save(new Test());

        String filepath = fileDir + test.getId();

        String filepath_main = filepath + "/main";

        String filedownload = "/q/" + test.getId() +"/main";

        test.test_imgupload(test_name, question_count , filepath_main,filedownload);
        testRepository.save(test);
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
    // result
    /*
     * result 결과 이미지 등록 메소드
     * 예외처리
     * 1.이미 이미지가 존재하는 경우
     * 2.Test가 존재하지 않는경우
     * */
    @Transactional
    public void result_upload(MultipartFile file, Long test_id , String score) throws IOException {

        Test byId = testRepository.findById(test_id).orElseThrow( () -> new CustomException("해당하는 test가 존재하지않습니다."));

        String filepath = fileDir + test_id;
        String filepath_result = fileDir + test_id +"/" + score;
        String filedownload = "/a/" + test_id+"/" + score;

        //test의 main 페이지가 존재하지 않는다면 예외 처리
        //사실 test가 존재하지 않으면 파일 자체가 없는거여서 로직에 안걸리지만
        //테스트후 제거 할지 말지 정해보자 리펙토링 가능성 있음
        if (!Files.exists(Path.of(filepath))) {
            throw new CustomException("현재 Test에 해당하는 이미지 파일이 존재하지않습니다.");
        }

        Optional<Result> result_Optional = resultRepository.findByFilepath(filepath_result);
        if ( result_Optional.isPresent()){
            throw new CustomException("현재 해당하는 이미지 파일이 있습니다.");
        }

        resultRepository.save(new Result(byId, filepath + "/" +score , score, filedownload));

        //문제가 없다면 파일 생성
        savefile(file , score, filepath);

    }

}
