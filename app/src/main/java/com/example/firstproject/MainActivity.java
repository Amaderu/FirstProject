package com.example.firstproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        // создаем LayoutParams
        LinearLayout.LayoutParams linLayoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        // устанавливаем linLayout как корневой элемент экрана
        setContentView(layout, linLayoutParam);

        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
                wrapContent, wrapContent);

        textView = new TextView(this);
        textView.setTextSize(20);
        textView.setPadding(16, 16, 16, 16);
        textView.setLayoutParams(lParams);
        layout.addView(textView, lParams);


        int btnGravity = Gravity.LEFT;
        lParams.gravity = btnGravity;

        Button button = new Button(this);
        button.setText("Retry");
        View.OnClickListener oclBtnOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Меняем текст в TextView (tvOut)
                requestToSite("user1");
            }
        };
        button.setOnClickListener(oclBtnOk);
        layout.addView(button, lParams);


        /*Bundle arguments = getIntent().getExtras();
        if(arguments!=null){
            String firstName = arguments.getString("FirstName");
            String secondName = arguments.getString("SecondName");
            String mail = arguments.getString("Mail");
            textView.setText("Name: " + firstName + "\nSecond N: " + secondName +
                    "\nmail: " + mail);
        }*/
        String[] User = RegActivity.User1;
        if(User!=null){
            String firstName = User[0];
            String secondName = User[1];
            String mail = User[2];
            textView.setText("Name: " + firstName + "\nSecond N: " + secondName +
                    "\nmail: " + mail+"\npassword: " + User[3]);
        }
        setContentView(layout);
        //setContentView(textView);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                requestToSite("Amaderu");
            }
        },1500L);

    }
    public void requestToSite(String username){
        ApiAdd.getInstance()
                .getApi()
                .getUser(username)
                .enqueue(new Callback<PostModel.Swagger>() {
                    @Override
                    public void onResponse(@NonNull Call<PostModel.Swagger> call, @NonNull retrofit2.Response<PostModel.Swagger> response) {
                        if (response.isSuccessful()) {
                            PostModel.Swagger postModel = response.body();
                            int id = postModel.getId();
                            String userName = postModel.getUsername();
                            String firstName = postModel.getFirstName();
                            String lastName = postModel.getLastName();
                            String mail = postModel.getEmail();
                            String pass = postModel.getPassword();
                            String phone = postModel.getPhone();
                            int userstat = postModel.getUserStatus();
                            //textView.setText("Name: " + firstName + "\nSecond N: " + secondName +"\nmail: " + mail+"\npassword: " + User[3]);

                            String string = "id: "+id+"\n username: "+userName+"\n  firstName: "+firstName+"\n  lastName: "+lastName+"\n  " +
                                    "email: "+mail+"\n  password: "+pass+"\n  phone: "+phone+"\n  userStatus: "+userstat+"\n";
                            textView.setText(string);
                            //Toast.makeText(MainActivity.this, postModel.getFirstName().toString(), Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("Retrofi2","Error");
                        }
                        //PostModel postModel = response.body();
                        //Toast.makeText(LoginActivity.this, postModel.getFirstName().toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(@NonNull Call<PostModel.Swagger> call, @NonNull Throwable t) {
                        Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
        /*ApiAdd.getInstance()
                .getApi()
                .getPost(1)
                .enqueue(new Callback<PostModel.Post>() {
                    @Override
                    public void onResponse(@NonNull Call<PostModel.Post> call, @NonNull retrofit2.Response<PostModel.Post> response) {
                        if (response.isSuccessful()) {
                            PostModel.Post post = response.body();
                            Toast.makeText(LoginActivity.this, Integer.toString(post.getId()), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, Integer.toString(response.code()), Toast.LENGTH_SHORT).show();
                        }
                        //PostModel postModel = response.body();
                        //Toast.makeText(LoginActivity.this, postModel.getFirstName().toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(@NonNull Call<PostModel.Post> call, @NonNull Throwable t) {
                        Toast.makeText(LoginActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
                        Toast.makeText(LoginActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });*/

        /*ApiAdd.getInstance()
                .getApi()
                .createUser(Add)
                .enqueue(new Callback<PostModel.Post>() {
                    @Override
                    public void onResponse(@NonNull Call<PostModel.Post> call, @NonNull retrofit2.Response<PostModel.Post> response) {
                        if (response.isSuccessful()) {
                            PostModel.Post post = response.body();
                            Toast.makeText(LoginActivity.this, Integer.toString(post.getId()), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, Integer.toString(response.code()), Toast.LENGTH_SHORT).show();
                        }
                        //PostModel postModel = response.body();
                        //Toast.makeText(LoginActivity.this, postModel.getFirstName().toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(@NonNull Call<PostModel.Post> call, @NonNull Throwable t) {
                        Toast.makeText(LoginActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
                        Toast.makeText(LoginActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });*/


        /*try {
            ApiAdd.getApi().getData("Amaderu").execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ApiAdd.getApi().getData("Amaderu").enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, retrofit2.Response<List<PostModel>> response) {

                Toast.makeText(LoginActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });*/

        /*try {
            Scanner scanner = new Scanner(new URL("https://petstore.swagger.io/v2/user/Amaderu").openStream());
            String response = scanner.useDelimiter("\\Z").next();
            textView.setText(response);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
    public void autoUser(PostModel.Swagger user){
        user.setId(1);
        user.setFirstName("ad");
        user.setLastName("sd");
        user.setEmail("edfyu@gmail.com");
        user.setPassword("qeqwe");
        user.setPhone("sad");
    }
}
