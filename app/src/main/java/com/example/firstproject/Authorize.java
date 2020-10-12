package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

public class Authorize extends AppCompatActivity {
    Button btnLogin;
    ImageView btnUnion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorize);
        /*TextView myTextView = (TextView) findViewById(R.id.entry);
        myTextView.setText("New text in TextView");
        TextView myTextView2 = (TextView) findViewById(R.id.logIn);
        myTextView2.setText("Type");
        */
        // найдем View-элементы
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnUnion = (ImageView) findViewById(R.id.btnUnion);

        /*btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);*/


    }
    public  void LongClick(View v){

    }
    public void switchClick(View v) {
        // по id определеяем кнопку, вызвавшую этот обработчик
        switch (v.getId()) {
            case R.id.btnLogin:
                //кнопка
                //Авторизация
                break;
            case R.id.btnUnion:
                //кнопка Union

                btnUnion.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Long Click Union",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        return false;
                    }
                });
            /*case R.id.btnCancel:
                // кнопка Cancel
                tvOut.setText("Нажата кнопка Cancel");
                finish();
                break;*/
        }
    }
}
