package com.thecodacollection.codingchallenge.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "VIEW_COUNTS")
@Getter
@Setter
public class ViewCountsData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "view_counts_generator")
    @SequenceGenerator(name="view_counts_generator", sequenceName = "view_counts_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE_ID")
    private Long titleId;

    @Column(name = "VIEW_COUNT")
    private Integer viewCount;

}
