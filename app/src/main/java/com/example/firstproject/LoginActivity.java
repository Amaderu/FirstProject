package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText Mail;
    private EditText Password;
=======
import android.os.Handler;

public class LoginActivity extends AppCompatActivity {
>>>>>>> origin/master

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
<<<<<<< HEAD
        Mail=(EditText) findViewById(R.id.fieldMail);
        String[] User = RegActivity.User1;

        //Bundle arguments = getIntent().getExtras();

        if(User!=null){
            String mail = User[2];
            Mail.setText(mail);
        }

        /*Handler handler = new Handler();
=======
        Handler handler = new Handler();
>>>>>>> origin/master
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
<<<<<<< HEAD
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

=======
        },3000L);
    }
>>>>>>> origin/master
}
