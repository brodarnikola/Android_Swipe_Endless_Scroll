package com.example.nikola.testapp.adapters.endlessScrollAdapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nikola.testapp.R;
import com.example.nikola.testapp.customControls.OnLoadMoreListener;
import com.example.nikola.testapp.models.pojo.TeamCategory;

import java.util.ArrayList;
import java.util.List;


public class EndlessScrollAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private List<TeamCategory> movies;
    private Context context;

    private boolean isLoadingAdded = false;

    public EndlessScrollAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<>();
    }

    public List<TeamCategory> getMovies() {
        return movies;
    }

    public void setMovies(List<TeamCategory> movies) {
        this.movies = movies;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.progress_item, parent, false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.list_row_category_3, parent, false);
        viewHolder = new MovieVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        TeamCategory TeamCategory = movies.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                MovieVH movieVH = (MovieVH) holder;

                movieVH.textView1.setText("" + TeamCategory.getCategoryID());
                movieVH.textView2.setText("" + TeamCategory.getTerm());
                break;
            case LOADING:
//                Do nothing
                break;
        }

    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == movies.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    /*
   Helpers
   _________________________________________________________________________________________________
    */

    public void add(TeamCategory mc) {
        movies.add(mc);
        notifyItemInserted(movies.size() - 1);
    }

    public void addAll(List<TeamCategory> mcList) {
        for (TeamCategory mc : mcList) {
            add(mc);
        }
    }

    public void remove(TeamCategory city) {
        int position = movies.indexOf(city);
        if (position > -1) {
            movies.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new TeamCategory());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = movies.size() - 1;
        TeamCategory item = getItem(position);

        if (item != null) {
            movies.remove(position);
            notifyItemRemoved(position);
        }
    }

    public TeamCategory getItem(int position) {
        return movies.get(position);
    }


   /*
   View Holders
   _________________________________________________________________________________________________
    */

    /**
     * Main list's content ViewHolder
     */
    protected class MovieVH extends RecyclerView.ViewHolder {
        private TextView textView1, textView2;

        public MovieVH(View itemView) {
            super(itemView);

            textView1 = (TextView) itemView.findViewById(R.id.tvFirst);
            textView2 = (TextView) itemView.findViewById(R.id.tvSecond);
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }


}
