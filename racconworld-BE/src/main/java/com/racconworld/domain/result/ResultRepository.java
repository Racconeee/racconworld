package com.racconworld.domain.result;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result , Long> {
    @Query("SELECT r FROM Result r WHERE r.filepath = :filepath")
    Optional<Result> findByFilepath(@Param("filepath") String filepath);

}
