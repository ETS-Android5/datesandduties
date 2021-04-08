package com.example.datesandduties;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;

import com.android.volley.Request.Method;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.datesandduties.app.AppController;
import com.example.datesandduties.net_utils.Const;
import com.example.datesandduties.MainActivity;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import com.android.volley.toolbox.StringRequest;
import com.example.datesandduties.app.AppController;
import com.example.datesandduties.net_utils.Const;





public class deleteAccount extends Activity {

    public static String username;


    private Button deleteAct;
    private EditText loginUsername, loginPassword;

    private String TAG = sign_in_page.class.getSimpleName();
    //private String login_string_req = "string_req";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        deleteAct = (Button) findViewById(R.id.delAct);
        //test = (TextView) findViewById(R.id.loginDebug);
        deleteAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCheck();
            }
        });
    }

    private void deleteCheck() {

       String url = Const.DEL_USER + "/" + sign_in_page.getUsername();

        StringRequest del = new StringRequest(Method.DELETE, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response.toString());
                       // outTest.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: "+ error.getMessage());
            }
        });


        AppController.getInstance().addToRequestQueue(del);



    }

}
