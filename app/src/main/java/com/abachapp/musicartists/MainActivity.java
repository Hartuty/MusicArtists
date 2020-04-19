package com.abachapp.musicartists;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.abachapp.musicartists.API.ApiClient;
import com.abachapp.musicartists.API.ApiInterface;
import com.abachapp.musicartists.Model.Info;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getdata(View view) {
        ApiInterface apiInterface= ApiClient.getRetrofit().create(ApiInterface.class);
        String name="sauti sol";
        Call<Info> call=apiInterface.showdata(name);
        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {
                Toast.makeText(MainActivity.this,response.body().getData().get(0).getPreview(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_LONG).show();
            }
        });
    }
}
