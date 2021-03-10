package com.example.datesandduties;

import androidx.appcompat.app.AppCompatActivity;

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

public class createAccount extends Activity {

    private Button createAccount;
    private EditText inputName, inputUsername, inputPassword, inputGender, inputAge, inputEmail, inputPhone, inputCountry;
    private TextView outTest;

    private String TAG = createAccount.class.getSimpleName();
    private String tag_string_req = "string_req";

    //private WebSocketClient cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        createAccount = (Button) findViewById(R.id.createAccount);
        outTest = (TextView) findViewById(R.id.outTest);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccountRequest();
            }
        });
    }




    private void createAccountRequest() {

        inputName = (EditText) findViewById(R.id.inputName);
        inputUsername = (EditText) findViewById(R.id.inputUsername);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
        inputEmail = (EditText) findViewById(R.id.inputEmail);
        inputGender = (EditText) findViewById(R.id.inputGender);
        inputAge = (EditText) findViewById(R.id.inputAge);
        inputPhone = (EditText) findViewById(R.id.inputPhone);
        inputCountry = (EditText) findViewById(R.id.inputCountry);

        String name, username, password, email, gender, country;
        Integer age, phone;


        name = inputName.getText().toString();
        username = inputUsername.getText().toString();
        password = inputPassword.getText().toString();
        email = inputEmail.getText().toString();
        gender = inputGender.getText().toString();
        age = Integer.valueOf(inputAge.getText().toString());
        phone = Integer.valueOf(inputPhone.getText().toString());
        country = inputCountry.getText().toString();


        String urlSuffix = "?name=" + name +
                "&username=" + username +
                "&password=" + password +
                "&email=" + email +
                "&gender=" + gender +
                "&age=" + age +
                "&phone=" + phone +
                "&country=" + country;

       /* HashMap<String, String> params = new HashMap<String, String>();
        params.put("name", name);
        params.put("username", username);
        params.put("password", password);
        params.put("email", email);
        params.put("gender", gender);
        params.put("age", age.toString());
        params.put("phone", phone.toString());
        params.put("country", country);

        */

       /* JsonObjectRequest jsonRequest = new JsonObjectRequest(Method.POST, Const.URL_CREATE_ACCOUNT, new JSONObject(params),
          //      new Response.Listener<JSONObject>() {
         //           @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                        outTest.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                outTest.setText(error.getMessage());
            }
        }); */


        String url = Const.URL_CREATE_ACCOUNT + urlSuffix; //created suffix to
        outTest.setText(url);

         StringRequest req = new StringRequest(Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                      public void onResponse(String response) {
                         Log.d(TAG, response.toString());
                         outTest.setText(response.toString());
                   }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: "+ error.getMessage());
             }
        }) {
            @Override
            protected Map<String,String> getParams(){
               Map<String,String> params = new HashMap<String, String>();
                params.put("name", name);
               params.put("username",username);
               params.put("password", password);
                params.put("email",email);
                params.put("gender", gender);
               params.put("age", age.toString());
              params.put("phone", phone.toString());
              params.put("country", country);

            return params;
           }

          @Override
          public Map<String, String> getHeaders() throws AuthFailureError {
              Map<String,String> params = new HashMap<String, String>();
              params.put("Content-type", "application/json");
                return params;
          }


    };
        AppController.getInstance().addToRequestQueue(req, tag_string_req);

    }



}