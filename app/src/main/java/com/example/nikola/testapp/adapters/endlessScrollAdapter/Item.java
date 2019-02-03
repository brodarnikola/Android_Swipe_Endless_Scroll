package com.example.nikola.testapp.adapters.endlessScrollAdapter;

/**
 * Created by deno6 on 9.6.2017..
 */

public abstract  class Item {

    public static final int TYPE_CATEGORY = 1;
    public static final int TYPE_PROGRESS_BAR = 2;

    public abstract int getTypeItem();
}
