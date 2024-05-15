package com.example.apiha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GlavActivity extends AppCompatActivity {


    SearchView searchView;
    CheckBox up, down;

    TextView uptxt, downtxt;

    Button basket;

    Apiinterface apiinterface;

    ArrayList<Service> list;
    ArrayList<Service> dop_list;
    ArrayList<Integer> dop_int_list;

    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glav);

        list = new ArrayList<Service>();

        rv = findViewById(R.id.rv);

        basket = findViewById(R.id.basket);

        dop_int_list = new ArrayList<Integer>();
        dop_list = new ArrayList<Service>();

        up = findViewById(R.id.up);
        down = findViewById(R.id.down);
        apiinterface = ServiceBuilder.requestBuilder().create(Apiinterface.class);
        Call<ArrayList<Service>> getServices = apiinterface.getServices();

        getServices.enqueue(new Callback<ArrayList<Service>>() {
            @Override
            public void onResponse(Call<ArrayList<Service>> call, Response<ArrayList<Service>> response) {
                list = response.body();
                rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rv.setHasFixedSize(true);
                itemAdapter adapter = new itemAdapter(getApplicationContext(), list);
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Service>> call, Throwable t) {

            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (up.isChecked()){
                    dop_int_list.clear();
                    dop_list.clear();
                    down.setChecked(false);

                    for (Service i : list){
                        dop_int_list.add(Integer.parseInt(i.getCoast()));
                        //Toast.makeText(GlavActivity.this, i.coast, Toast.LENGTH_SHORT).show();
                    }
                    Collections.sort(dop_int_list);
                    for (int k : dop_int_list){
                        for (Service o: list){
                            if (k == Integer.parseInt(o.coast)){
                                dop_list.add(o);
                            }
                        }
                    }
                    itemAdapter adapter = new itemAdapter(getApplicationContext(), dop_list);
                    rv.setAdapter(adapter);
                }

                else {
                    itemAdapter adapter = new itemAdapter(getApplicationContext(), list);
                    rv.setAdapter(adapter);
                }

            }
        });


        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dop_int_list.clear();
                dop_list.clear();
                if (down.isChecked()){
                    up.setChecked(false);

                    for (Service i : list){
                        dop_int_list.add(Integer.parseInt(i.getCoast()));
                        //Toast.makeText(GlavActivity.this, i.coast, Toast.LENGTH_SHORT).show();
                    }
                    Collections.sort(dop_int_list, Collections.reverseOrder());
                    for (int k : dop_int_list){
                        for (Service o: list){
                            if (k == Integer.parseInt(o.coast)){
                                dop_list.add(o);
                            }
                        }
                    }
                    itemAdapter adapter = new itemAdapter(getApplicationContext(), dop_list);
                    rv.setAdapter(adapter);
                }
                else {
                    itemAdapter adapter = new itemAdapter(getApplicationContext(), list);
                    rv.setAdapter(adapter);
                }


            }
        });


        searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterList(s);
                return false;
            }
        });







        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GlavActivity.this, BasketActivity.class);
                //intent.putExtra("user", user.get(0).login.toString());
                startActivity(intent);
            }
        });




    }


    private void filterList(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        ArrayList<Service> filtered_list = new ArrayList<>();
        Set<Service> set = new HashSet<>();
        for(Service item: list){
            if(item.getName().toLowerCase().contains(s.toLowerCase())){
                set.add(item);
            }
//            if(item.getName().toLowerCase().contains("ст")){
//                set.add(item);
//            }
//
//            if(item.getName().toLowerCase().contains("окр")){
//                set.add(item);
//            }
//
//            if(item.getName().toLowerCase().contains("ук")){
//                set.add(item);
//            }
//
//            if(item.getName().toLowerCase().contains("з")){
//                set.add(item);
//            }

            filtered_list.clear();
            filtered_list.addAll(set);

        }

        if(filtered_list.isEmpty()){
            Toast.makeText(getApplicationContext(), "Data was not fount", Toast.LENGTH_SHORT).show();
        }
        else{
            itemAdapter adapter = new itemAdapter(getApplicationContext(), filtered_list);
            rv.setAdapter(adapter);
        }

    }
}