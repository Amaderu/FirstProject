package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    private EditText Username;
    private EditText Password;
    boolean verfy=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       /* Mail=(EditText) findViewById(R.id.fieldMail);
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
        },3000L);
        if(User!=null){
            String pass = User[3];

        }*/
        Username=(EditText) findViewById(R.id.fieldUsername);
        Password=(EditText) findViewById(R.id.fieldPass);
        Password.setText("QQq#1234");

    }
    private boolean validateUser(){
        if(!validatePassword()||!validateUsername()) return false;
        ApiAdd.getInstance().getApi().getUser(Username.getText().toString()).enqueue(new Callback<PostModel.Swagger>() {
            @Override
            public void onResponse(Call<PostModel.Swagger> call, Response<PostModel.Swagger> response) {
                if (response.isSuccessful()&&response.body().getPassword().equals(Password.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Success log in", (int) 0).show();
                    verfy = true;
                }
                else{
                    Username.setError("Invalid");
                    Password.setError("Invalid");
                    Toast.makeText(LoginActivity.this,"Invalid username or password", (int) 0).show();
                }
            }

            @Override
            public void onFailure(Call<PostModel.Swagger> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "An error occurred during networking", (int) 0).show();
                t.printStackTrace();
            }
        });
        if(!verfy){
            return false;
        }
        return true;
    }


    public void enter(View v){
        if(!validateUser()) return;
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    private boolean validateUsername() {
        final String usernameInput = Username.getText().toString().trim();
        if (usernameInput.isEmpty()) {
            Username.setError("Field can't be empty");
            return false;
        } else {
            Username.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        final String passwordInput = Password.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            Password.setError("Field can't be empty");
            return false;
        } else {
            Password.setError(null);
            return true;
        }
    }
}
