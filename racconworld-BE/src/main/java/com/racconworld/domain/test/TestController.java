package com.racconworld.domain.test;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/test")
public class TestController {

    private final TestService testService;


    //page 기능사용해서 조회하기
    @Operation(summary = "전체 Test_list Slice로 조회하기 ",
            description =   " test/list?page=1 \n\n" +
                            "메인페이지에 보여지는 Slice_Testlist\n\n" +
                            "기본적으로 한번 조회할때 9씩 list를 반환하게 되고\n\n" +
                            "@RequestParam MultipartFile file : 파일\n\n" +
                            "아래와 같은 형태로 반환하게 된다.\n\n" +
                            "{\n\n" +
                            "        \"test_id\": 50,\n\n" +
                            "        \"testName\": \"12\",\n\n" +
                            "        \"views\": 0,\n\n" +
                            "        \"filepath\": \"/Users/raccon/study/project/Spring/racconworld_data/img/50/main\",\n\n" +
                            "        \"filedownload\": \"/q/50/main\"\n\n" +
                            "    },\n\n" +
                            "    {\n\n" +
                            "        \"test_id\": 51,\n\n" +
                            "        \"testName\": \"13\",\n\n" +
                            "        \"views\": 0,\n\n" +
                            "        \"filepath\": \"/Users/raccon/study/project/Spring/racconworld_data/img/51/main\",\n\n" +
                            "        \"filedownload\": \"/q/51/main\"\n\n" +
                            "    },")
    @GetMapping("/list")
    public ResponseEntity<List<ShowTestDto>> getTestList(@RequestParam(defaultValue = "0") int page) {
        List<ShowTestDto> testList = testService.getTestListByPage(page);
        return ResponseEntity.ok().body(testList);
    }

    @Operation(summary = "조회수 많은 상위 5개 Test 조회  ",
            description =   "사과 나무에 달리는 조회수 많은 상위 5개 종목들이다. \n\n" +
                            "아래와 같은 형태로 5개가 반환하게 된다.\n\n" +
                            "{\n\n" +
                            "        \"test_id\": 50,\n\n" +
                            "        \"testName\": \"12\",\n\n" +
                            "        \"views\": 0,\n\n" +
                            "        \"filepath\": \"/Users/raccon/study/project/Spring/racconworld_data/img/50/main\",\n\n" +
                            "        \"filedownload\": \"/q/50/main\"\n\n" +
                            "    },\n\n" +
                            "    {\n\n" +
                            "        \"test_id\": 51,\n\n" +
                            "        \"testName\": \"13\",\n\n" +
                            "        \"views\": 0,\n\n" +
                            "        \"filepath\": \"/Users/raccon/study/project/Spring/racconworld_data/img/51/main\",\n\n" +
                            "        \"filedownload\": \"/q/51/main\"\n\n" +
                            "    },")
    @GetMapping("/list/top5")
    public ResponseEntity<List<ShowTestDto>> getTest_top5() {
        List<ShowTestDto> testList_Top5 = testService.getTestListByTop5();
        return ResponseEntity.ok().body(testList_Top5);
    }

}
