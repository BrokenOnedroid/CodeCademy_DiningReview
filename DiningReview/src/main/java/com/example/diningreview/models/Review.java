package com.example.diningreview.models;

import lombok.Data;

import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.SecondaryTable;

@Entity
@Table(name="REVIEW")
@Data
public class Review {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="USERNAME")
    @ManyToOne
    private User user;

    @Column(name="RESTAURANT")
    @ManyToOne
    private Restaurant restaurant;

    @Column(name="PEANUT_SCORE")
    private Integer peanutScore;

    @Column(name="EGG_SCORE")
    private Integer eggScore;

    @Column(name="DIARY_SCORE")
    private Integer diaryScore;

    @Column(name="COMMENTARY")
    private String commentary;

    @Column(name="STATUS")
    private ReviewStatus status;
}