package com.example.nikola.testapp.Settings;

import android.content.Context;
import android.content.SharedPreferences;

public class Settings {

    private  static  final String XML_FILE = "prefs.xml";


    private static final String KEY_GOOGLE_PROFILE_PICTURE = "google_profile_picture";
    public static final String DEFAULT_GOOGLE_PROFILE_PICTURE = "0";

    private static final String KEY_GOOGLE_USERNAME = "google_username";
    private static final String DEFAULT_GOOGLE_USERNAME = "0";



    private static SharedPreferences getPreferences(Context c) {
        SharedPreferences sp = c.getSharedPreferences(XML_FILE, Context.MODE_PRIVATE);
        return  sp;
    }


    public static String getGoogleProfilePicture(Context c) {
        return getPreferences(c).getString(KEY_GOOGLE_PROFILE_PICTURE, DEFAULT_GOOGLE_PROFILE_PICTURE);
    }

    public static void setGoogleProfilePicture(Context c, String value ) {
        SharedPreferences sp = getPreferences(c);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_GOOGLE_PROFILE_PICTURE, value);
        editor.commit();
    }

    public static String getGoogleUsername(Context c) {
        return getPreferences(c).getString(KEY_GOOGLE_USERNAME, DEFAULT_GOOGLE_USERNAME);
    }

    public static void setGoogleUsername(Context c, String value) {
        SharedPreferences sp = getPreferences(c);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_GOOGLE_USERNAME, value);
        editor.commit();
    }



}
