package com.example.diningreview.models;

import lombok.Data;

@Entity
@Data
@Table(name="ADMINREVIEWACTION")
private class AdminReviewAction {

    @Column(name="ACCEPTED")
    private boolean accepted;
}