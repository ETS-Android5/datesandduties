package com.example.volleytest;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.toolbox.JsonObjectRequest;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button btnJson, btnString, btnImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnString = (Button) findViewById(R.id.btnStringRequest);
        btnJson = (Button) findViewById(R.id.btnJsonRequest);
        btnImage = (Button) findViewById(R.id.btnImageRequest);

    }


    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.btnStringRequest:
                startActivity(new Intent(MainActivity.this, stringRequest.class));
                break;
            case R.id.btnJsonRequest:
                startActivity(new Intent(MainActivity.this, jsonRequest.class));
                break;
            case R.id.btnImageRequest:
                startActivity(new Intent(MainActivity.this, imgRequest.class));
                break;
            default:
                break;
        }
    }
}