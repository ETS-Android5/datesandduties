package com.example.datesandduties;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.datesandduties.app.AppController;
import com.example.datesandduties.dates;
import com.example.datesandduties.sign_in_page;
import com.example.datesandduties.net_utils.Const;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Map;
import java.util.Scanner;

public class addEvent extends Activity implements View.OnClickListener{

    private EditText inputDate, inputTitle, inputTime, inputDesc;
    private Button addEvent;
    private TextView outError;
    private int eventID;
    private String title;
    private String titled;
    private String eventid = "";
    private String TAG = addEvent.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        inputDate = (EditText) findViewById(R.id.inputDate);
        inputDate.setText(dates.getYear()+"-"+dates.getMonth()+"-"+dates.getDay());
        inputTitle = (EditText) findViewById(R.id.inputTitle);
        inputTime= (EditText) findViewById(R.id.inputTime);
        inputDesc = (EditText) findViewById(R.id.inputDescription);
        addEvent = (Button) findViewById(R.id.sendEvent);
        outError = (TextView) findViewById(R.id.errorOut);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.sendEvent:
                checkInputs();
                break;
        }
    }

    public void checkInputs(){
        String title, description, date, time;
        title = inputTitle.getText().toString();
        description = inputDesc.getText().toString();
        date = inputDate.getText().toString();
        time = inputTime.getText().toString();

        if(title.isEmpty()){
            outError.setText("Please insert a valid title");
        }
        else if(description.isEmpty()){
            outError.setText("Please enter a description");
        }
        else if(date.isEmpty()){
            outError.setText("Please enter a date");
        }
        else if(time.isEmpty()) {
            outError.setText("please enter time of event.");
        }

        else{
            JSONObject newEvent = new JSONObject();
            try {
                newEvent.put("owner", sign_in_page.getUsername());
                newEvent.put("title", title);
                newEvent.put("description", description);
                newEvent.put("date", date);
                newEvent.put("time", time);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            StringRequest addNewEvent = new StringRequest(Request.Method.POST, Const.ADD_EVENT,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, response.toString());
                                //outError.setText("Valid inputs Event will be created");
                            String getid = response.toString();
                            Scanner sc = new Scanner(getid);
                            sc.useDelimiter(": ");
                            sc.next();
                            eventID = sc.nextInt();
                            requestLink();
                        }
                    },
                    new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: "+ error.getMessage());
                    outError.setText(error.toString());
                }
            }){
                @Override
                public byte[] getBody() throws AuthFailureError {
                    return newEvent.toString().getBytes();
                }
                public String getBodyContentType() {
                    return "application/json";
                }
            };
            AppController.getInstance().addToRequestQueue(addNewEvent);
        }
    }

    private void requestLink(){
        Integer user = sign_in_page.getID();
        int userid = user;
        String linkEvent = Const.LINK_EVENT + "/" + userid + "/" + eventID;
        StringRequest linkEventToUser = new StringRequest(Request.Method.PUT, linkEvent,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response.toString());
                        startActivity(new Intent(addEvent.this, dates.class));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: "+ error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(linkEventToUser);
    }
}