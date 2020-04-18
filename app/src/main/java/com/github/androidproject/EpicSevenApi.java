package com.github.androidproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EpicSevenApi {
    @GET("/hero")
    Call<RestEpicSevenResponse> getHeroResponse();

    @GET("/hero/{id}")
    Call<RestHeroInfoResponse> getHeroInfoResponse(@Path("id") String id);
}
