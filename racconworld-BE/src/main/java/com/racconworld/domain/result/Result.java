package com.racconworld.domain.result;


import com.racconworld.domain.test.Test;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter  //  -> 빼기
@Builder
public class Result {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "test_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Test result_to_test;

    @Column
    private String filename; // 0202에 변경
    @Column
    private String filepath;

    public Result(Test result_to_test,String filepath , String filename ) {
        result_to_test.getResults().add(this);
        filename = filename;
        filepath = filepath;
    }
}
