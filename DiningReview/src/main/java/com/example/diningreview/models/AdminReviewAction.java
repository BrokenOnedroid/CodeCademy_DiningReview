package com.example.diningreview.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Data
@Table(name="ADMINREVIEWACTION")
public class AdminReviewAction {

    @Column(name="ACCEPTED")
    private boolean accepted;
}