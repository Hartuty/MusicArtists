package com.abachapp.musicartists.API;

import com.abachapp.musicartists.Model.Pexels.Photo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface PexelApiInterface {

    @Headers("Authorization: 563492ad6f9170000100000156c43435effc45cba5a382c82604eb1b")
    @GET("{random}")
    Call<Photo>getphoto(@Path("random") int random);
}
