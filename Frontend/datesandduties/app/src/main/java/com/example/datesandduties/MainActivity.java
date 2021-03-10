package com.example.datesandduties;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button btnAccountCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAccountCreate = (Button) findViewById(R.id.accountCreationPage);
    }


    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.accountCreationPage:
                startActivity(new Intent(MainActivity.this, createAccount.class));
                break;

        }
    }


}