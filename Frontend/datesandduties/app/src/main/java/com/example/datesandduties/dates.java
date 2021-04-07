package com.example.datesandduties;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.datesandduties.net_utils.Const;

import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class dates extends Activity implements View.OnClickListener{

    private static JSONArray UserEvents;
    private String TAG = dates.class.getSimpleName();
    private String tag_string_req = "event_Req";

    private static int Gday;
    private Button addEvent, viewEvents;

    private static int Gmonth;
    private static int Gyear;
    private CalendarView calendar;
    private TextView today, xEvents;

    private String user;
    //public Time intialDate = new Time();
    private JSONArray currentEvents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates);

        addEvent = (Button) findViewById(R.id.AddEvent);

        user = sign_in_page.getUsername();
        xEvents = (TextView) findViewById(R.id.numEvents);
        today = (TextView) findViewById(R.id.day);
        calendar = (CalendarView) findViewById(R.id.calendarView);
        Calendar cal = Calendar.getInstance();
        Gyear = cal.get(Calendar.YEAR);
        Gmonth = cal.get(Calendar.MONTH) + 1;
        Gday = cal.get(Calendar.DAY_OF_MONTH);
        xEvents.setText(countEvents());
        viewEvents = (Button) findViewById(R.id.editEvents);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Gyear = year;
                Gmonth = month+1;
                Gday = dayOfMonth;
                String str = (Gmonth+ "/" + Gday + "/" + Gyear);
                today.setText(str);

                //check date for events with username
                //count events for day

                try {
                    setCurrentEvents();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                xEvents.setText(countEvents());
            }
        });



    }

    private String countEvents(){ //day input

        //request events for user for day

        //count json length

        int count = UserEvents.length();
        String refund = "You have ";
        //say how many events are for that day
        if(count>1){
            refund+=  count + " events on ";
        }
        else if(count>0){
            refund+=  count + " event on ";
        }
        //if no events
        else{
            refund += "no events on ";
        }
        refund += Gmonth + "/" + Gday + "/" + Gyear;
        return refund;
    }

    public void setCurrentEvents() throws JSONException {


        //json array request for current events
        String url = Const.GET_EVENTS + "/" + sign_in_page.getUsername();
        //save input into private currentEvents
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        currentEvents = response;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: "+ error.getMessage());
            }
        });
        UserEvents = new JSONArray();
        JSONObject jason;
        String Data = Gmonth+"";
        if(Gday<10){
            Data += "0" + Gday + Gyear;
        }
        else{
            Data += Gday + Gyear;
        }
        for(int i = 0;i < currentEvents.length();i++){
            jason = currentEvents.getJSONObject(i);
            if(jason.getString("date").equals(Data)){
                UserEvents.put(jason);
            }
        }

        //return json array

    }

    public static int getDay(){
        return Gday;
    }
    public static int getMonth(){
        return Gmonth;
    }
    public static int getYear(){
        return Gyear;
    }
    public static void setYear(int year){
        Gyear = year;
    }
    public static void setMonth(int Month){
        Gmonth = Month;
    }
    public static void setDay(int Day){
        Gday = Day;
    }

    public static String displayDate(){
        return Gmonth+"/"+Gday+"/"+Gyear;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.AddEvent:
                startActivity(new Intent(dates.this, addEvent.class));
                break;
            case R.id.editEvents:
                startActivity(new Intent(dates.this, eventMain.class));
                break;
        }
    }


    public static JSONArray getEvents(){
        return UserEvents;
    }
}