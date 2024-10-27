package com.example.diningreview.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SecondaryTable;


@Entity
@Table(name="REVIEW")
@SecondaryTable(name="USER", 
    pkJoinColumn=@PrimaryKeyJoinColumns(name="ID"))
@Data
public class User {

    @Id
    @GeneratedValue(strategy=GenrationType.IDENTITY)
    private Long id;

    @Column(name="USERNAME")


}