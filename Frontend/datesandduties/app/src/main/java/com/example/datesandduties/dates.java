package com.example.datesandduties;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

public class dates extends Activity {
    /**
     * class handles the calendar view, shows the month and date
     */

    private CalendarView calendar;
    private TextView today;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * creates monthly calendar view, calculates month and day setup
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates);

        today = (TextView) findViewById(R.id.day);
        calendar = (CalendarView) findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String str = ((month+1)+ "/" + dayOfMonth + "/" + year);
                today.setText(str);
            }
        });



    }





}