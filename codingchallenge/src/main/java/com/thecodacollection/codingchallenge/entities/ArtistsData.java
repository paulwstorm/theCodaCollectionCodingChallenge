package com.thecodacollection.codingchallenge.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ARTISTS")
@Getter
@Setter
public class ArtistsData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artists_generator")
    @SequenceGenerator(name="artists_generator", sequenceName = "artists_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "GENRE")
    private String genre;
}