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

public class taskMain extends Activity implements View.OnClickListener{

    private TextView curDate;

    private Button leftb, rightb, edit, delete, up, down;

    private EditText title, desc, date, priority;

    public int idTask;
    public int taskNum = 0;
    public int totalTasks = 0;

    private int dateOfNow;
    public JSONArray currentTasks = new JSONArray();
    public JSONObject displayTask = new JSONObject();
    private String TAG = eventMain.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_main);


        title = (EditText) findViewById(R.id.outTitleT);
        desc = (EditText) findViewById(R.id.outDescription);
        date = (EditText) findViewById(R.id.outDueDate);
        priority = (EditText) findViewById(R.id.outPriority);

        up = (Button) findViewById(R.id.upButton);
        down = (Button) findViewById(R.id.downButton);
        leftb= (Button) findViewById(R.id.leftT);
        rightb = (Button) findViewById(R.id.rightT);
        edit = (Button) findViewById(R.id.editTasks);
        delete = (Button) findViewById(R.id.delTask);
        changeDate();
        setCurDate();

        //currentEvents = new JSONArray();
    }
    @SuppressLint("NewApi")
    public void setCurDate() {
        showTask();
    }

    @SuppressLint("NewApi")
    public void changeDate(){
        curDate = (TextView) findViewById(R.id.dayOfTasks);
        curDate.setText(dates.displayDate());
        for(int i = 0; i<100;i++){
            currentTasks.remove(i);
        }
        //currentTasks = dates.getTasks();
        totalTasks = currentTasks.length();
        taskNum = 0;
        if(totalTasks == 0){
            title.setText("");
            desc.setText("");
            date.setText("");
            priority.setText("");
        }
    }

    public void showTask() {
        try {
            displayTask = new JSONObject();
            displayTask = currentTasks.getJSONObject(taskNum);
            title.setText(displayTask.getString("title"));
            desc.setText(displayTask.getString("description"));
            date.setText(displayTask.getString("date"));
            priority.setText(displayTask.getString("priority"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.leftT:
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
                for(int i = 0; i<totalTasks+1;i++){
                    currentTasks.remove(i);
                }
                changeDate();
                setCurDate();
                break;

            case R.id.rightT:

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
                for(int i = 0; i<totalTasks+1;i++){
                    currentTasks.remove(i);
                }
                changeDate();
                setCurDate();
                break;

            case R.id.editTasks:

                String newTitle, newDesc, newDate, newPriority, owner = null;
                int id = -1;

                newTitle = title.getText().toString();
                newDesc = desc.getText().toString();
                newDate = date.getText().toString();
                newPriority = priority.getText().toString();
                //insert code to update the event
                try {
                    id = displayTask.getInt("id");
                    owner = displayTask.getString("owner");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JSONObject edit = new JSONObject();
                try {
                    edit.put("title", newTitle);
                    edit.put("description", newDesc);
                    edit.put("due_date", newDate);
                    edit.put("priority", newPriority);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest editTask = new JsonObjectRequest(Request.Method.PUT, Const.EDIT_TASK, edit,
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
                AppController.getInstance().addToRequestQueue(editTask);

                break;

            case R.id.delTask:
                int delID = -1;
                try {
                    delID = displayTask.getInt("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String remove = Const.REM_TASK + "/" + sign_in_page.getID() + "/" + delID;
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



                String delTask = Const.DEL_TASK + "/" + delID;
                //    string post request for linking id to event to user
                StringRequest deleteTask = new StringRequest(Request.Method.DELETE, delTask,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, response.toString());
                                currentTasks.remove(taskNum);
                                changeDate();
                                setCurDate();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: "+ error.getMessage());
                    }
                });
                AppController.getInstance().addToRequestQueue(deleteTask);

                //use id for delete event request
                break;

            case R.id.downButton:
                cycleTaskDown();
                break;
            case R.id.upButton:
                cycleTaskUp();
                break;
        }
    }

    public void cycleTaskDown(){
        taskNum++;
        if(totalTasks<taskNum){
            taskNum = 0;
        }
        setCurDate();
    }
    public void cycleTaskUp(){
        taskNum--;
        if(taskNum<0){
            taskNum = totalTasks-1;
        }
        setCurDate();
    }

}