package com.thecodacollection.codingchallenge.repository;

import com.thecodacollection.codingchallenge.entities.ArtistsData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistsData, Long> {

    public ArtistsData getByName(String name);

}

