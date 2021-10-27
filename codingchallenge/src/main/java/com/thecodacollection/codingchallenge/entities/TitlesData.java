package com.thecodacollection.codingchallenge.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TITLES")
@Getter
@Setter
public class TitlesData implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

//    @ManyToOne
    @Column(name = "GENRE")
    private String genre;

//    @ManyToOne
    @Column(name = "ARTIST")
    private String artist;

//    @ManyToOne
    @Column(name = "VENUE")
    private String venue;

    @Column(name = "ACTIVE")
    private Boolean active;

//    @OneToOne(mappedBy = "titleId")
//    private ViewCountsData viewCountsData;

}