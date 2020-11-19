package com.example.firstproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firstproject.api.PetResponse;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Pets extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter programmAdapter;
    RecyclerView.LayoutManager layoutManager;
    Button btn;
    EditText responseTextView;
    TextView amountRes;
    public List<PetResponse> pets;

    String[] Category = {"Cats","dogs","Cats","dogs","Cats","dogs"};
    //String[] Category;
    String[] Names = {"Cats1","Dog1","Cat2","Dog2","Cat3","Dog3"};
    //String[] Names;
    int[] images ={R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground};
    //int[] images ={R.drawable.ic_launcher_foreground};
    String[] Status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pets);

        amountRes=findViewById(R.id.amount);
        recyclerView= findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //requestGetPet(100);
        responseTextView = findViewById(R.id.responseTextView);
        btn = findViewById(R.id.search_btnStatus);
        View.OnClickListener oclBtnFind;
        oclBtnFind = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestfindByStatus(responseTextView.getText().toString());
            }
        };
        btn.setOnClickListener(oclBtnFind);
        //requestfindByStatus("sold");
        /*if(!pets.isEmpty()){
            programmAdapter = new programmAdapter(this,pets);
            recyclerView.setAdapter(programmAdapter);
        }*/
        //programmAdapter = new programmAdapter(this,pets);
        recyclerView.setAdapter(programmAdapter);


    }
    //findByStatus
    public void requestfindByStatus(final String status) {
        ApiAdd.getInstance()
                .getApi()
                .findByStatus(status)
                .enqueue(new Callback<List<PetResponse>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<PetResponse>> call, @NonNull retrofit2.Response<List<PetResponse>> response) {
                        if (response.isSuccessful()) {
                            List<PetResponse> pets = response.body();
                            amountRes.setText(Integer.toString(pets.size()));

                            programmAdapter = new programmAdapter(Pets.this,pets);
                            recyclerView.setAdapter(programmAdapter);
                            //pet.getTags();
                            Toast.makeText(Pets.this, response.raw().toString(), Toast.LENGTH_SHORT).show();

                            //TextView responseTextView = findViewById(R.id.responseTextView);
                            //responseTextView.setText(pet.getName());
                        } else {
                            //Log.e("Retrofi2","Error");
                            Toast.makeText(Pets.this, "Pet with status " + "\"" + status + "\"" + " not found", (int) 0).show();
                            Toast.makeText(Pets.this, response.raw().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<PetResponse>> call, @NonNull Throwable t) {
                        Toast.makeText(Pets.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
                        Toast.makeText(Pets.this, "Fail", Toast.LENGTH_SHORT).show();

                        t.printStackTrace();
                    }
                });
    }
    public void requestGetPet(final Integer id) {
        ApiAdd.getInstance()
                .getApi()
                .getPetbyId(id)
                .enqueue(new Callback<PetResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<PetResponse> call, @NonNull retrofit2.Response<PetResponse> response) {
                        if (response.isSuccessful()) {
                            PetResponse pet = response.body();
                            pets.add(pet);
                            programmAdapter = new programmAdapter(Pets.this,pets);
                            recyclerView.setAdapter(programmAdapter);
                            //pet.getTags();
                            Toast.makeText(Pets.this, response.raw().toString(), Toast.LENGTH_SHORT).show();


                            //responseTextView.setText(pet.getName());
                        } else {
                            //Log.e("Retrofi2","Error");
                            Toast.makeText(Pets.this, "Pet with id " + "\"" + Integer.toString(id) + "\"" + " not found", (int) 0).show();
                            Toast.makeText(Pets.this, response.raw().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<PetResponse> call, @NonNull Throwable t) {
                        Toast.makeText(Pets.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
                        Toast.makeText(Pets.this, "Fail", Toast.LENGTH_SHORT).show();

                        t.printStackTrace();
                    }
                });
    }

}
