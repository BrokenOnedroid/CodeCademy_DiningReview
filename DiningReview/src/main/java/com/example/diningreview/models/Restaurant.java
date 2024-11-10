package com.example.diningreview.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="RESTAURANT")
@Data
public class Restaurant {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

     @Column(name="NAME")
     private String name;

     @Column(name="ADDRESS")
     private String address;

     @Column(name="SCORE_PEANUT")
     private Integer scorePeanut;

     @Column(name="SCORE_EGG")
     private Integer scoreEgg;

    @Column(name="SCORE_DAIRY")
    private Integer scoreDairy;
}