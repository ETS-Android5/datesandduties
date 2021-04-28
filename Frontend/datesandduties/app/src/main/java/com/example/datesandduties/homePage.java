package com.example.datesandduties;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.datesandduties.sign_in_page;

public class homePage extends Activity implements View.OnClickListener {

    private Button signOut, goToDates, settingz, goToDuties;

    String user = sign_in_page.getUsername();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        settingz = (Button) findViewById(R.id.settings);
        signOut = (Button) findViewById(R.id.signOut);
        goToDates = (Button) findViewById(R.id.goToDates);
        goToDuties = (Button) findViewById(R.id.goToDuties);
    }




    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.signOut:
                startActivity(new Intent(homePage.this, MainActivity.class));
                break;
            case R.id.goToDates:
                startActivity(new Intent(homePage.this, dates.class ));
                break;
            case R.id.settings:
                startActivity(new Intent(homePage.this, deleteAccount.class ));

                break;
            case R.id.goToDuties:
                startActivity(new Intent(homePage.this, taskMain.class));
                break;

        }
    }
}