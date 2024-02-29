package com.racconworld.domain.test;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Test , Long> {

//    Hibernate 다음과 같이 joinFetch를 여러번 사용하는 것을 허용하지않는다.
//    @Query("SELECT t FROM Test t LEFT JOIN FETCH t.questions LEFT JOIN FETCH t.choices WHERE t.id = :testId")
//    현재 우선 Test조회할때 choices만 조회하고 question는 따로 조회 방법 생각나면 리펙토링하자
//
    //t.choices 가 아님
    //이 메소드 없앰
    @Query("SELECT t FROM Test t ORDER BY t.views ASC")
    Slice<Test> findAllOrderByViewsAsc(Pageable pageable);

    @Query("SELECT t FROM Test t JOIN FETCH t.questions WHERE t.id = :id")
//    @Query("SELECT t FROM Test t JOIN FETCH t.questions q JOIN FETCH q.choices WHERE t.id = :id")
    Test findByIdWithQuestions(@Param("id") Long id);

    @Query("SELECT t FROM Test t WHERE t.filepath = :filepath")
    Optional<Test> findByFilepath(@Param("filepath") String filepath);

    @Query("SELECT t FROM Test t JOIN FETCH t.results WHERE t.id = :test_id")
    Optional<Test> findByIdAAndResults(Long test_id);

    @Modifying
    @Query("update Test t set t.views = t.views + 1 where t.id = :id")
    int updateTestByViews(Long id);


    void deleteById(Long id);
}
