package com.example.nikola.testapp.adapters.endlessScrollAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nikola.testapp.R;

public class TeamCategoryHolder  extends RecyclerView.ViewHolder {

    //TYPE NEWS
    public TextView textFirst;
    public TextView textSecond;
    // TYPE LOADING
    public ProgressBar progressBar;

    public TeamCategoryHolder(View itemView, int viewType) {
        super(itemView);
        if (viewType == Item.TYPE_CATEGORY) {
            textFirst = itemView.findViewById(R.id.tvFirst);
            textSecond = itemView.findViewById(R.id.tvSecond);
        }
        else {

            progressBar = itemView.findViewById(R.id.progressBar1);
        }
    }
}

