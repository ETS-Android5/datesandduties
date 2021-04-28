package com.example.datesandduties;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.datesandduties.app.AppController;
import com.example.datesandduties.net_utils.Const;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class addEvent extends Activity implements View.OnClickListener{

    protected EditText inputDate, inputTitle, inputTime, inputDesc;
    private Button addEvent;
    private TextView outError;
    private int eventID;
    private String title;
    private String titled;

    private String TAG = addEvent.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        inputDate = (EditText) findViewById(R.id.inputDate);
        inputDate.setText(dates.displayDate());
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

        //other stuff that checks if date and time are valid based on inputs to backend;
        else{


            String suffix = "?owner=" + sign_in_page.getUsername()
                    +"&title=" + title
                    +"&description=" + description
                    +"&date=" + date
                    +"&time=" + time;
             //request out for adding event

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

            String url = Const.ADD_EVENT + "?event=" + newEvent.toString();
            titled = title;
            /*
            StringRequest addNewEvent = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, response.toString());
                            outError.setText("Valid inputs Event will be created" + date);
                            requestLink(title);

                            //startActivity(new Intent(addEvent.this, dates.class));
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: "+ error.getMessage());
                }
            });*/

            //outError.setText(newEvent.toString());
            JsonObjectRequest addNewEvent = new JsonObjectRequest(Request.Method.POST, Const.ADD_EVENT, newEvent,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d(TAG, response.toString());
                                outError.setText("Valid inputs Event will be created");
                                requestLink();
                                startActivity(new Intent(addEvent.this, dates.class));
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: "+ error.getMessage());
                }
            });

            AppController.getInstance().addToRequestQueue(addNewEvent);

        }


    }

    private void requestLink(){

        String idRequest = Const.FIND_EVENT + "/" + titled;
        outError.setText("Fail link1");
        JsonObjectRequest findEventID = new JsonObjectRequest(Request.Method.GET, idRequest, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        // put parse the id of event here

                        String eventid = null;
                        try {
                            eventid = response.getString("id");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Integer eventnum = Integer.parseInt(eventid);
                        eventID = eventnum;



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: "+ error.getMessage());
            }
        });

        /*JsonObjectRequest findEventID = new JsonObjectRequest(Request.Method.GET, idRequest, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        // put parse the id of event here
                        try {
                            JSONObject event = response.getJSONObject("this");
                            String eventid = event.getString("eventID");
                            eventID = Integer.parseInt(eventid);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: "+ error.getMessage());
            }
        });*/

        AppController.getInstance().addToRequestQueue(findEventID);
        outError.setText("Fail link2");
        Integer user = sign_in_page.getID();
        int userid = user;
        String linkEvent = Const.LINK_EVENT + "/" + userid + "/" + eventID;
        //    string post request for linking id to event to user
        StringRequest linkEventToUser = new StringRequest(Request.Method.PUT, linkEvent,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response.toString());
                        //if Success!
                        if(response.equals("Success!")){
                            //return to previous page
                            startActivity(new Intent(addEvent.this, dates.class));
                        }
                        //else nothing this should work
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