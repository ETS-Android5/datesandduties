package com.example.datesandduties;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.datesandduties.app.AppController;
import com.example.datesandduties.dates;
import com.example.datesandduties.net_utils.Const;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONObject;

public class addEvent extends Activity implements View.OnClickListener{

    private EditText inputDate, inputTitle, inputTime, inputDesc;
    private Button addEvent;
    private TextView outError;
    private int eventID;

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
            outError.setText("Valid inputs Event will be created" + date);

            String suffix = "?owner=" + sign_in_page.getUsername()
                    +"&title=" + title
                    +"&description=" + description
                    +"&date=" + date
                    +"&time=" + time;
            suffix = Const.ADD_EVENT + suffix; //request out for adding event

            StringRequest addNewEvent = new StringRequest(Request.Method.GET, suffix,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, response.toString());
                            if(response.equals("Entry Saved!")){
                                requestLink(title);
                            }
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

    private void requestLink(String title){

        String idRequest = Const.FIND_EVENT + "/" + title;



        JsonObjectRequest findEventID = new JsonObjectRequest(Request.Method.GET, idRequest, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        // put parse the id of event here
                        eventID = 1;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: "+ error.getMessage());
            }
        });

        AppController.getInstance().addToRequestQueue(findEventID);


        String linkEvent = Const.LINK_EVENT + "?accountId=" + sign_in_page.getID() + "&eventId=" + eventID;
        //    string post request for linking id to event to user
        StringRequest linkEventToUser = new StringRequest(Request.Method.GET, linkEvent,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response.toString());
                        //if Success!
                        if(response.equals("Success!")){
                            //return to previous page
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