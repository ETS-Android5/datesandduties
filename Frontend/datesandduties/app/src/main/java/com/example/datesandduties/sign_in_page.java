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

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.datesandduties.app.AppController;
import com.example.datesandduties.net_utils.Const;

public class sign_in_page extends Activity {

    public static String username = "0";

    private Button signIn;
    private EditText loginUsername, loginPassword;

    private String TAG = sign_in_page.class.getSimpleName();
    private String login_string_req = "string_req";
    private String accept;

    private TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);

        signIn = (Button) findViewById(R.id.logIn);
        test = (TextView) findViewById(R.id.loginDebug);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginCheck();
            }
        });
    }

    private void loginCheck() {

        //check login using login volley get

        loginUsername = (EditText) findViewById(R.id.loginUsername);
        loginPassword = (EditText) findViewById(R.id.loginPassword);

        String url = Const.URL_LOGIN + "/" + loginUsername.getText().toString() + "/" + loginPassword.getText().toString();
        StringRequest signon = new StringRequest(Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response.toString());
                        accept = response.toString();

                        if(accept.equals("Login successful!")){
                            //do stuff
                            test.setText(accept);
                            username = loginUsername.getText().toString();
                            startActivity(new Intent(sign_in_page.this, homePage.class));
                        }
                        else{
                            //login incorrect do nothing
                            test.setText(accept);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: "+ error.getMessage());
            }
        });

        AppController.getInstance().addToRequestQueue(signon);



    }

    public static String getUsername(){
        return username;
    }
    public void clearUsername(){
        username = null;
    }

}