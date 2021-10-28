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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "titles_generator")
    @SequenceGenerator(name="titles_generator", sequenceName = "titles_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "GENRE")
    private String genre;

    @Column(name = "ARTIST")
    private String artist;

    @Column(name = "VENUE")
    private String venue;

    @Column(name = "ACTIVE")
    private Boolean active;

}