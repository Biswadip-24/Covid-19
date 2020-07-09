package com.app.learning.covid_19;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;





public class MainActivity extends AppCompatActivity {

    String Covid_URL_all="https://corona.lmao.ninja/v2/all?";
    String Covid_URL_country="https://corona.lmao.ninja/v2/countries/India";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This Activity is no longer Used

    }

}
