package com.example.minutemarker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class QueActivity extends AppCompatActivity {

    // called since the activity is starting
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // defining the UI file
        setContentView(R.layout.activity_que_detail);

        // we are grabbing our que word from the intent
        String queTitle = getIntent().getStringExtra("queTitle");

        // displaying que as page title
        setTitle(queTitle);
    }
}
