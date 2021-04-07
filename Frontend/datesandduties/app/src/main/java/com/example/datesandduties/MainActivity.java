package com.example.datesandduties;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.datesandduties.app.AppController;
import com.example.datesandduties.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button btnAccountCreate, btnListAllAccounts, btnSignIn;
    private String TAG = MainActivity.class.getSimpleName();
    private TextView allAccounts;
    private String tag_json_obj = "jobj_req";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAccountCreate = (Button) findViewById(R.id.accountCreationPage);
        btnListAllAccounts = (Button) findViewById(R.id.btnListAll);
        btnSignIn = (Button) findViewById(R.id.signIn);
        allAccounts = (TextView) findViewById(R.id.allAccounts);
    }


    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.accountCreationPage:
                startActivity(new Intent(MainActivity.this, createAccount.class));
                break;
            case R.id.btnListAll:
                requestAllAccounts();
                //requestAccounts();
                break;
            case R.id.signIn:
                startActivity(new Intent(MainActivity.this, sign_in_page.class));
                break;
        }
    }

    private void requestAccounts() {

    }

    private void requestAllAccounts() {

        JsonArrayRequest req = new JsonArrayRequest(Const.URL_LIST_ALL_ACCOUNTS,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        allAccounts.setText(response.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, error.getMessage());
            }
        } ) ;
        AppController.getInstance().addToRequestQueue(req, tag_json_obj);

    }


}