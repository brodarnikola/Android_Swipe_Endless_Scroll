package com.example.nikola.testapp.models.pojo;

import java.util.ArrayList;

/**
 * Created by Deni Slunjski on 13.9.2016..
 */
public class Category {

    private int categoryID;
    private String categoryName;
    private ArrayList<Tournament> tournamentUniques;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category() {
        tournamentUniques = new ArrayList<>();
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public ArrayList<Tournament> getTournamentUniques() {
        return tournamentUniques;
    }

    public void setTournament(ArrayList<Tournament> tournamentUniques) {
        this.tournamentUniques = tournamentUniques;
    }

}
