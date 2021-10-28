package com.thecodacollection.codingchallenge.service;


import com.thecodacollection.codingchallenge.entities.ReqTitleDTO;
import com.thecodacollection.codingchallenge.repository.ArtistRepository;
import com.thecodacollection.codingchallenge.repository.TitlesRepository;
import com.thecodacollection.codingchallenge.repository.VenuesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        } else {
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
        artistRepository.save(reqTitleDTO.getArtist());
        venuesRepository.save(reqTitleDTO.getVenue());
        titlesRepository.save(reqTitleDTO.getTitle());
    }
}
