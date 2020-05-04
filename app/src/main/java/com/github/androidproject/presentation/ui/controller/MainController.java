package com.github.androidproject.presentation.ui.controller;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.github.androidproject.Constants;
import com.github.androidproject.Singletons;
import com.github.androidproject.data.EpicSevenApi;
import com.github.androidproject.presentation.ui.models.Hero;
import com.github.androidproject.presentation.ui.models.HeroInfo;
import com.github.androidproject.presentation.ui.models.RestEpicSevenResponse;
import com.github.androidproject.presentation.ui.models.RestHeroInfoResponse;
import com.github.androidproject.presentation.ui.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {

    private MainActivity view;
    private List<Hero> heroList = new ArrayList<>();
    private List<HeroInfo> heroInfoList = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private List<Hero> notRetrievedHeroList = new ArrayList<>();

    public MainController(MainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences) {
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart() {

        //deleteDataInCache(); //remove current saved list from cache => test api calls.

        try{
            heroList = getDataFromCache(Constants.KEY_HERO_LIST);
            heroInfoList = getDataFromCache(Constants.KEY_HERO_INFO_LIST);

            assert heroList != null;
            Log.d("Test", "Taille liste" + heroList.size());
            Log.d("Test", "Taille liste" + heroInfoList.size());
        } catch (Exception e) {
            Log.d("Exception", "Impossible de récuperer les données depuis le cache");
        }

        if(heroList != null && heroInfoList != null){
            view.showList(heroList, heroInfoList);
            Toast.makeText(view.getApplicationContext(),"Load from Cache", Toast.LENGTH_SHORT).show();
        } else {
            makeApiCall(); //if no data from cache, we make an ApiCall to get Data from API
        }
    }

    private void makeApiCall(){
        Call<RestEpicSevenResponse> call = Singletons.getEpicSevenApi().getHeroResponse();
        call.enqueue(new Callback<RestEpicSevenResponse>() {
            @Override
            public void onResponse(Call<RestEpicSevenResponse> call, Response<RestEpicSevenResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    heroList = response.body().getResults();
                    heroInfoList = new ArrayList<>();

                    for(Hero hero : heroList) {
                        makeApiCall2(hero);
                    }
                    view.showList(heroList, heroInfoList);
                }
            }
            @Override
            public void onFailure(Call<RestEpicSevenResponse> call, Throwable t) {
                view.showError();
            }
        });
    }

    private void makeApiCall2(final Hero hero){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        EpicSevenApi EpicSevenApi = retrofit.create(EpicSevenApi.class);

        Call<RestHeroInfoResponse> call = EpicSevenApi.getHeroInfoResponse(hero.get_id());
        call.enqueue(new Callback<RestHeroInfoResponse>() {
            @Override
            public void onResponse(Call<RestHeroInfoResponse> call, Response<RestHeroInfoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<HeroInfo> heroInfoListTemp = response.body().getResults();
                    HeroInfo heroInfo = heroInfoListTemp.get(0);

                    heroInfoList.add(heroInfo);
                }else{
                    notRetrievedHeroList.add(hero); //heroes api couldn't fetch
                }

                if (heroInfoList.size() + notRetrievedHeroList.size() == heroList.size()) {
                    saveList(Constants.KEY_HERO_LIST, heroList);
                    saveList(Constants.KEY_HERO_INFO_LIST, heroInfoList);
                    Toast.makeText(view.getApplicationContext(),"API Success 2", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RestHeroInfoResponse> call, Throwable t) {
                view.showError();
            }
        });
    }

    private void saveList(String storageKey, List list) {
        String jsonString = gson.toJson(list);

        sharedPreferences
                .edit()
                .putString(storageKey, jsonString)  //clé, String
                .apply();

        if(storageKey.equals(Constants.KEY_HERO_INFO_LIST)){
            Toast.makeText(view.getApplicationContext(),"List saved", Toast.LENGTH_SHORT).show();
        }
    }


    private void deleteDataInCache(){
        try{
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(Constants.KEY_HERO_LIST);
            editor.remove(Constants.KEY_HERO_INFO_LIST);
            editor.apply();
        }catch (Exception e) {
            Log.d("Exception", "Failed to remove data in cache");
        }
    }

    private List getDataFromCache(String storageKey) {
        String data = sharedPreferences.getString(storageKey, null);

        if(data == null){
            return null;
        } else {

            Type listType;
            if(storageKey.equals(Constants.KEY_HERO_LIST)){
                listType = new TypeToken<List<Hero>>(){}.getType();//deserialize list
            }else{
                listType = new TypeToken<List<HeroInfo>>(){}.getType();
            }
            return gson.fromJson(data, listType);
        }
    }

}
