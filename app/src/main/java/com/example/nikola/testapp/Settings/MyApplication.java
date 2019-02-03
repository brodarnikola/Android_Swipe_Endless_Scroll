package com.example.nikola.testapp.Settings;

import android.app.Application;
import android.content.Context;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class MyApplication extends Application {


    private static MyApplication instance;
    public final HashMap<String, Object> items = new HashMap<String, Object>();

    private String homeMatchTourID;
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        context = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }


    public void set(String key, Object value) {
        this.items.put(key, value);
    }

    public Object get(String key) {
        return this.items.get(key);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }



}
