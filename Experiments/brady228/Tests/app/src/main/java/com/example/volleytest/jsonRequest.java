package com.example.volleytest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.AuthFailureError;
import java.util.Map;
import java.util.HashMap;
import com.example.volleytest.app.AppController;
import com.example.volleytest.net_utils.Const;
import com.example.volleytest.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class jsonRequest extends Activity implements View.OnClickListener {

    private String TAG = jsonRequest.class.getSimpleName();
    private Button btnJsonObj, btnJsonArray;
    private TextView msgResponse;
    private ProgressDialog pDialog;

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_request);

        btnJsonObj = (Button) findViewById(R.id.jsonObjectRequest);
        btnJsonArray = (Button) findViewById(R.id.jsonArrayRequest);
        msgResponse = (TextView) findViewById(R.id.jsonResponse);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading........");
        pDialog.setCancelable(false);

        btnJsonObj.setOnClickListener(this);
        btnJsonArray.setOnClickListener(this);
    }

    private void showProgressDialog(){
        if(!pDialog.isShowing()){
            pDialog.show();
        }
    }

    private void hideProgressDialog(){
        if(pDialog.isShowing()){
            pDialog.hide();
        }
    }

    private void makeJsonObjReq(){
        showProgressDialog();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET, Const.URL_JSON_OBJECT, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                msgResponse.setText(response.toString());
                hideProgressDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: "+error.getMessage());
                hideProgressDialog();
            }
        }) {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type","application/json");
                return headers;
            }

            @Override
            protected Map<String,String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", "Androidhive");
                params.put("email","abc@androidhive.info");
                params.put("pass", "password123");

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }



    private void makeJsonArryReq(){
        showProgressDialog();
        JsonArrayRequest req = new JsonArrayRequest(Const.URL_JSON_ARRAY,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        msgResponse.setText(response.toString());
                        hideProgressDialog();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                VolleyLog.d(TAG, error.getMessage());
                hideProgressDialog();
            }
        }) {
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError{
                HashMap<String,String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("name","Androidhive");
                params.put("emails", "abc@androidhive.info");
                params.put("pass","password123");

                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(req, tag_json_obj);
    }


    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.jsonObjectRequest:
                makeJsonObjReq();
                break;
            case R.id.jsonArrayRequest:
                makeJsonArryReq();
                break;
        }
    }


}