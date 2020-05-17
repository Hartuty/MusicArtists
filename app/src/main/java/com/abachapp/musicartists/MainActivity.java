package com.abachapp.musicartists;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abachapp.musicartists.API.ApiClient;
import com.abachapp.musicartists.API.ApiInterface;
import com.abachapp.musicartists.Model.Info;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.btnsearch);
        editText=findViewById(R.id.searchname);
    }

    public void getdata(View view) {
        ApiInterface apiInterface= ApiClient.getRetrofit().create(ApiInterface.class);
        String name=editText.getText().toString();
        Toast.makeText(MainActivity.this,name,Toast.LENGTH_LONG).show();
        Call<Info> call=apiInterface.showdata(name);
        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {

            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void showdialog(String Artist, String Albumname)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("Results");
        builder.setMessage("The name of the Artist is " + Artist + " " + " The album in question is " + Albumname);
        builder.create();
    }
}
