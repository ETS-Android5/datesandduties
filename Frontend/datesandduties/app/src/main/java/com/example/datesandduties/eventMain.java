package com.example.datesandduties;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.datesandduties.app.AppController;
import com.example.datesandduties.dates;
import com.example.datesandduties.net_utils.Const;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class eventMain extends Activity implements View.OnClickListener{

    private TextView curDate;

    private Button leftb, rightb, edit, delete, up, down;

    private EditText title, desc, date, time;

    public int idEvent;
    public int eventNum = 0;
    public int totalEvents = 0;

    private int dateOfNow;
    public JSONArray currentEvents = new JSONArray();
    public JSONObject displayEvent = new JSONObject();
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
        changeDate();
        setCurDate();

        //currentEvents = new JSONArray();
    }
    @SuppressLint("NewApi")
    public void setCurDate() {
        showEvent();
    }

    @SuppressLint("NewApi")
    public void changeDate(){
        curDate = (TextView) findViewById(R.id.dayOfEvents);
        curDate.setText(dates.displayDate());
        for(int i = 0; i<100;i++){
            currentEvents.remove(i);
        }
        currentEvents = dates.getEvents();
        totalEvents = currentEvents.length();
        eventNum = 0;
        if(totalEvents == 0){
            title.setText("");
            desc.setText("");
            date.setText("");
            time.setText("");
        }
    }

    public void showEvent() {
            try {
                displayEvent = new JSONObject();
                displayEvent = currentEvents.getJSONObject(eventNum);
                title.setText(displayEvent.getString("title"));
                desc.setText(displayEvent.getString("description"));
                date.setText(displayEvent.getString("date"));
                time.setText(displayEvent.getString("time"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }



    @SuppressLint("NewApi")
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
                for(int i = 0; i<totalEvents+1;i++){
                    currentEvents.remove(i);
                }
                changeDate();
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
                for(int i = 0; i<totalEvents+1;i++){
                    currentEvents.remove(i);
                }
                changeDate();
                setCurDate();
                break;

            case R.id.editEvents:

                String newTitle, newDesc, newDate, newTime, owner = null;
                int id = -1;

                newTitle = title.getText().toString();
                newDesc = desc.getText().toString();
                newDate = date.getText().toString();
                newTime = time.getText().toString();
                //insert code to update the event
                try {
                    id = displayEvent.getInt("id");
                    owner = displayEvent.getString("owner");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                
                JSONObject edit = new JSONObject();
                try {
                    edit.put("title", newTitle);
                    edit.put("description", newDesc);
                    edit.put("date", newDate);
                    edit.put("time", newTime);
                    edit.put("id", id);
                    edit.put("owner", owner);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest editEvent = new JsonObjectRequest(Request.Method.PUT, Const.EDIT_EVENT, edit,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d(TAG, response.toString());
                                changeDate();
                                setCurDate();
                            }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: "+ error.getMessage());
                    }
                });
                AppController.getInstance().addToRequestQueue(editEvent);

                break;

            case R.id.delEvent:
                int delID = -1;
                try {
                    delID = displayEvent.getInt("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String remove = Const.REM_EVENT + "/" + sign_in_page.getID() + "/" + delID;
                StringRequest removeEvent = new StringRequest(Request.Method.PUT, remove,
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
                AppController.getInstance().addToRequestQueue(removeEvent);



                String delEvent = Const.DEL_EVENT + "/" + delID;
                //    string post request for linking id to event to user
                StringRequest deleteEvent = new StringRequest(Request.Method.DELETE, delEvent,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, response.toString());
                                currentEvents.remove(eventNum);
                                changeDate();
                                setCurDate();
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

            case R.id.downButt:
                cycleEventsDown();
                break;
            case R.id.upButt:
                cycleEventsUp();
                break;
        }
    }

    public void cycleEventsDown(){
        eventNum++;
        if(totalEvents<eventNum){
            eventNum = 0;
        }
        setCurDate();
    }
    public void cycleEventsUp(){
        eventNum--;
        if(eventNum<0){
            eventNum = totalEvents-1;
        }
        setCurDate();
    }

}