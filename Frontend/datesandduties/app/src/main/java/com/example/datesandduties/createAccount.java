package com.example.datesandduties;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.datesandduties.app.AppController;
import com.example.datesandduties.net_utils.Const;
import com.example.datesandduties.MainActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class createAccount extends Activity {

    private Button createAccount;
    private EditText inputName, inputUsername, inputPassword, inputGender, inputAge, inputEmail, inputPhone, inputCountry;
    private TextView outTest;

    private String TAG = createAccount.class.getSimpleName();
    private String tag_string_req = "string_req";
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




    private void createAccountRequest(){

        inputName = (EditText) findViewById(R.id.inputName);
        inputUsername = (EditText) findViewById(R.id.inputUsername);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
        inputEmail = (EditText) findViewById(R.id.inputEmail);
        inputGender = (EditText) findViewById(R.id.inputGender);
        inputAge = (EditText) findViewById(R.id.inputAge);
        inputPhone = (EditText) findViewById(R.id.inputPhone);
        inputCountry = (EditText) findViewById(R.id.inputCountry);

        String name, username, password, email, gender, age, phone, country;

        JSONObject newAccount = new JSONObject();
        try {
            newAccount.put("name", inputName.getText().toString());
            newAccount.put("username", inputUsername.getText().toString());
            newAccount.put("password", inputPassword.getText().toString());
            newAccount.put("email", inputEmail.getText().toString());
            newAccount.put("gender", inputGender.getText().toString());
            newAccount.put("age", Integer.valueOf(inputAge.getText().toString()));
            newAccount.put("phone", Integer.valueOf(inputPhone.getText().toString()));
            newAccount.put("country", inputCountry.getText().toString());
        } catch (JSONException e){
            Log.e("Couldn't create JSON", e.toString());
        }

        outTest.setText(newAccount.toString());

        String urlSuffix = "?name="+inputName.getText().toString()+
                            "&username="+inputUsername.getText().toString()+
                            "&password="+inputPassword.getText().toString()+
                            "&email="+inputEmail.getText().toString()+
                            "&gender="+inputGender.getText().toString()+
                            "&age="+inputAge.getText().toString()+
                            "&phone="+inputPhone.getText().toString()+
                            "&country="+inputCountry.getText().toString();

        String url = Const.URL_CREATE_ACCOUNT + urlSuffix;
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
        });
        AppController.getInstance().addToRequestQueue(req, tag_string_req);

    }



}