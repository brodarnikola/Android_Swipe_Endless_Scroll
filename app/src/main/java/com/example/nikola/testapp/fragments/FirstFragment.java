package com.example.nikola.testapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nikola.testapp.R;
import com.example.nikola.testapp.Settings.Constants;
import com.example.nikola.testapp.Settings.MyApplication;
import com.example.nikola.testapp.adapters.categoryAdapter.CategoryAdapter;
import com.example.nikola.testapp.models.pojo.TeamCategory;

import java.util.ArrayList;

public class FirstFragment extends Fragment {


    private RecyclerView categoryRecylerView;
    private ArrayList<TeamCategory> categoryLinkedHashMap;
    private CategoryAdapter categoryAdapter;

    public static FirstFragment newInstance() {
        FirstFragment fragment = new FirstFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);

        categoryLinkedHashMap = (ArrayList<TeamCategory>) MyApplication.getInstance().get(Constants.teamCategories);

        categoryRecylerView = (RecyclerView) view.findViewById(R.id.recylerView);
        //categoryRecylerView.setNestedScrollingEnabled(false);

        categoryAdapter = new CategoryAdapter(categoryLinkedHashMap, view.getContext(), getFragmentManager());
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), 1);
        categoryRecylerView.setLayoutManager(layoutManager);
        categoryRecylerView.setItemAnimator(new DefaultItemAnimator());
        categoryRecylerView.setAdapter(categoryAdapter);

        return view;
    }
}
