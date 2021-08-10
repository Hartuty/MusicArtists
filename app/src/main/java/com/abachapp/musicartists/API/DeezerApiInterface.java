package com.abachapp.musicartists.API;

import com.abachapp.musicartists.Model.Deezers.Info;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface DeezerApiInterface {
    @Headers({
            "x-rapidapi-host:deezerdevs-deezer.p.rapidapi.com",
            "x-rapidapi-key:3af496f5f0msh03d2f8cfef0bc12p198e33jsn925159ca9e52"
    })
    @GET("search")
    Call<Info> showdata(@Query("q") String name);
}
