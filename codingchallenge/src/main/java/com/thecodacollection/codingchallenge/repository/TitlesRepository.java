package com.thecodacollection.codingchallenge.repository;

import com.thecodacollection.codingchallenge.entities.TitlesData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public interface TitlesRepository extends JpaRepository<TitlesData, Long> {

    public List<TitlesData> getAllByArtist(String artist);

}
