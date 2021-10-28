package com.thecodacollection.codingchallenge.repository;

import com.thecodacollection.codingchallenge.entities.VenuesData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenuesRepository extends JpaRepository<VenuesData, Long> {

    public VenuesData getByName(String name);

}

