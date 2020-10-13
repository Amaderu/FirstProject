package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = new TextView(this);
        textView.setTextSize(20);
        textView.setPadding(16, 16, 16, 16);

        Bundle arguments = getIntent().getExtras();

        if(arguments!=null){
            String firstName = arguments.getString("FirstName");
            String secondName = arguments.getString("SecondName");
            String mail = arguments.getString("Mail");
            textView.setText("Name: " + firstName + "\nSecond N: " + secondName +
                    "\nmail: " + mail);
        }
        setContentView(textView);
    }
}
