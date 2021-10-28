package com.thecodacollection.codingchallenge.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "VENUES")
@Getter
@Setter
public class VenuesData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venues_generator")
    @SequenceGenerator(name="venues_generator", sequenceName = "venues_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

}