package com.example.nikola.testapp.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.nikola.testapp.R;
import com.example.nikola.testapp.customControls.BottomNavigationViewBehavior;
import com.example.nikola.testapp.fragments.EndlessScrollFragment;
import com.example.nikola.testapp.fragments.FirstFragment;
import com.example.nikola.testapp.fragments.OptionsFragment;
import com.example.nikola.testapp.fragments.SearchFragment3;

public class Main2Activity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    selectedFragment = FirstFragment.newInstance();
                    break;
                case R.id.navigation_delete:
                    //mTextMessage.setText(R.string.title_dashboard);
                    selectedFragment = SearchFragment3.newInstance();
                    break;
                case R.id.navigation_touch_id:
                    //mTextMessage.setText(R.string.title_touch_id);
                    selectedFragment = OptionsFragment.newInstance();
                    break;

                case R.id.navigation_endless_scroll:
                    //mTextMessage.setText(R.string.title_touch_id);
                    selectedFragment = EndlessScrollFragment.newInstance();
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, selectedFragment);
            transaction.commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, FirstFragment.newInstance());
        transaction.commit();

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationViewBehavior());
    }


}
