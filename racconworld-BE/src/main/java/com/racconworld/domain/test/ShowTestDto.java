package com.racconworld.domain.test;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ShowTestDto {


    private Long test_id;
    private String testName;            //테스트이름
    private Long views;                 //조회수
    private String img_url;                 //테스트 그림



}
