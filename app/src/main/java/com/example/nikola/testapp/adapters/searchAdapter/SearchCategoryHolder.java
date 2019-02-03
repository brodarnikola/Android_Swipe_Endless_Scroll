package com.example.nikola.testapp.adapters.searchAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.nikola.testapp.R;

public class SearchCategoryHolder extends RecyclerView.ViewHolder {

    //TYPE NEWS
    public TextView textFirst;
    public TextView textSecond;

    public SearchCategoryHolder(View itemView, int viewType) {
        super(itemView);
        if (viewType == Item.TYPE_CATEGORY) {
            textFirst = itemView.findViewById(R.id.tvFirst);
            textSecond = itemView.findViewById(R.id.tvSecond);
        }
    }

}

