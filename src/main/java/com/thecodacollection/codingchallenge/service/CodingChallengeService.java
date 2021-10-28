package com.thecodacollection.codingchallenge.service;


import com.thecodacollection.codingchallenge.entities.*;
import com.thecodacollection.codingchallenge.repository.ArtistRepository;
import com.thecodacollection.codingchallenge.repository.TitlesRepository;
import com.thecodacollection.codingchallenge.repository.VenuesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CodingChallengeService {

    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    TitlesRepository titlesRepository;
    @Autowired
    VenuesRepository venuesRepository;

    private Integer uploadPercentage;
    private String title;
    private Boolean uploading = false;

    public Boolean currentlyUploading() {
        return uploading;
    }

    public Integer currentUploadStatus() {
        Integer currentUploadPercentage = uploadPercentage;
        if (uploadPercentage == 100) {
            uploadPercentage = 0;
            uploading = false;
            title = null;
        } else if (this.uploading == true){
            uploadPercentage += 20;
        }

        return currentUploadPercentage;
    }

    public void uploadToAmazon(String title) throws InterruptedException {
        this.title = title;
        this.uploadPercentage = 0;
        this.uploading = true;
    }

    public void newConcert(ReqTitleDTO reqTitleDTO) {
        ArtistsData artistsData = artistRepository.getByName(reqTitleDTO.getArtist().getName());
        if (artistsData == null) {
            artistRepository.save(reqTitleDTO.getArtist());
        }

        VenuesData venuesData = venuesRepository.getByName(reqTitleDTO.getVenue().getName());
        if (venuesData == null) {
            venuesRepository.save(reqTitleDTO.getVenue());
        }

        TitlesData titlesData = titlesRepository.getByTitle(reqTitleDTO.getTitle().getTitle());
        if (titlesData == null) {
            titlesRepository.save(reqTitleDTO.getTitle());

            TitlesData newTitle = titlesRepository.getByTitle(reqTitleDTO.getTitle().getTitle());
            ViewCountsData viewCountsData = new ViewCountsData();
            viewCountsData.setTitleId(newTitle.getId());
            viewCountsData.setViewCount(0);
        }
    }
}
