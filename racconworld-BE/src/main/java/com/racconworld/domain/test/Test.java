package com.racconworld.domain.test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.racconworld.domain.quizquestion.QuizQuestion;
import com.racconworld.domain.result.Result;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Test {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private Long id;


    //이번 프로젝트에서 연관관계 매핑 할때 new Arraylist -< HashSet으로 변경했는데
    // 이유는
    //  중복된 데이터를 허용하지않기때문에 데이터 일관성 증가
    //  검색 , 삽입 부분에서 ArrayList는 순차적인 검색으로 시간이 시간이 느리지만
    //  검색 , 삽입 부분에서 HashSet는 해시기반의 데이터 구조로 O(1) 시간 복잡도를 가지게 된다.
    // -> 따라서 HashSet을 사용하자
    @JsonIgnore
    @OneToMany(mappedBy = "result_to_test",fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<Result> results = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "question_to_test",fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<QuizQuestion> questions = new ArrayList<>();

    private String testName;            //테스트이름
    private Long views;                 //조회수
    private int question_count;        //질문 개수
    @Column
    private String filepath;    //파일 저장경로
    @Column
    private String filedownload;   //파일 다운로드 경로

    //to -> entitiy
    public ShowTestDto toDTO(){
        return ShowTestDto.builder()
                .test_id(this.id)
                .testName(this.testName)
                .views(this.views)
                .build();
    }

    public Test(String testName , int question_count, String filepath , String filedownload) {
        this.testName = testName;
        this.views = 0L;
        this.question_count = question_count;
        this.filepath = filepath;
        this.filedownload = filedownload;
    }

    public void test_imgupload(String testName , int question_count, String filepath , String filedownload) {
        this.testName = testName;
        this.views = 0L;
        this.question_count = question_count;
        this.filepath = filepath;
        this.filedownload = filedownload;
    }

    public void addQuiz_question(QuizQuestion quizQuestion){
        questions.add(quizQuestion);
    }


}

