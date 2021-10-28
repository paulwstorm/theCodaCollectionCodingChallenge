package com.thecodacollection.codingchallenge.repository;

import com.thecodacollection.codingchallenge.entities.ViewCountsData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewCountsRepository extends JpaRepository<ViewCountsData, Long> {

    @Query(value = "SELECT T.ARTIST AS ARTIST " +
            "FROM VIEW_COUNTS V " +
            "JOIN TITLES T ON T.ID = V.TITLE_ID " +
            "WHERE ARTIST IS NOT NULL " +
            "GROUP BY ARTIST " +
            "ORDER BY SUM(V.VIEW_COUNT) DESC " +
            "LIMIT ?1",
    nativeQuery = true)
    public List<String> getArtistViews(Integer top);

}

