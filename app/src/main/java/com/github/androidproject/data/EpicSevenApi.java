package com.github.androidproject.data;

import com.github.androidproject.presentation.ui.models.RestEpicSevenResponse;
import com.github.androidproject.presentation.ui.models.RestHeroInfoResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EpicSevenApi {
    @GET("/hero")
    Call<RestEpicSevenResponse> getHeroResponse();

    @GET("/hero/{id}")
    Call<RestHeroInfoResponse> getHeroInfoResponse(@Path("id") String id);
}
