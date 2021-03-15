package com.example.datesandduties;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class homePage extends Activity implements View.OnClickListener {
    /**
     * creates home page for default view
     */

    private Button signOut, goToDates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        signOut = (Button) findViewById(R.id.signOut);
        goToDates = (Button) findViewById(R.id.goToDates);
    }




    @Override
    public void onClick(View v){
        /**
         * handles sign out and go to date pages from the home page
         */
        switch (v.getId()) {
            case R.id.signOut:
                startActivity(new Intent(homePage.this, MainActivity.class));
                break;
            case R.id.goToDates:
                startActivity(new Intent(homePage.this, dates.class ));

        }
    }
}