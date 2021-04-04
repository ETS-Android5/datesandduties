package com.example.datesandduties;

import androidx.appcompat.app.AppCompatActivity;
import com.example.datesandduties.dates;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class eventMain extends Activity implements View.OnClickListener{

    private TextView curDate;

    private Button leftb, rightb, edit, delete, up, down;

    private EditText title, desc, date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_main);


        title = (EditText) findViewById(R.id.outTitle);
        desc = (EditText) findViewById(R.id.outDesc);
        date = (EditText) findViewById(R.id.outDate);
        time = (EditText) findViewById(R.id.outTime);

        up = (Button) findViewById(R.id.upButt);
        down = (Button) findViewById(R.id.downButt);
        leftb= (Button) findViewById(R.id.left);
        rightb = (Button) findViewById(R.id.right);
        edit = (Button) findViewById(R.id.editEvents);
        delete = (Button) findViewById(R.id.delEvent);
        setCurDate();

        //function to display all events

    }
    public void setCurDate() {
        curDate = (TextView) findViewById(R.id.dayOfEvents);
        curDate.setText(dates.displayDate());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left:
                if(dates.getDay()>1){
                    dates.setDay(dates.getDay()-1);
                }
                else if(dates.getMonth()>1){
                    dates.setMonth(dates.getMonth()-1);
                    int d = dates.getMonth();
                    if(d==9||d==4||d==6||d==11){
                        dates.setDay(30);
                    }
                    else if(d==2){
                        if(dates.getYear()%4 ==0){
                            dates.setDay(29);
                        }
                        else{
                            dates.setDay(28);
                        }
                    }
                    else{
                        dates.setDay(31);
                    }
                }
                else{
                    dates.setYear(dates.getYear()-1);
                    dates.setMonth(12);
                    dates.setDay(31);
                }
                setCurDate();
                break;

            case R.id.right:

                int m = dates.getMonth();
                int d = dates.getDay();
                int y = dates.getYear();
                if(m==9||m==4||m==6||m==11){
                    if(d==30){
                        dates.setDay(1);
                        if(m+1==13){
                            dates.setYear(y+1);
                            dates.setMonth(1);
                        }
                        else{
                            dates.setMonth(m+1);
                        }
                    }
                    else{
                        dates.setDay(d+1);
                    }
                }
                else if(m==2){
                    if(d==29&&y%4==0){
                        dates.setDay(1);
                        dates.setMonth(3);
                    }
                    else if(d==28&&y%4!=0){
                        dates.setDay(1);
                        dates.setMonth(3);
                    }
                    else{
                        dates.setDay(d+1);
                    }

                }
                else{
                    if(d==31){
                        dates.setDay(1);
                        if(m+1==13){
                            dates.setYear(y+1);
                            dates.setMonth(1);
                        }
                        else{
                            dates.setMonth(m+1);
                        }
                    }
                    else{
                            dates.setDay(d+1);
                    }

                }
                setCurDate();
                break;

            case R.id.editEvents:

                String newTitle, newDesc, newDate, newTime;

                newTitle = title.getText().toString();
                newDesc = desc.getText().toString();
                newDate = date.getText().toString();
                newTime = time.getText().toString();
                //insert code to update the event



                break;

            case R.id.delEvent:

                break;
        }
    }
}