package com.example.datesandduties;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.datesandduties.app.AppController;
import com.example.datesandduties.dates;
import com.example.datesandduties.net_utils.Const;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;

public class eventMain extends Activity implements View.OnClickListener{

    private TextView curDate;

    private Button leftb, rightb, edit, delete, up, down;

    private EditText title, desc, date, time;

    public int idEvent;

    private int dateOfNow;

    private String TAG = eventMain.class.getSimpleName();

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
        pullEvents();

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


                String currentEvent = Const.FIND_EVENT + "/" + title.getText().toString();
                StringRequest eventID = new StringRequest(Request.Method.GET, currentEvent,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, response.toString());
                                idEvent = Integer.parseInt(response.toString());
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: "+ error.getMessage());
                    }
                });
                AppController.getInstance().addToRequestQueue(eventID);







                String delEvent = Const.LINK_EVENT + "/" + idEvent ;
                //    string post request for linking id to event to user
                StringRequest deleteEvent = new StringRequest(Request.Method.DELETE, delEvent,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, response.toString());

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: "+ error.getMessage());
                    }
                });
                AppController.getInstance().addToRequestQueue(deleteEvent);


                //use id for delete event request
                break;
        }
    }


    private void pullEvents(){

        String url = Const.GET_EVENTS + "/" + sign_in_page.getID();
        JsonArrayRequest getEvents = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        desc.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: "+ error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(getEvents);
    }
}