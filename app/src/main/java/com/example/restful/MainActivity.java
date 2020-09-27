package com.example.restful;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ClientApi clientApi;
    private ListView listView;
    private ArrayList<Data> data= new ArrayList<>();
    private RecyclerView recyclerView;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_View);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, data);
        recyclerView.setAdapter(adapter);




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://simplifiedcoding.net/demos/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        clientApi = retrofit.create(ClientApi.class);
        Call<List<Data>> call = clientApi.getData();

        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
               if (!response.isSuccessful()){
                   Toast.makeText(MainActivity.this, "Response Fail", Toast.LENGTH_SHORT).show();
                   return;
               }
                Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
               data.addAll(response.body());
               adapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                t.printStackTrace();

            }
        });

    }
}