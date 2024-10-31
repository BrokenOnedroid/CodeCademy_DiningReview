package com.example.diningdeview.models;

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
    @GeneratedValue(strategy=GenrationType.IDENTITY)
    private Long id;

     @Column(name="NAME")
     private String name;

     @Column(name="ADRESS")
     private String adress;

     @Column(name="SCORE_PEANUT")
     private Integer scorePeanut;

     @Column(name="SCORE_EGG")
     private Integer scoreEgg;

    @Column(name="SCORE_DAIRY")
    private Integer scoreDairy;
}