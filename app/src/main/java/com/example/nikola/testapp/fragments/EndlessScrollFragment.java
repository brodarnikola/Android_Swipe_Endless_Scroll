package com.example.nikola.testapp.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nikola.testapp.R;
import com.example.nikola.testapp.Settings.Constants;
import com.example.nikola.testapp.Settings.MyApplication;
import com.example.nikola.testapp.adapters.categoryAdapter.CategoryAdapter;
import com.example.nikola.testapp.adapters.endlessScrollAdapter.EndlessScrollAdapter;
import com.example.nikola.testapp.customControls.EndlessRecyclerOnScrollListener;
import com.example.nikola.testapp.customControls.OnLoadMoreListener;
import com.example.nikola.testapp.customControls.PaginationScrollListener;
import com.example.nikola.testapp.models.pojo.TeamCategory;

import java.util.ArrayList;
import java.util.List;

public class EndlessScrollFragment extends Fragment {


    private RecyclerView categoryRecylerView;
    private ArrayList<TeamCategory> categoryLinkedHashMap;
    private EndlessScrollAdapter endlessScrollAdapter;

    private ProgressBar item_progress_bar;
    private int mLoadedItems = 0;

    private static final String TAG = "MainActivity";

    LinearLayoutManager linearLayoutManager;
    ProgressBar progressBar;

    private static final int PAGE_START = 0;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 3;
    private int currentPage = PAGE_START;


    public static EndlessScrollFragment newInstance() {
        EndlessScrollFragment fragment = new EndlessScrollFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_endless_scroll, container, false);

        categoryLinkedHashMap = (ArrayList<TeamCategory>) MyApplication.getInstance().get(Constants.teamCategories);

        categoryRecylerView = (RecyclerView) view.findViewById(R.id.recylerView);
        item_progress_bar = (ProgressBar) view.findViewById(R.id.item_progress_bar);

        progressBar = (ProgressBar) view.findViewById(R.id.item_progress_bar);

        endlessScrollAdapter = new EndlessScrollAdapter(getContext());

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        categoryRecylerView.setLayoutManager(linearLayoutManager);

        categoryRecylerView.setItemAnimator(new DefaultItemAnimator());

        categoryRecylerView.setAdapter(endlessScrollAdapter);

        categoryRecylerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                // mocking network delay for API call
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextPage();
                    }
                }, 1000);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });


        loadFirstPage();
        // mocking network delay for API call
        /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //loadFirstPage();
            }
        }, 100); */

        return view;
    }


    private void loadFirstPage() {
        Log.d(TAG, "loadFirstPage: ");
        //List<TeamCategory> movies = TeamCategory.createMovies(endlessScrollAdapter.getItemCount());
        progressBar.setVisibility(View.GONE);
        endlessScrollAdapter.addAll(categoryLinkedHashMap);

        if (currentPage <= TOTAL_PAGES) endlessScrollAdapter.addLoadingFooter();
        else isLastPage = true;

    }

    private void loadNextPage() {
        Log.d(TAG, "loadNextPage: " + currentPage);

        endlessScrollAdapter.removeLoadingFooter();
        isLoading = false;

        endlessScrollAdapter.addAll(categoryLinkedHashMap);

        if (currentPage != TOTAL_PAGES) endlessScrollAdapter.addLoadingFooter();
        else isLastPage = true;
    }


    private void addDataToList() {

        item_progress_bar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    TeamCategory teamCategory = new TeamCategory();
                    teamCategory.setRecyclerType(1);
                    teamCategory.setCategoryID((int) Math.random());
                    teamCategory.setTerm("SampleText : " + Math.random());
                    categoryLinkedHashMap.add(teamCategory);
                    mLoadedItems++;
                }
                endlessScrollAdapter.notifyDataSetChanged();
                item_progress_bar.setVisibility(View.GONE);
            }
        }, 1500);

    }


}