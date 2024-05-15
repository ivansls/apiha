package com.example.apiha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText login, password, salt, name;
    Button auth, reg;

    Apiinterface apiinterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = findViewById(R.id.auth);
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        salt = findViewById(R.id.salt);
        reg = findViewById(R.id.reg);
        name = findViewById(R.id.name);



        apiinterface = ServiceBuilder.requestBuilder().create(Apiinterface.class);



        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ArrayList<Users>> authuser = apiinterface.auth_user(login.getText().toString(), password.getText().toString(), name.getText().toString() ,salt.getText().toString());
                authuser.enqueue(new Callback<ArrayList<Users>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Users>> call, Response<ArrayList<Users>> response) {
                        Toast.makeText(MainActivity.this, "AUTH", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Users>> call, Throwable t) {

                    }
                });
            }
        });


        auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ArrayList<Users>> getUser = apiinterface.getUser(login.getText().toString());
                getUser.enqueue(new Callback<ArrayList<Users>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Users>> call, Response<ArrayList<Users>> response) {
                        if (response.isSuccessful()){
                            ArrayList<Users> user = response.body();
                            Call<String> getHash = apiinterface.getHash(password.getText().toString(), salt.getText().toString());

                            getHash.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    if (response.isSuccessful()){
                                        String hash = response.body();

                                        if (hash.toString().equals(user.get(0).password.toString())){
                                            Toast.makeText(MainActivity.this, "YES", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(MainActivity.this, GlavActivity.class);
                                            intent.putExtra("user", user.get(0).login.toString());
                                            startActivity(intent);
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {

                                }
                            });
                        }
                        else {
                            Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Users>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}