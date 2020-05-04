package com.github.androidproject;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.androidproject.data.EpicSevenApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static Gson gsonInstance;
    private static EpicSevenApi epicSevenApiInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static Gson getGson() {
        if(gsonInstance == null){
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }

    public static EpicSevenApi getEpicSevenApi() {
        if(epicSevenApiInstance == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            epicSevenApiInstance = retrofit.create(EpicSevenApi.class);
        }
        return epicSevenApiInstance;
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        if(sharedPreferencesInstance == null) {
            sharedPreferencesInstance = context.getSharedPreferences(Constants.KEY_APPLICATION_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferencesInstance;
    }
}
