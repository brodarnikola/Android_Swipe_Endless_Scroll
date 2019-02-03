package com.example.nikola.testapp.models.pojo;

/**
 * Created by Deni Slunjski on 21.9.2016..
 */

public class TournamentUnique {

    public int getTourUID() {
        return tourUID;
    }

    public void setTourUID(int tourUID) {
        this.tourUID = tourUID;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    private int tourUID;
    private String term;
}
