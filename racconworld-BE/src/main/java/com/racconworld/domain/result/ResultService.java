package com.racconworld.domain.result;


import com.racconworld.domain.test.Test;
import com.racconworld.domain.test.TestRepository;
import com.racconworld.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResultService {

    private final ResultRepository resultRepository;
    private final TestRepository testRepository;

    /*
    * 예외처리
    * 1. 해당 테스트가 있는지 먼저 판단
    * 2. 점수에 해당하는 파일이 존재하는지 확인
    *
    * 예외가 발생하지않는다면 ShowResultDto를 통해서 반환한다.
    * */
    public ShowResultDto show_result(Long test_id , String score){


        Test byId = testRepository.findByIdAAndResults(test_id).orElseThrow( () -> new CustomException("해당 테스트가 없습니다."));

        Optional<Result> filteredResult = byId.getResults()
                .stream()
                .filter(result -> result.getScore().equals(score))
                .findFirst();

        Result result = filteredResult.orElseThrow(() -> new CustomException("점수에 대한 파일이 존재하지않습니다."));

        ShowResultDto dto = new ShowResultDto(result.getFilepath() , result.getFiledownload());

        return dto;
    }

}
