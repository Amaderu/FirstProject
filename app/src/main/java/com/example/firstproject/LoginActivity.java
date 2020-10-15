package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.os.Handler;

public class LoginActivity extends AppCompatActivity {
    private EditText Mail;
    private EditText Password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Mail=(EditText) findViewById(R.id.fieldMail);
        String[] User = RegActivity.User1;

        //Bundle arguments = getIntent().getExtras();

        if(User!=null){
            String mail = User[2];
            Mail.setText(mail);
        }

        /*Handler handler = new Handler();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        },3000L);*/
    }
    private boolean validateUser(){
        String[] user = RegActivity.User1;
        Mail=(EditText) findViewById(R.id.fieldMail);
        Password=(EditText) findViewById(R.id.fieldPass);

        if(!Mail.getText().toString().equals(user[2])|!Password.getText().toString().equals(user[3])){
            return false;
        }
        return true;
    }
    public void enter(View v){
        if(!validateUser())
                return;
        Intent main = new Intent(this,MainActivity.class);
        startActivity(main);
        finish();

    }
}
