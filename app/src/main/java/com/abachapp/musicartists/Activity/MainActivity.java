package com.abachapp.musicartists.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.abachapp.musicartists.API.DeezerApiClient;
import com.abachapp.musicartists.API.DeezerApiInterface;
import com.abachapp.musicartists.API.PexelApiClient;
import com.abachapp.musicartists.API.PexelApiInterface;
import com.abachapp.musicartists.Adapter.CustomAdapter;
import com.abachapp.musicartists.Model.Deezers.Datum;
import com.abachapp.musicartists.Model.Deezers.Info;
import com.abachapp.musicartists.Model.Pexels.Photo;
import com.abachapp.musicartists.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.image_home);
        editText=findViewById(R.id.searchname);
        getphoto();
    }

    private void getphoto() {
        PexelApiInterface apiInterface= PexelApiClient.getRetrofit().create(PexelApiInterface.class);
        int min = 1500000;
        int max = 2000000;
        int random = new Random().nextInt((max - min) + 1) + min;
        Call<Photo> call=apiInterface.getphoto(random);
        call.enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(Call<Photo> call, Response<Photo> response) {
                String image=response.body().getSrc().getPortrait();
                String photographer=response.body().getPhotographer();
                Picasso.get().load(image).into(imageView);
                Toast.makeText(MainActivity.this,"Image by "+photographer,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Photo> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Failed to Load",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getdata(View view) {
        Intent intent=new Intent(MainActivity.this, Results.class);
        String search=editText.getText().toString();
        intent.putExtra("searchname",search);
        startActivity(intent);
    }


}
