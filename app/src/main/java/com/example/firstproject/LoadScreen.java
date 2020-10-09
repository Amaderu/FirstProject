package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class LoadScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_screen);

        TimerTask MyTask = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(LoadScreen.this, Authorize.class));
                finish();
            }
        };
        Timer MyTimer = new Timer();
        MyTimer.schedule(MyTask,5000L);
    }


    //findViewById(R.drawable.ic_launcher_background);
    //Toast

}
