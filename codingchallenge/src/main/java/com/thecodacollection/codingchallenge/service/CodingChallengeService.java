package com.thecodacollection.codingchallenge.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class CodingChallengeService {

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
            uploadPercentage += 10;
        }

        return currentUploadPercentage;
    }

    public void uploadToAmazon(String title) throws InterruptedException {
        this.title = title;
        this.uploadPercentage = 0;
        this.uploading = true;
    }
}
