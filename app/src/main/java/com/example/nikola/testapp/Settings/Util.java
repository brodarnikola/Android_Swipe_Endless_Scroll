package com.example.nikola.testapp.Settings;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.List;

public class Util {


    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {

        try {
            float ratio = Math.min(
                    (float) maxImageSize / realImage.getWidth(),
                    (float) maxImageSize / realImage.getHeight());
            int width = Math.round((float) ratio * realImage.getWidth());
            int height = Math.round((float) ratio * realImage.getHeight());

            Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                    height, filter);
            return newBitmap;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    // I need to find here screen width. Later I will use this screen width for place holders on home screen
    public static void screenWidthforMobile(Activity context){

        // I need to find here screen width. Later I will use this screen width for place holders on home screen
        DisplayMetrics displaymetrics = new DisplayMetrics();

        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;

        MyApplication.getInstance().set(Constants.screenWidth, width);

        int index =0;

        List<Integer> widthDifferances= new ArrayList<>();
        widthDifferances.add(Math.abs(width - Constants.NEWS_PICTURE_WIDTH_FIRST));
        widthDifferances.add(Math.abs(width - Constants.NEWS_PICTURE_WIDTH_SECOND));
        widthDifferances.add(Math.abs(width - Constants.NEWS_PICTURE_WIDTH_THIRT));
        widthDifferances.add(Math.abs(width - Constants.NEWS_PICTURE_WIDTH_FOURTH));
        int differance = widthDifferances.get(0);
        for (int i=0;i<widthDifferances.size();i++){
            if(widthDifferances.get(i)<differance){
                differance=widthDifferances.get(i);
                index=i;
            }
        }
        int correctIndex = index+1;
        MyApplication.getInstance().set(Constants.screenWidthType, String.valueOf(correctIndex));
    }



}
