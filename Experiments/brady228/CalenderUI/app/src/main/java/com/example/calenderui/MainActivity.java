package com.example.calenderui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    String str = "today";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalendarView calendar = (CalendarView)findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String str = ( (month+1) + "/" +  dayOfMonth+ "/" + year);
                TextView d = findViewById(R.id.Week);
                TextView e = findViewById(R.id.selectedDate);
                d.setText(str);
                e.setText("no events");
                if(month==1 && dayOfMonth==11 && year==2021){
                    e.setText("3 events");
                }
                if(month==1 && dayOfMonth==10 && year==2021){
                    d.setText("Today!");
                }
            }
        });

    }



    public void viewEvents(View view) {

        //check for events from
    }



}