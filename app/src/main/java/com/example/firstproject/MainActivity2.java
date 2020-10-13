package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void  onClick(View v){
        final EditText FName = findViewById(R.id.fieldFName);
        final EditText SName = findViewById(R.id.fieldSName);
        final EditText Mail = findViewById(R.id.fieldMail);
        final EditText Pass = findViewById(R.id.fieldPass);
        final EditText ConPass = findViewById(R.id.fieldConPass);

        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("FirstName",FName.getText().toString());
        intent.putExtra("SecondName",SName.getText().toString());
        intent.putExtra("Mail",Mail.getText().toString());
        String mail =Mail.getText().toString();
        Pattern pattern1 = Pattern.compile("([A-Za-z]+)(\\d*)(@mail\\.ru)");
        Pattern pattern2 = Pattern.compile("([\\D]*)(@mail\\.ru)");
        boolean bmail = mail.matches(pattern1.toString());
        if(!bmail){
            Toast toast = Toast.makeText(getApplicationContext(), "НЕ правильно",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            Mail.setTextColor(Color.RED);
            return;
        }
        Mail.setTextColor(Color.BLACK);
        String passsword=Pass.getText().toString();
        String conPass=ConPass.getText().toString();
        if(passsword.equals(conPass) && !passsword.isEmpty() && !conPass.isEmpty())
            startActivity(intent);
    }
}
