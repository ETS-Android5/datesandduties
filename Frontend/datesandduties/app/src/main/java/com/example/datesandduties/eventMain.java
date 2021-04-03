package com.example.datesandduties;

import androidx.appcompat.app.AppCompatActivity;
import com.example.datesandduties.dates;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class eventMain extends Activity {

    private TextView curDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_main);

        curDate = (TextView) findViewById(R.id.dayOfEvents);
        curDate.setText(dates.getMonth() + "/" + dates.getDay() + "/" + dates.getYear());

        //function to display all events

    }





}