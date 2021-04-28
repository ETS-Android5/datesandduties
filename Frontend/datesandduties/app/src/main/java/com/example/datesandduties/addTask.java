package com.example.datesandduties;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.datesandduties.app.AppController;
import com.example.datesandduties.net_utils.Const;

import org.json.JSONException;
import org.json.JSONObject;

public class addTask extends Activity implements View.OnClickListener {



        private EditText inputDueDate, inputTitle, inputDesc, inputPriority;
        private Button addTask;
        private TextView outError;
        private int eventID;
        private String title;
        private String titled;

        private String TAG = com.example.datesandduties.addTask.class.getSimpleName();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_task);

            inputTitle = (EditText) findViewById(R.id.inputTitleTask);
            inputDesc = (EditText) findViewById(R.id.inputDescrTask);
            inputPriority = (EditText) findViewById(R.id.inputPriority);
            inputDueDate = (EditText) findViewById(R.id.inputDueDate);
            addTask = (Button) findViewById(R.id.sendTask);


            outError = (TextView) findViewById(R.id.outerror);


        }

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.sendTask:
                    checkInputs();
                    break;

            }
        }



        public void checkInputs(){

            String title, description, dueDate, priority;


            title = inputTitle.getText().toString();
            description = inputDesc.getText().toString();
            dueDate = inputDueDate.getText().toString();
            priority = inputPriority.getText().toString();

            if(title.isEmpty()){
                outError.setText("Please insert a valid title");
            }
            else if(description.isEmpty()){
                outError.setText("Please enter a description");
            }
            else if(dueDate.isEmpty()){
                outError.setText("Please enter a due date");
            }
            else if(priority.isEmpty()) {
                outError.setText("Please enter a priority");
            }

            //other stuff that checks if date and time are valid based on inputs to backend;
            else{


                String suffix = "?owner=" + sign_in_page.getUsername()
                        +"&title=" + title
                        +"&description=" + description
                        +"&priority=" + priority
                        +"&due_date=" + dueDate;
                //request out for adding event

                JSONObject newTask = new JSONObject();
                try {
                    newTask.put("owner", sign_in_page.getUsername());
                    newTask.put("title", title);
                    newTask.put("description", description);
                    newTask.put("priority", priority);
                    newTask.put("due_date", dueDate);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String url = Const.ADD_TASK + "?task=" + newTask.toString();
                titled = title;


                //outError.setText(newEvent.toString());
                JsonObjectRequest addNewTask = new JsonObjectRequest(Request.Method.POST, Const.ADD_TASK, newTask,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d(TAG, response.toString());
                                outError.setText("Valid inputs Event will be created");
                                requestLink();
                                startActivity(new Intent(com.example.datesandduties.addTask.this, dates.class));
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: "+ error.getMessage());
                    }
                });

                AppController.getInstance().addToRequestQueue(addNewTask);

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
                                startActivity(new Intent(com.example.datesandduties.addTask.this, dates.class));
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

