package com.example.nikola.testapp.activities;

import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.nikola.testapp.R;
import com.example.nikola.testapp.Settings.Constants;
import com.example.nikola.testapp.Settings.MyApplication;
import com.example.nikola.testapp.Settings.Util;
import com.example.nikola.testapp.models.parser.TermsParser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

public class SplashScreenActivity extends AppCompatActivity {

    private MyApplication myApp;
    private Context context;
    private SplashScreenActivity splashScreen;

    private RelativeLayout rlMain;

    private Animation mBounceAnimation;

    private AsyncTask<Void, Void, String> downloadData;

    private ImageView logo, ivBg;
    private HorizontalScrollView horizontalScroolView;

    private Long startTime, currentTime, resultTime;

    private AnimationSet set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        myApp = MyApplication.getInstance();
        context = this;
        splashScreen = this;

        Util.screenWidthforMobile(splashScreen);

        logo = (ImageView) findViewById(R.id.loading_logo);
        logo.setImageBitmap(Util.scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.splash_logo), (float) ((int) myApp.get(Constants.screenWidth) * 0.6), false));

        rlMain = (RelativeLayout) findViewById(R.id.rlMain);
        ivBg = (ImageView) findViewById(R.id.ivBg);

        Float width = Float.parseFloat(String.valueOf(ivBg.getDrawable().getIntrinsicWidth()));
        //Toast.makeText(context, "width is: " + width, Toast.LENGTH_SHORT).show();
        final ObjectAnimator animation = ObjectAnimator.ofFloat(ivBg, "translationX", -width);
        animation.setDuration(3000);
        animation.start();

        horizontalScroolView = (HorizontalScrollView) findViewById(R.id.horizontalScroolView);

        horizontalScroolView.setOnTouchListener(new OnTouch());

        //bounceAnimation();

        rotateAnimation();

    }

    private void rotateAnimation() {

        set = new AnimationSet(true);

        AlphaAnimation animation1 = new AlphaAnimation(0.2f, 1.0f);

        final Animation anim = new ScaleAnimation(0.7f, 1f, 0.7f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        final RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setInterpolator(new LinearInterpolator());

        set.addAnimation(animation1);
        set.addAnimation(anim);
        set.addAnimation(rotate);
        set.setDuration(2000);

        logo.startAnimation(set);

        downloadData = new AsyncTask<Void, Void, String>() {

            /*@Override
            protected void onPreExecute() {
                super.onPreExecute();
            } */

            @Override
            protected String doInBackground(Void... params) {
                startTime = System.currentTimeMillis();
                String response = "";
                try {

                    getScreenWidth();
                    //Thread.sleep(3000);
                    downloadTerms();
                    downloadTerms();
                    downloadTerms();
                    downloadTerms();
                    downloadTerms();
                    /* downloadTerms();
                    downloadTerms();
                    downloadTerms();
                    downloadTerms();
                    downloadTerms();
                    downloadTerms(); */

                } catch (Exception e) {
                    e.printStackTrace();
                    response = "Error";
                }
                return response;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                currentTime = System.currentTimeMillis();
                resultTime = currentTime - startTime;

                Toast.makeText(context, "Izvršavalo se " + resultTime, Toast.LENGTH_SHORT).show();

                ivBg.clearAnimation();

                set.cancel();

                // Check if we're running on Android 5.0 or higher
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    /*Intent intent = new Intent(splashScreen, LoginActivity.class);
                    // Apply activity transition
                    ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation(splashScreen, logo, "robot");

                    startActivity(intent, options.toBundle()); */
                    startActivity(new Intent(context, LoginActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                } else {
                    // Swap without transition
                }
            }
        };
        downloadData.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    private void getScreenWidth() {

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        int percent30 = width * 30 / 100;

        MyApplication.getInstance().set(Constants.percent_30_of_ScreenWidth, percent30);
    }

    private void bounceAnimation() {

        // Get the animation from resource file
        mBounceAnimation = AnimationUtils.loadAnimation(context, R.anim.bounce_animation);

        //MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 400);
        //mBounceAnimation.setInterpolator(interpolator);

        //logo.startAnimation(mBounceAnimation);

        /*ViewTreeObserver vto = ivBg.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                ivBg.getViewTreeObserver().removeOnPreDrawListener(this);
                finalHeight = ivBg.getMeasuredHeight();
                finalWidth = ivBg.getMeasuredWidth();
                //tv.setText("Height: " + finalHeight + " Width: " + finalWidth);
                return true;
            }
        }); */


        final ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(2000); // 1 second
        scaleAnimation.setRepeatCount(Animation.INFINITE);

        logo.startAnimation(scaleAnimation);

        downloadData = new AsyncTask<Void, Void, String>() {

            /*@Override
            protected void onPreExecute() {
                super.onPreExecute();
            } */

            @Override
            protected String doInBackground(Void... params) {
                startTime = System.currentTimeMillis();
                String response = "";
                try {

                    //Thread.sleep(3000);
                    downloadTerms();

                } catch (Exception e) {
                    e.printStackTrace();
                    response = "Error";
                }
                return response;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                currentTime = System.currentTimeMillis();
                resultTime = currentTime - startTime;

                Toast.makeText(context, "Izvršavalo se " + resultTime, Toast.LENGTH_SHORT).show();

                //animation.cancel();
                ivBg.clearAnimation();
                logo.clearAnimation();
                //scaleAnimation.cancel();

                //logo.setImageBitmap(Util.scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.splash_logo), (float) ((int) myApp.get(Constants.screenWidth) * 0.7), false));

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Check if we're running on Android 5.0 or higher
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    /*Intent intent = new Intent(splashScreen, LoginActivity.class);
                    // Apply activity transition
                    ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation(splashScreen, logo, "robot");

                    startActivity(intent, options.toBundle()); */
                    startActivity(new Intent(context, LoginActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                } else {
                    // Swap without transition
                }
                //mBounceAnimation.cancel();
            }
        };
        downloadData.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        /*RotateAnimation rotate = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(5000);
        rotate.setInterpolator(new LinearInterpolator());
        logo.startAnimation(rotate); */

        /*Rotate rotacija = new Rotate();

        TransitionValues startAnim = new TransitionValues();
        TransitionValues endAnim = new TransitionValues();

        rotacija.captureStartValues(startAnim);
        rotacija.captureEndValues(endAnim);


        long startAnimations = 1000;
        long endAnimations = 3000;

        rotacija.createAnimator(rlMain, startAnim,endAnim); */
        //logo.startAnimation(rotacija.createAnimator());

    }

    private void downloadTerms() {

        StringBuffer buffer = new StringBuffer();
        try {
            URL obj = new URL(Constants.mainTerms);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setReadTimeout(10000);
            con.setConnectTimeout(15000);

            con.setRequestMethod("GET");

            con.setRequestProperty("http.agent", "Unknown");

            con.setDoInput(true);

            con.connect();

            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // do your stuff here
                InputStream is = con.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(new GZIPInputStream(is)));
                //BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(is)));

                String line = null;
                while ((line = br.readLine()) != null) {
                    buffer.append(line);

                }
                is.close();
                br.close();
            }


        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Error", e.toString());
        }

        TermsParser termsParser = new TermsParser();
        try {
            termsParser.parseXmlFile(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    private class OnTouch implements View.OnTouchListener
    {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    }



}
