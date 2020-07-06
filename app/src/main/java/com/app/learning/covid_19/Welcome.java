package com.app.learning.covid_19;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Welcome extends AppCompatActivity {

    private static int duration=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeInt=new Intent(Welcome.this,Main2Activity.class);
                startActivity(homeInt);
                finish();
            }
        },duration);

    }
}
