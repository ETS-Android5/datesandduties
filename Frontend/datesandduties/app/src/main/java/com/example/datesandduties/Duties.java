package com.example.datesandduties;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.datesandduties.app.AppController;
import com.example.datesandduties.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Duties extends Activity implements View.OnClickListener{

    private Button edit, delete, up, down;

    private EditText title, desc, date, priority;

    public int idTask;
    public int taskNum = 0;
    public int totalTasks = 0;
    public int userID;

    private int dateOfNow;
    public JSONArray currentTasks = new JSONArray();
    public JSONObject displayTask = new JSONObject();
    private String TAG = Duties.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duties);


        title = (EditText) findViewById(R.id.outTitleT);
        desc = (EditText) findViewById(R.id.outDescription);
        date = (EditText) findViewById(R.id.outDueDate);
        priority = (EditText) findViewById(R.id.outPriority);

        userID = sign_in_page.getID();
        String url = Const.GET_TASKS + "/" + userID;

        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        currentTasks = response;
                        totalTasks = currentTasks.length();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: "+ error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(req);
        showTask();
    }

    public void showTask(){
        try {
            displayTask = currentTasks.getJSONObject(taskNum);
            title.setText(displayTask.getString("title"));
            desc.setText(displayTask.getString("description"));
            date.setText(displayTask.getString("due_date"));
            priority.setText(displayTask.getString("priority"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.upButton:
                taskNum--;
                if(taskNum<0){
                    taskNum = totalTasks-1;
                }
                showTask();
                break;
            case R.id.downButton:
                taskNum++;
                if(taskNum>totalTasks-1){
                    taskNum = 0;
                }
                showTask();
                break;
            case R.id.addTask:
                startActivity(new Intent(Duties.this, addNewTask.class));
                break;
            case R.id.refresh:
                taskNum = 0;
                reload();
                showTask();
                break;
            case R.id.homeButt:
                startActivity(new Intent(Duties.this, homePage.class));
                break;
            case R.id.delTask:
                deleteCurrentTask();
                break;
        }

    }

    public void deleteCurrentTask(){
        int currTask = -1;
        try {
            currTask = displayTask.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        String remove = Const.REM_TASK + "/" + sign_in_page.getID() + "/" + currTask;
        StringRequest removeTaskLink = new StringRequest(Request.Method.PUT, remove,
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
        AppController.getInstance().addToRequestQueue(removeTaskLink);

        String delEvent = Const.DEL_TASK + "/" + currTask;
        //    string post request for linking id to event to user
        StringRequest deleteTask = new StringRequest(Request.Method.DELETE, delEvent,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response.toString());
                        reload();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: "+ error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(deleteTask);

    }


    public void reload(){
        String url = Const.GET_TASKS + "/" + userID;
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        currentTasks = response;
                        totalTasks = currentTasks.length();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: "+ error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(req);
    }

}