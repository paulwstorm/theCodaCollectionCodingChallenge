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
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE_ID")
    private Integer titleId;

    @Column(name = "VIEW_COUNT")
    private Integer viewCount;

//    @OneToOne
////    @JoinColumn(name = "TITLE_ID", referencedColumnName = "ID")
//    TitlesData titlesData;

}
