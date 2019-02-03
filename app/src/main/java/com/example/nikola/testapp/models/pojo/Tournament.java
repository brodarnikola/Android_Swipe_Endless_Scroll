package com.example.nikola.testapp.models.pojo;

import java.util.ArrayList;

/**
 * Created by Deni Slunjski on 13.9.2016..
 */
public class Tournament {

    private int tournamentID;
    private String tournamentName;
    private ArrayList<Standing> standings;

    private int TourUID;
    private String ts;

    private int gameCount;

    public int getTourUID() {
        return TourUID;
    }

    public void setTourUID(int tourUID) {
        TourUID = tourUID;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public int getGameCount() {
        return gameCount;
    }

    public void setGameCount(int gameCount) {
        this.gameCount = gameCount;
    }

    public Tournament() {
        standings = new ArrayList<>();
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public ArrayList<Standing> getStandings() {
        return standings;
    }

    public void setStandings(ArrayList<Standing> standings) {
        this.standings = standings;
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(int tournamentID) {
        this.tournamentID = tournamentID;
    }
}
