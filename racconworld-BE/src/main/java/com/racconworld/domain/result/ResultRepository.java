package com.racconworld.domain.result;

import com.racconworld.domain.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result , Long> {
//    boolean existsByFilepath(String filepath);


    @Query("SELECT r FROM Result r WHERE r.filepath = :filepath")
    Optional<Result> findByFilepath(@Param("filepath") String filepath);

}
