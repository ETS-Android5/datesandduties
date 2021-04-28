package com.example.datesandduties;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.datesandduties.app.AppController;
import com.example.datesandduties.net_utils.Const;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;

public class addNewTask extends Activity implements View.OnClickListener {


    protected EditText inputDueDate, inputTitle, inputDesc, inputPriority;
    private Button addTask;
    private TextView outError;
    private int taskID;
    private String title;
    private String titled;
    private String TAG = com.example.datesandduties.addTask.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);
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
                createTask();

                break;

        }
    }

    public void createTask(){
        JSONObject jason = new JSONObject();
        try {
            jason.put("owner", sign_in_page.getUsername());
            jason.put("title", inputTitle.getText());
            jason.put("description", inputDesc.getText());
            jason.put("priority", inputPriority.getText());
            jason.put("due_date", inputDueDate.getText());
            jason.put("recurrence", "Daily");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringRequest addNewTask = new StringRequest(Request.Method.POST, Const.ADD_TASK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response.toString());
                        String getid = response.toString();
                        Scanner sc = new Scanner(getid);
                        sc.useDelimiter(": ");
                        sc.next();
                        taskID = sc.nextInt();
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
                return jason.toString().getBytes();
            }
            public String getBodyContentType() {
                return "application/json";
            }
        };
        AppController.getInstance().addToRequestQueue(addNewTask);


    }


    public void requestLink(){
        Integer user = sign_in_page.getID();
        int userid = user;
        String linkEvent = Const.LINK_TASK + "/" + userid + "/" + taskID;
        StringRequest linkEventToUser = new StringRequest(Request.Method.PUT, linkEvent,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response.toString());
                        startActivity(new Intent(addNewTask.this, Duties.class));
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