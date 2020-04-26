package com.github.androidproject.interfaces;

import com.github.androidproject.controller.RestEpicSevenResponse;
import com.github.androidproject.controller.RestHeroInfoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EpicSevenApi {
    @GET("/hero")
    Call<RestEpicSevenResponse> getHeroResponse();

    @GET("/hero/{id}")
    Call<RestHeroInfoResponse> getHeroInfoResponse(@Path("id") String id);
}
