package com.thecodacollection.codingchallenge.controller;

import com.thecodacollection.codingchallenge.entities.TitlesData;
import com.thecodacollection.codingchallenge.entities.ViewCountsData;
import com.thecodacollection.codingchallenge.repository.TitlesRepository;
import com.thecodacollection.codingchallenge.repository.ViewCountsRepository;
import com.thecodacollection.codingchallenge.service.CodingChallengeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class CodingChallengeController {

    @Autowired
    TitlesRepository titlesRepository;
    @Autowired
    ViewCountsRepository viewCountsRepository;
    @Autowired
    CodingChallengeService codingChallengeService;

    @RequestMapping("theCodaCollection")
    public String homepage() {
        return "Hello!";
    }

    @RequestMapping("/theCodaCollection/topFilms")
    public List<TitlesData> getTopFilms() {
        return titlesRepository.findAll();
    }

    @RequestMapping("/theCodaCollection/topArtists")
    public List<String> getViewCounts() {
        List<String> topArtists = viewCountsRepository.getArtistViews();
        return topArtists;
    }

    @RequestMapping("/theCodaCollection/{artist}")
    public List<TitlesData> getFilmsByArtist(@PathVariable("artist") String artist) {
        return titlesRepository.getAllByArtist(artist);
    }

    @PostMapping("/theCodaCollection/upload")
    public String uploadFilm(@RequestParam("title") String title) throws InterruptedException {
        Boolean currentlyUploading = codingChallengeService.currentlyUploading();
        if (currentlyUploading == false) {
            codingChallengeService.uploadToAmazon(title);
            return "Upload of " + title + " in progress!";
        } else {
            return "Another upload is in progress, please wait and try again";
        }
    }

    @RequestMapping("/theCodaCollection/uploadStatus")
    public String getUploadStatus() {
        String uploadStatus = String.valueOf(codingChallengeService.currentUploadStatus());
        return "current upload status: " + uploadStatus + "%";
    }

    @PostMapping("/theCodaCollection")
    public String addNewConcert() {

    }
}
