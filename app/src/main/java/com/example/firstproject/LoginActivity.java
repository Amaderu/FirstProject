package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
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
    boolean verfy = false;
    // Идентификатор уведомления
    private int NOTIFY_ID = 101;
    // Идентификатор канала
    private static String CHANNEL_ID = String.valueOf(R.string.channel_name);

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
        Username = (EditText) findViewById(R.id.fieldUsername);
        Password = (EditText) findViewById(R.id.fieldPass);
        Password.setText("QQq#1234");

    }

    private boolean validateUser() {
        if (!validatePassword() || !validateUsername()) return false;
        ApiAdd.getInstance().getApi().getUser(Username.getText().toString()).enqueue(new Callback<PostModel.Swagger>() {
            @Override
            public void onResponse(Call<PostModel.Swagger> call, Response<PostModel.Swagger> response) {
                if (response.isSuccessful()) {
                    if (response.body().getPassword().equals(Password.getText().toString())) {
                        Toast.makeText(LoginActivity.this, "Success log in", (int) 0).show();
                        verfy = true;
                    }

                } else {
                    Username.setError("Invalid");
                    Password.setError("Invalid");
                    Toast.makeText(LoginActivity.this, "Invalid username or password", (int) 0).show();
                }
            }

            @Override
            public void onFailure(Call<PostModel.Swagger> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "An error occurred during networking", (int) 0).show();
                t.printStackTrace();
            }
        });

        //verfy = true;// опять не работает swagger
        if (!verfy) {
            return false;
        }
        return true;
    }


    public void enter(View v) {
        if (!validateUser()) return;
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        Intent notificationIntent = new Intent(LoginActivity.this, MainActivity.class);
        createNotificationChannel();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);


        PendingIntent pendingIntent = PendingIntent.getActivity(LoginActivity.this,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);//FLAG_CANCEL_CURRENT
        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentTitle("Авторизация")
                .setContentText("Успешная авторизация")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .addAction(R.mipmap.btnunion, "Открыть", pendingIntent);

        Notification notification = builder.build();
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //NotificationManagerCompat.from(LoginActivity.this);

        notificationManager.notify(NOTIFY_ID++, notification);

        startActivity(intent);
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

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager notificationManager = getSystemService(NotificationManager.class);

            NotificationChannel defaultChannel = notificationManager.getNotificationChannel(CHANNEL_ID);
            if (defaultChannel == null) {
                CharSequence name = getString(R.string.channel_name);
                String description = getString(R.string.channel_description);
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                channel.setDescription(description);

                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}
