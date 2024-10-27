package com.example.diningreview.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="USER")
@Data
public class User {

    @Id
    @GeneratedValue(strategy=GenrationType.IDENTITY)
    private Long id;

    @Column(name="DISPLAYNAME", unique=true)
    private String displayName;

    @Column(name="CITY")
    private String city;

    @Column(name="STATE")
    private String state;

    @Column(name="ZIPCODE")
    private String zipcode;

    @Column(name="PEANUT_ALLEGIE")
    private boolean peanutAllergie;

    @Column(name="EGG_ALLERGIE")
    private boolean eggAllergie;

    @Column(name="DAIRY_ALLERGIE")
    private boolean diaryAllergie;
}