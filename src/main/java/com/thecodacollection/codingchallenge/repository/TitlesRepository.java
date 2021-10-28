package com.thecodacollection.codingchallenge.repository;

import com.thecodacollection.codingchallenge.entities.TitlesData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public interface TitlesRepository extends JpaRepository<TitlesData, Long> {

    public List<TitlesData> getAllByArtist(String artist);

    public TitlesData getByTitle(String title);

    @Transactional
    @Modifying
    @Query(value = "UPDATE TitlesData SET venue = :venue WHERE title = :title")
    int updateTitleVenue(@Param("venue") String venue, @Param("title") String title);

    @Transactional
    @Modifying
    @Query(value = "UPDATE TitlesData t SET t.active = :activeStatus WHERE t.title = :title")
    int updateTitleActiveStatus(@Param("activeStatus") Boolean activeStatus, @Param("title") String title);

}
