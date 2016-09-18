package com.example.ssperandeo.countinggame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CountingActivity extends AppCompatActivity {

    private int maxNumbers = 10;
    private int currentNumber = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting);
    }

}
