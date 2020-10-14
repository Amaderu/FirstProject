package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Pattern;

public class RegActivity extends AppCompatActivity {

    private EditText textInputEmail;
    private EditText textInputUsername;
    private EditText textInputPassword;
    private EditText textInputConPassword;

    public static String[] User1;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        textInputEmail = findViewById(R.id.fieldMail);
        textInputUsername = findViewById(R.id.fieldFName);
        textInputPassword = findViewById(R.id.fieldPass);
        textInputConPassword =findViewById(R.id.fieldConPass);
        auto();

    }
    private void auto(){
        final EditText FName = findViewById(R.id.fieldFName);
        final EditText SName = findViewById(R.id.fieldSName);
        final EditText Mail = findViewById(R.id.fieldMail);
        final EditText Pass = findViewById(R.id.fieldPass);
        final EditText ConPass = findViewById(R.id.fieldConPass);

        FName.setText("Amaderu");
        SName.setText("454");
        Mail.setText("Qwe@mail.com");
        Pass.setText("QQq#1234");
        ConPass.setText("QQq#1234");
    }

    private boolean validateEmail() {
        String emailInput = textInputEmail.getText().toString().trim();
        if (emailInput.isEmpty()) {
            textInputEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textInputEmail.setError("Please enter a valid email address");
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }
    private boolean validateUsername() {
        String usernameInput = textInputUsername.getText().toString().trim();
        if (usernameInput.isEmpty()) {
            textInputUsername.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 18) {
            textInputUsername.setError("Username too long");
            return false;
        } else {
            textInputUsername.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        String passwordInput = textInputPassword.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Field can't be empty");
            return false;
        }else if (passwordInput.length() < 8) {
            textInputPassword.setError("Password is short");
            return false;
        }else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            textInputPassword.setError("Password too weak");
            return false;
        } else {
            textInputPassword.setError(null);
            return true;
        }
    }
    private boolean validateConPassword(){
        String passwordInput = textInputPassword.getText().toString().trim();
        String ConPasswordInput = textInputConPassword.getText().toString().trim();
        if(!passwordInput.equals(ConPasswordInput)){
            textInputConPassword.setError("Password is not match");
            return false;
        }
        else{
            textInputConPassword.setError(null);
            return true;
        }
    }


    public void  confirmInput(View v){
        final EditText FName = findViewById(R.id.fieldFName);
        final EditText SName = findViewById(R.id.fieldSName);
        final EditText Mail = findViewById(R.id.fieldMail);
        final EditText Pass = findViewById(R.id.fieldPass);

        /*Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("FirstName",FName.getText().toString());
        intent.putExtra("SecondName",SName.getText().toString());
        intent.putExtra("Mail",Mail.getText().toString());
        String mail =Mail.getText().toString();*/

        //Pattern pattern1 = Pattern.compile("([A-Za-z]+)(\\d*)(@mail\\.ru)");
        //Pattern pattern2 = Pattern.compile("([\\D]*)(@mail\\.ru)");
        //Patterns.EMAIL_ADDRESS.matcher(mail).matches();
        //boolean bmail = mail.matches(pattern1.toString());


        /*if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            Toast toast = Toast.makeText(getApplicationContext(), "НЕ правильно",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            if(Mail.getText().length()==0)
                Mail.setHintTextColor(Color.RED);
            else Mail.setTextColor(Color.RED);
            return;
        }
        Mail.setHintTextColor(Color.BLACK);
        Mail.setTextColor(Color.BLACK);
        String passsword=Pass.getText().toString();
        String conPass=ConPass.getText().toString();
        if(passsword.equals(conPass) && !passsword.isEmpty() && !conPass.isEmpty())
            startActivity(intent);*/



        if (!validateEmail() | !validateUsername() | !validatePassword() | !validateConPassword()) {
            return;
        }
        User1=new String[4];
        User1[0]=FName.getText().toString();
        User1[1]=SName.getText().toString();
        User1[2]=Mail.getText().toString();
        User1[3]=Pass.getText().toString();
        Intent auth = new Intent(this,LoginActivity.class);
        //auth.putExtra("mail",User1);
        startActivity(auth);
        finish();

    }


    public void Checking(EditText EdT){
        if(EdT.getText().length()==0)
            EdT.setHintTextColor(Color.RED);
        else EdT.setTextColor(Color.BLACK);
    }
}
