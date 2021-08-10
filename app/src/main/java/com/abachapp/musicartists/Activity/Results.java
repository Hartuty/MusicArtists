package com.abachapp.musicartists.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.abachapp.musicartists.API.DeezerApiClient;
import com.abachapp.musicartists.API.DeezerApiInterface;
import com.abachapp.musicartists.Adapter.CustomAdapter;
import com.abachapp.musicartists.Model.Deezers.Datum;
import com.abachapp.musicartists.Model.Deezers.Info;
import com.abachapp.musicartists.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Results extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        recyclerView=findViewById(R.id.recycler_view_result);
        textView=findViewById(R.id.title_search);
        Intent intent=getIntent();
        String name= intent.getStringExtra("searchname");
        textView.setText(name);
        SearchResults(name);

    }

    public void SearchResults(String name) {
        DeezerApiInterface apiInterface= DeezerApiClient.getRetrofit().create(DeezerApiInterface.class);
        Toast.makeText(Results.this,name,Toast.LENGTH_LONG).show();
        Call<Info> call=apiInterface.showdata(name);
        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {
                List<Datum> datumList=response.body().getData();
                CustomAdapter customAdapter=new CustomAdapter(datumList,Results.this);
                LinearLayoutManager layoutManager= new LinearLayoutManager(Results.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(customAdapter);
            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {
                Toast.makeText(Results.this,"Failed",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    public void Return_Home(View view) {
        finish();
    }
}
