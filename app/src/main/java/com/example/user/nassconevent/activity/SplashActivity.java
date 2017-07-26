package com.example.user.nassconevent.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.nassconevent.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent goToLoginActivityIntent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(goToLoginActivityIntent);
            }
        }, 4000);
    }
}
