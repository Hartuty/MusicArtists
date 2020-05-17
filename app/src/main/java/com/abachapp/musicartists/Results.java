package com.abachapp.musicartists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.abachapp.musicartists.API.ApiClient;
import com.abachapp.musicartists.API.ApiInterface;
import com.abachapp.musicartists.Model.Datum;
import com.abachapp.musicartists.Model.Info;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Intent intent=getIntent();
        String name= intent.getStringExtra("searchname");
        SearchResults(name);

    }

    public void SearchResults(String name) {
        ApiInterface apiInterface= ApiClient.getRetrofit().create(ApiInterface.class);
        Toast.makeText(Results.this,name,Toast.LENGTH_LONG).show();
        Call<Info> call=apiInterface.showdata(name);
        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {

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
