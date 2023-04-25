package com.example.minutemarker;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
//extends app compact for features to work on older androids
public class SettingActivity extends AppCompatActivity {
    //the one instance of que detector permeating whole program.
    private QueDetector que_detector;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingactiviy);

        radioGroup = findViewById(R.id.group);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                QueDetector que_detector = (QueDetector) getIntent().getSerializableExtra("que_detector");
                switch (checkedId) {
                    case R.id.lightTheme:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;
                    case R.id.darkTheme:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;

                }
            }

        });


    }

}

