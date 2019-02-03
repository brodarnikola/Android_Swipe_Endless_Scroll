package com.example.nikola.testapp.adapters.categoryAdapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.example.nikola.testapp.R;
import com.example.nikola.testapp.models.pojo.Category;
import com.example.nikola.testapp.models.pojo.TeamCategory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<TeamCategoryHolder> {

    private Context context;

    private ArrayList<TeamCategory> categoryLinkedHashMap;

    FragmentTransaction ft;

    public CategoryAdapter( ArrayList<TeamCategory> mCategoryLinkedHashMap, Context context, FragmentManager fragmentManager) {

        this.categoryLinkedHashMap = mCategoryLinkedHashMap;
        this.context = context;
        ft = fragmentManager.beginTransaction();
    }

    @Override
    public int getItemViewType(int position) {

        int type = -1;
        if (categoryLinkedHashMap.get(position).getTypeItem() == Item.TYPE_CATEGORY) {
            type = Item.TYPE_CATEGORY;
        }
        return type;
    }

    @Override
    public TeamCategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        if (viewType == Item.TYPE_CATEGORY) {
            view = LayoutInflater.from(context).inflate(R.layout.list_row_category_3, parent, false);
        }
        return new TeamCategoryHolder(view, viewType);
    }


    @Override
    public void onBindViewHolder(TeamCategoryHolder holder, int position) {

        switch (getItemViewType(position)) {
            case 1:
                bindNewsItem(holder, position);
                break;
        }
    }


    private void bindNewsItem(TeamCategoryHolder holder, final int position) {

        final View convertView = holder.itemView;

        final TeamCategory currentItem = (TeamCategory) categoryLinkedHashMap.get(position);

        TextView newsDay = holder.textFirst;
        TextView newsMonth = holder.textSecond;

        newsDay.setText(String.valueOf(currentItem.getCategoryID()));
        newsMonth.setText(currentItem.getTerm());
    }


    @Override
    public int getItemCount() {
        return categoryLinkedHashMap.size();
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(true);
    }





}


