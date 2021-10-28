package com.thecodacollection.codingchallenge.controller;

import com.thecodacollection.codingchallenge.entities.ReqTitleDTO;
import com.thecodacollection.codingchallenge.entities.TitlesData;
import com.thecodacollection.codingchallenge.entities.VenuesData;
import com.thecodacollection.codingchallenge.repository.TitlesRepository;
import com.thecodacollection.codingchallenge.repository.VenuesRepository;
import com.thecodacollection.codingchallenge.repository.ViewCountsRepository;
import com.thecodacollection.codingchallenge.service.CodingChallengeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CodingChallengeController {

    @Autowired
    TitlesRepository titlesRepository;
    @Autowired
    ViewCountsRepository viewCountsRepository;
    @Autowired
    CodingChallengeService codingChallengeService;
    @Autowired
    VenuesRepository venuesRepository;

    @RequestMapping("/theCodaCollection/allFilms")
    public List<TitlesData> getTopFilms() {
        return titlesRepository.findAll();
    }

    @RequestMapping("/theCodaCollection/topArtists")
    public List<String> getViewCounts(@RequestParam(value = "top", required = false, defaultValue = "10") Integer top) {
        List<String> topArtists = viewCountsRepository.getArtistViews(top);
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
    public void addNewConcert(@RequestBody ReqTitleDTO reqTitleDTO) {
        codingChallengeService.newConcert(reqTitleDTO);
    }

    @PostMapping("/theCodaCollection/updateTitle")
    public void updateTitle(
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "activeStatus", required = false) Boolean activeStatus,
            @RequestBody(required = false) ReqTitleDTO reqTitleDTO
            ) {

        if (activeStatus != null) {
            Integer activeStatusUpdates = titlesRepository.updateTitleActiveStatus(activeStatus, title);
            log.info("Active Status updated for " + String.valueOf(activeStatusUpdates) + " titles!");
        };

        if (reqTitleDTO != null) {
            VenuesData venuesData = venuesRepository.getByName(reqTitleDTO.getVenue().getName());
            Integer venueUpdates;
            if (venuesData != null) {
                venueUpdates = titlesRepository.updateTitleVenue(reqTitleDTO.getVenue().getName(), title);
            } else {
                venuesRepository.save(reqTitleDTO.getVenue());
                venueUpdates = titlesRepository.updateTitleVenue(reqTitleDTO.getVenue().getName(), title);
            }
            log.info("Venue updated for " + String.valueOf(venueUpdates) + " titles!");
        }
    }
}
