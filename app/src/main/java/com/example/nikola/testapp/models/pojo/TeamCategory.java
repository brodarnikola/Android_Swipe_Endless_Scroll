package com.example.nikola.testapp.models.pojo;

import com.example.nikola.testapp.adapters.categoryAdapter.Item;

/**
 * Created by Deni Slunjski on 9.9.2016..
 */
public class TeamCategory extends Item {

    private int categoryID;
    private String term;
    private int recyclerType;

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getRecyclerType() {
        return recyclerType;
    }

    public void setRecyclerType(int recyclerType) {
        this.recyclerType = recyclerType;
    }


    @Override
    public int getTypeItem() {
        return Item.TYPE_CATEGORY;
    }
}