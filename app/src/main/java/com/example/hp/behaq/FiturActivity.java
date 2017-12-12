package com.example.hp.behaq;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.hp.behaq.Fragment.AnalyzeFragment;
import com.example.hp.behaq.Fragment.AskFragment;
import com.example.hp.behaq.Fragment.ProfileFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class FiturActivity extends AppCompatActivity {

    private TextView textView;
    private BottomBar bottomBar;
    private TextView mTextMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitur);

        bottomBar = (BottomBar) findViewById(R.id.navigation);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.navigation_analyze) {
                    AnalyzeFragment analyzeFragment = new AnalyzeFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, analyzeFragment);
                    fragmentTransaction.commit();
                } else if (tabId == R.id.navigation_ask) {
                    AskFragment askFragment = new AskFragment();
                    FragmentTransaction fragmentAskTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentAskTransaction.replace(R.id.content, askFragment);
                    fragmentAskTransaction.commit();
                } else if (tabId == R.id.navigation_profile) {
                    ProfileFragment newsFragment = new ProfileFragment();
                    FragmentTransaction fragmentNewsTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentNewsTransaction.replace(R.id.content, newsFragment);
                    fragmentNewsTransaction.commit();
                }
            }
        });
    }


   /* private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_analyze:
                    AnalyzeFragment analyzeFragment = new AnalyzeFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, analyzeFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_ask:
                    AskFragment askFragment = new AskFragment();
                    FragmentTransaction fragmentAskTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentAskTransaction.replace(R.id.content, askFragment);
                    fragmentAskTransaction.commit();
                    return true;
                case R.id.navigation_profile:
                    ProfileFragment newsFragment = new ProfileFragment();
                    FragmentTransaction fragmentNewsTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentNewsTransaction.replace(R.id.content, newsFragment);
                    fragmentNewsTransaction.commit();
                    return true;
            }
            return false;
        }

    };*/

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitur);

        AnalyzeFragment analyzeFragment = new AnalyzeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, analyzeFragment);
        fragmentTransaction.commit();

//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }*/

}
