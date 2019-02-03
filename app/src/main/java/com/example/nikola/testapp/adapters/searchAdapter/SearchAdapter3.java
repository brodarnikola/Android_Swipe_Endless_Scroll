package com.example.nikola.testapp.adapters.searchAdapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nikola.testapp.R;
import com.example.nikola.testapp.models.pojo.TeamCategory;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter3 extends  RecyclerView.Adapter<SearchAdapter3.MyViewHolder> {


    private Context context;
    public ArrayList<TeamCategory> categoryLinkedHashMap;
    FragmentTransaction ft;
    private List<TeamCategory> cartList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvFirst, tvSecond;
        public RelativeLayout viewBackground, viewForeground;

        public MyViewHolder(View view) {
            super(view);

            tvFirst = view.findViewById(R.id.tvFirst);
            tvSecond = view.findViewById(R.id.tvSecond);
            //viewBackground = view.findViewById(R.id.view_background);
            //viewForeground = view.findViewById(R.id.view_foreground);

            tvFirst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //onItemTouchListener.onButton1Click(v, getPosition());
                }
            });

            tvSecond.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //onItemTouchListener.onButton2Click(v, getPosition());
                }
            });
        }
    }

    public SearchAdapter3(Context context, FragmentManager fragmentManager, ArrayList<TeamCategory> mCartList) {

        this.categoryLinkedHashMap = mCartList;
        this.context = context;
        this.cartList = mCartList;

        ft = fragmentManager.beginTransaction();
    }

    @Override
    public SearchAdapter3.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_category_3, parent, false);

        return new SearchAdapter3.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchAdapter3.MyViewHolder holder, final int position) {

        switch (getItemViewType(position)) {
            case 1:
                bindNewsItem(holder, position);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {

        int type = -1;
        if (categoryLinkedHashMap.get(position).getTypeItem() == Item.TYPE_CATEGORY) {
            type = Item.TYPE_CATEGORY;
        }
        return type;
    }

    private void bindNewsItem(SearchAdapter3.MyViewHolder holder, final int position) {

        final View convertView = holder.itemView;

        final TeamCategory currentItem = (TeamCategory) categoryLinkedHashMap.get(position);

        TextView newsDay = holder.tvFirst;
        TextView newsMonth = holder.tvSecond;

        newsDay.setText(String.valueOf(currentItem.getCategoryID()));
        newsMonth.setText(currentItem.getTerm());
    }

    @Override
    public int getItemCount() {
        return categoryLinkedHashMap.size();
    }

    /*public void removeItem(int position) {
        categoryLinkedHashMap.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    } */

    public void restoreItem(Item item, int position) {
        //categoryLinkedHashMap.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }

    public void refresh(ArrayList<TeamCategory> mCategoryLinkedHashMap) {

        categoryLinkedHashMap = mCategoryLinkedHashMap;
        notifyDataSetChanged();
    }

    public void addItem(TeamCategory item, int position) {
        try {
            categoryLinkedHashMap.add(position, item);
            notifyItemInserted(position);
        } catch(Exception e) {
            Log.e("MainActivity", e.getMessage());
        }
    }

    public TeamCategory removeItem(int position) {
        TeamCategory item = null;
        try {
            item = categoryLinkedHashMap.get(position);
            categoryLinkedHashMap.remove(position);
            notifyItemRemoved(position);
        } catch(Exception e) {
            Log.e("search", e.getMessage());
        }
        return item;
    }


}



