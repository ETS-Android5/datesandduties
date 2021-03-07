package com.example.volleytest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.example.volleytest.app.AppController;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request.Method;
import com.example.volleytest.net_utils.Const;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.ProgressDialog;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

public class stringRequest extends Activity {

    private String TAG = stringRequest.class.getSimpleName();
    private Button btnStringreq;
    private TextView msgResponse;
    private ProgressDialog pDialog;

    private String tag_string_req = "string_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_request);

        btnStringreq =  (Button) findViewById(R.id.strRequest);
        msgResponse = (TextView) findViewById(R.id.strResponse);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        btnStringreq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                makeStringReq();
            }
        });
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

    private void makeStringReq(){
        showProgressDialog();

        StringRequest strReq = new StringRequest(Method.GET, Const.URL_STRING_REQ, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
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
        });
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }





}