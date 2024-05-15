package com.example.apiha;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BasketActivity extends AppCompatActivity {

    Apiinterface apiinterface;
    RecyclerView rv;

    ArrayList<String> s;
    ArrayList<Service> serv;

    SharedPreferences settings;

    String a1;

    String a2;

    String a3;
    int count;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        rv = findViewById(R.id.rv);

        s = new ArrayList<String>();
        s.add("ddd");

        serv = new ArrayList<Service>();

        apiinterface = ServiceBuilder.requestBuilder().create(Apiinterface.class);
        settings = getSharedPreferences("settings", MODE_PRIVATE);

        Call<ArrayList<Service>> getServices = apiinterface.getServices();

//        for (String k : settings.getString("basket", "0").split(" ")){
//            Toast.makeText(this, k, Toast.LENGTH_SHORT).show();
//
//        }

        ArrayList<String> arrayList = new ArrayList<>();
        if (!Objects.equals(Arrays.asList(settings.getString("basket", "0").split(" ")).get(0), "0")){

            arrayList.addAll(Arrays.asList(settings.getString("basket", "0").split(" ")));
        }


        getServices.enqueue(new Callback<ArrayList<Service>>() {
            @Override
            public void onResponse(Call<ArrayList<Service>> call, Response<ArrayList<Service>> response) {
                if (response.isSuccessful()){
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv.setHasFixedSize(true);
                    ArrayList<Service> list = response.body();
                    itemAdapter_basket adapter = new itemAdapter_basket(getApplicationContext(), list, arrayList);
                    rv.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Service>> call, Throwable t) {

            }
        });

    }



}