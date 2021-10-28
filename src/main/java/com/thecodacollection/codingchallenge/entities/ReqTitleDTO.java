package com.thecodacollection.codingchallenge.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReqTitleDTO {

    private ArtistsData artist;
    private VenuesData venue;
    private TitlesData title;

}
