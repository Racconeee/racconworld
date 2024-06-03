package com.racconworld.domain.test;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ShowTestDto {

    private Long test_id;
    private String testName;            //테스트이름
    private Long views;                 //조회수
    private String filepath;
    private String filedownload;

}
