package com.example.igroup.schoolinfo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.igroup.schoolinfo.R;

/**
 * Created by iGroup on 11/21/2017.
 */
public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashScreen.this.startActivity(new Intent(SplashScreen.this,MainActivity.class));
            }
        },SPLASH_TIME_OUT);

    }
}
