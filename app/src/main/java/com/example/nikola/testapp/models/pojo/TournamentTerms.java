package com.example.nikola.testapp.models.pojo;

/**
 * Created by Deni Slunjski on 13.7.2016..
 */
public class TournamentTerms {

    private int tournamentID;
    private String term;

    private String ts;

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(int tournamentID) {
        this.tournamentID = tournamentID;
    }
}
