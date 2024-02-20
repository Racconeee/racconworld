package com.racconworld.service;

import com.racconworld.domain.quizchoice.QuizChoiceRepository;
import com.racconworld.domain.quizquestion.QuizQuestionRepository;
import com.racconworld.domain.result.Result;
import com.racconworld.domain.result.ResultRepository;
import com.racconworld.domain.test.SelectTestDto;
import com.racconworld.domain.test.ShowTestDto;
import com.racconworld.domain.test.Test;
import com.racconworld.domain.test.TestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AllService {

    private final TestRepository testRepository;
    private final ResultRepository resultRepository;
    private final QuizQuestionRepository quizQuestionRepository;
    private final QuizChoiceRepository quizChoiceRepository;

//    @Transactional
//    public List<ShowTestDto> Test_list(){
//        List<Test> testList = testRepository.findAll();
//        List<ShowTestDto> list = testList.stream().map(t -> new ShowTestDto(t.getId(), t.getTestName(), t.getViews(), t.getImg_url()))
//                .collect(Collectors.toList());
//        return list;
//    }
//
//    @Transactional
//    public SelectTestDto Select_Test(Long id) throws Exception {
//        Optional<Test> optionalTest = testRepository.findTestWithQuestionsAndChoicesById(id);
//
//        if(optionalTest.isPresent()){
//            SelectTestDto dto = SelectTestDto.toDto(optionalTest.get());
//
//            return dto;
//        }else {
//            throw new Exception("Test가 없습니다.");
//        }
//    }
//
//    @Transactional
//    public void upload(MultipartFile file) throws Exception {
//        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";
//// 현재 파일 위치/Users/raccon/study/project/Spring/BE/racconworld-BE/src/main/resources/static/files
//
//        String fileName = file.getOriginalFilename();
//
//        File saveFile = new File(projectPath ,fileName);
//
//        file.transferTo(saveFile);
//        Result result = new Result();
//
//
//        Optional<Test> byId = testRepository.findById(1L);
//        result.setFilename(fileName);
//        result.setFilepath("/files/" + fileName);
//        result.setResult_to_test(byId.get());
//
//        resultRepository.save(result);
//    }
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
//



}
