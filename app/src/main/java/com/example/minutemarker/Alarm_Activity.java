package com.example.minutemarker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Alarm_Activity extends AppCompatActivity {
    EditText timeHour;
    EditText timeMinute;
    Button setTime;
    Button setAlarm;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        timeHour = findViewById(R.id.etHour);
        timeMinute = findViewById(R.id.etMinute);
        setTime = findViewById(R.id.btnTime);
        setAlarm = findViewById(R.id.btnAlarm);

        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(Alarm_Activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        timeHour.setText(String.format("%02d", hourOfDay));
                        timeMinute.setText(String.format("%02d", minutes));
                    }
                }, currentHour, currentMinute, false);
                timePickerDialog.show();
            }
        });
        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!timeHour.getText().toString().isEmpty() && !timeMinute.getText().toString().isEmpty() ) {
                    Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                    intent.putExtra(AlarmClock.EXTRA_HOUR, Integer.parseInt(timeHour.getText().toString()));
                    intent.putExtra(AlarmClock.EXTRA_MINUTES, Integer.parseInt(timeMinute.getText().toString()));
                    intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Set by MinuteMarker");
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(Alarm_Activity.this, "There is no app to support this action", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Alarm_Activity.this, "Please Choose a Time", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}