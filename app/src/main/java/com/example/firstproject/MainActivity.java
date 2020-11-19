package com.example.firstproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // Идентификатор уведомления
    private int NOTIFY_ID = 201;
    // Идентификатор канала
    private static String CHANNEL_ID = String.valueOf(R.string.channel_name);

    int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;
    TextView textView;
    View.OnClickListener oclBtnGet;
    EditText search;
    View.OnClickListener oclBtnPost = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestPostToSite();
        }
    };
    String changUser="Amaderu";

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

        search = new EditText(this);
        layout.addView(search,-1,150);

        int btnGravity = Gravity.START;
        lParams.gravity = btnGravity;

        Button btnGet = new Button(this);
        btnGet.setText("Get");

        oclBtnGet = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestGetToSite(search.getText().toString());
            }
        };

        btnGet.setOnClickListener(oclBtnGet);

        Button btnPost = new Button(this);
        btnPost.setText("Post "+"User2");

        LinearLayout.LayoutParams lParams2 = new LinearLayout.LayoutParams(
                wrapContent, wrapContent);
        lParams2.gravity=Gravity.END;
        btnPost.setOnClickListener(oclBtnPost);

        LinearLayout Button_layout = new LinearLayout(this);
        Button_layout.setOrientation(LinearLayout.HORIZONTAL);
        Button btnPut = new Button(this);
        btnPut.setText("Put "+"");
        btnPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPutToSite(search.getText().toString(),createBody(changUser));
            }
        });

        Button btnDel = new Button(this);
        btnDel.setText("Del "+"_");
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDelToSite(search.getText().toString());
            }
        });

        Button btnNotify = new Button(this);
        btnNotify.setText("Note");
        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotify();
            }
        });

        Button_layout.addView(btnGet, lParams);
        Button_layout.addView(btnPost, lParams2);
        Button_layout.addView(btnPut, lParams);
        Button_layout.addView(btnNotify,lParams);
        Button_layout.addView(btnDel, lParams);

        layout.addView(Button_layout,-1,wrapContent);



        textView = new TextView(this);
        textView.setTextSize(20);
        textView.setPadding(16, 16, 16, 16);
        textView.setText("Data");
        textView.setLayoutParams(lParams);
        layout.addView(textView, lParams);

        setContentView(layout);
        //setContentView(textView);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                requestGetToSite("Test_layla");
            }
        },1500L);

    }

    public void requestGetToSite(final String username){
        ApiAdd.getInstance()
                .getApi()
                .getUser(username)
                .enqueue(new Callback<PostModel.Swagger>() {
                    @Override
                    public void onResponse(@NonNull Call<PostModel.Swagger> call, @NonNull retrofit2.Response<PostModel.Swagger> response) {
                        if (response.isSuccessful()) {
                            PostModel.Swagger postModel = response.body();
                            long id = postModel.getId();
                            String userName = postModel.getUsername();
                            String firstName = postModel.getFirstName();
                            String lastName = postModel.getLastName();
                            String mail = postModel.getEmail();
                            String pass = postModel.getPassword();
                            String phone = postModel.getPhone();
                            int userstat = postModel.getUserStatus();

                            String string = "id: "+id+"\nusername: "+userName+"\nfirstName: "+firstName+"\nlastName: "+lastName+"\n" +
                                    "email: "+mail+"\npassword: "+pass+"\nphone: "+phone+"\nuserStatus: "+userstat+"\n";
                            textView.setText(string);
                            Toast.makeText(MainActivity.this, response.raw().toString(), Toast.LENGTH_SHORT).show();
                        } else {
                            //Log.e("Retrofi2","Error");
                            Toast.makeText(MainActivity.this,"User "+"\""+username+"\""+" not found",(int)0).show();
                            Toast.makeText(MainActivity.this, response.raw().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<PostModel.Swagger> call, @NonNull Throwable t) {
                        Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();

                        t.printStackTrace();
                    }
                });
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
    public void requestPostToSite(){
        ApiAdd.getInstance()
                .getApi()
                .createUser(createBody("user2"))
                .enqueue(new Callback<PostModel.Swagger>() {

                    @Override
                    public void onResponse(Call<PostModel.Swagger> call, Response<PostModel.Swagger> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Succsess sending", Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, response.raw().toString(), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Failed sending", Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, response.raw().toString(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<PostModel.Swagger> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
    }
    public void requestPutToSite(final String username, PostModel.Swagger user){
        ApiAdd.getInstance()
                .getApi()
                .putUser(username,createBody(user.getUsername()))
                .enqueue(new Callback<PostModel.Swagger>() {

                    @Override
                    public void onResponse(Call<PostModel.Swagger> call, Response<PostModel.Swagger> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Succsess update", Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, response.raw().toString(), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Failed upate", Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, response.raw().toString(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<PostModel.Swagger> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
    }
    public void requestDelToSite(final String username){
        ApiAdd.getInstance()
                .getApi()
                .delUser(username)
                .enqueue(new Callback<PostModel.Swagger>() {

                    @Override
                    public void onResponse(Call<PostModel.Swagger> call, Response<PostModel.Swagger> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Succsess Delete", Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, response.raw().toString(), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Failed Delete", Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, response.raw().toString(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<PostModel.Swagger> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
    }

    public PostModel.Swagger createBody(String userName){
        PostModel.Swagger user = new PostModel.Swagger();
        user.setId(778);
        user.setUsername(userName);
        user.setFirstName("user2");
        user.setLastName("sd");
        user.setEmail("edfyu@gmail.com");
        user.setPassword("qeqwe");
        user.setPhone("sad");
        user.setUserStatus(1);
        return user;

    }
    public void createBody(PostModel.Swagger user){
        user.setId(1);
        user.setUsername("user2");
        user.setFirstName("ad");
        user.setLastName("sd");
        user.setEmail("edfyu@gmail.com");
        user.setPassword("qeqwe");
        user.setPhone("sad");
    }
    private void createNotify(){
        Intent notificationIntent = new Intent(MainActivity.this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,
                0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);//FLAG_CANCEL_CURRENT
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(MainActivity.this,CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle("Авторизация")
                        .setContentText("Успешная авторизация")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);
        //.addAction(R.mipmap.btnunion,"Открыть",pendingIntent);
        Notification notification = builder.build();

        Notification notification1 = new Notification.Builder(MainActivity.this,CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Title")
                .setContentText("Notification text")
                .setAutoCancel(true).build();


                /*NotificationManagerCompat notificationManager =
                        //(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                NotificationManagerCompat.from(MainActivity.this);*/

        notificationManager.notify(NOTIFY_ID++,notification);
        notificationManager.notify(1,notification1);
    }


}
