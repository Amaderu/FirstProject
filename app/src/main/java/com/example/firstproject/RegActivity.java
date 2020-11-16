package com.example.firstproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegActivity extends AppCompatActivity {

    private EditText textInputEmail;
    private EditText textInputUsername;
    private EditText textInputPassword;
    private EditText textInputConPassword;
    boolean acessRegist=false;

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
        textInputUsername = findViewById(R.id.fieldUsername);
        textInputPassword = findViewById(R.id.fieldPass);
        textInputConPassword =findViewById(R.id.fieldConPass);
        auto();

    }
    private void auto(){
        final EditText Username = findViewById(R.id.fieldUsername);
        final EditText FName = findViewById(R.id.fieldFName);
        final EditText SName = findViewById(R.id.fieldSName);
        final EditText Mail = findViewById(R.id.fieldMail);
        final EditText Pass = findViewById(R.id.fieldPass);
        final EditText ConPass = findViewById(R.id.fieldConPass);

        Username.setText("Amaderu");
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

    public void confirmI(View v) {
        if (!validateEmail() | !validateUsername() | !validatePassword()) {
            return;
        }
    }

    public void  confirmInput(View v){
        final EditText Username = findViewById(R.id.fieldUsername);
        final EditText FName = findViewById(R.id.fieldFName);
        final EditText SName = findViewById(R.id.fieldSName);
        final EditText Mail = findViewById(R.id.fieldMail);
        final EditText Pass = findViewById(R.id.fieldPass);
        //final EditText ConPass = findViewById(R.id.fieldConPass);

        if (!validateEmail() | !validateUsername() | !validatePassword() | !validateConPassword()) {
            return;
        }
        requestGetToSite(Username.getText().toString());
        if(!acessRegist) return;
        final PostModel.Swagger user = new PostModel.Swagger();
        user.setId(0);
        user.setUsername(Username.getText().toString());
        user.setFirstName(FName.getText().toString());
        user.setLastName(SName.getText().toString());
        user.setEmail(Mail.getText().toString());
        user.setPassword(Pass.getText().toString());
        user.setPhone("");
        user.setUserStatus(1);
        requestPostToSite(user);
        if(!acessRegist) return;
        /*User1 = new String[4];
        User1[0]=FName.getText().toString();
        User1[1]=SName.getText().toString();
        User1[2]=Mail.getText().toString();
        User1[3]=Pass.getText().toString();*/

        /*try {
            Scanner scanner = new Scanner(new URL("https://petstore.swagger.io/v2/user/Amaderu").openStream());
            String response = scanner.useDelimiter("\\Z").next();
            textView.setText(response);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        startActivity( new Intent(RegActivity.this,LoginActivity.class));
        finish();
    }

    public void requestGetToSite(final String username) {
        ApiAdd.getInstance().getApi().getUser(username).enqueue(new Callback<PostModel.Swagger>() {
            @Override
            public void onResponse(Call<PostModel.Swagger> call, Response<PostModel.Swagger> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegActivity.this, "This user is exist", (int) 0).show();
                    acessRegist = false;
                } else {
                    acessRegist = true;
                }
            }

            @Override
            public void onFailure(Call<PostModel.Swagger> call, Throwable t) {
                Toast.makeText(RegActivity.this, "An error occurred during networking", (int) 0).show();
                acessRegist = false;
                t.printStackTrace();
            }
        });
    }
    public void requestPostToSite(PostModel.Swagger user) {
        ApiAdd.getInstance()
                .getApi()
                .createUser(user)
                .enqueue(new Callback<PostModel.Swagger>() {

                    @Override
                    public void onResponse(Call<PostModel.Swagger> call, Response<PostModel.Swagger> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(RegActivity.this, "Succsess registration", Toast.LENGTH_SHORT).show();
                            Toast.makeText(RegActivity.this, response.raw().toString(), Toast.LENGTH_SHORT).show();
                            acessRegist = true;
                        } else {
                            Toast.makeText(RegActivity.this, "Failed registration", Toast.LENGTH_SHORT).show();
                            Toast.makeText(RegActivity.this, response.raw().toString(), Toast.LENGTH_SHORT).show();
                            acessRegist = false;
                        }

                    }

                    @Override
                    public void onFailure(Call<PostModel.Swagger> call, Throwable t) {
                        Toast.makeText(RegActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
                        Toast.makeText(RegActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
    }
    public void Checking(EditText EdT){
        if(EdT.getText().length()==0)
            EdT.setHintTextColor(Color.RED);
        else EdT.setTextColor(Color.BLACK);
    }
}
