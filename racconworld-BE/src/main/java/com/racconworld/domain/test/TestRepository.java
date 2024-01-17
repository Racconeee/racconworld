package com.racconworld.domain.test;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface TestRepository extends JpaRepository<Test , Long> {

//    Hibernate 다음과 같이 joinFetch를 여러번 사용하는 것을 허용하지않는다.
//    @Query("SELECT t FROM Test t LEFT JOIN FETCH t.questions LEFT JOIN FETCH t.choices WHERE t.id = :testId")
//    현재 우선 Test조회할때 choices만 조회하고 question는 따로 조회 방법 생각나면 리펙토링하자
//
    @Query("SELECT t FROM Test t LEFT JOIN FETCH t.choices WHERE t.id = :testId")
    Optional<Test> findTestWithQuestionsAndChoicesById(@Param("testId") Long testId);

    @Query("SELECT t FROM Test t ORDER BY t.views ASC")
    Slice<Test> findAllOrderByViewsAsc(Pageable pageable);

}
