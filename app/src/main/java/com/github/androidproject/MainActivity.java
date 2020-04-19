package com.github.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity { //main activity

    private static final String BASE_URL = "https://api.epicsevendb.com/";

    //defining variables
    private RecyclerView recyclerView; //To display a collection of data
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences; //to save data
    private Gson gson;
    boolean isLastHero = false;
    List<HeroInfo> heroInfoList = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Main view related to activity_main.xml

        sharedPreferences = getSharedPreferences(Constants.KEY_APPLICATION_NAME, Context.MODE_PRIVATE);
        gson = new GsonBuilder() //create gson object to convert List into String (json type)
                .setLenient()
                .create();


        List<Hero> heroList = null; //get data from cache
       //heroInfoList = null;

        try{
            heroList = getHeroDataInCache();
            heroInfoList = getHeroInfoDataInCache();
        } catch (Exception e) {
            Log.d("Exception", "Impossible de récuperer les données depuis le cache");
        }

        if(heroList != null && heroInfoList != null){ //if data from cache is not null, we have data, we shows it

            showList(heroList, heroInfoList);
            Toast.makeText(getApplicationContext(),"Load from Cache", Toast.LENGTH_SHORT).show();
        } else {
            makeApiCall(); //if no data from cache, we make an ApiCall to get Data from API
        }
    }

    private List<Hero> getHeroDataInCache() {
        String jsonHero = sharedPreferences.getString(Constants.KEY_HERO_LIST, null);

        if(jsonHero == null){
            return null;
        } else {
            Type listType = new TypeToken<List<Hero>>(){}.getType(); //deserialize list
            return gson.fromJson(jsonHero, listType);
        }
    }


    private List<HeroInfo> getHeroInfoDataInCache() {
        String jsonHeroInfo = sharedPreferences.getString(Constants.KEY_HERO_INFO_LIST, null);

        if(jsonHeroInfo == null){
            return null;
        } else {
            Type listType = new TypeToken<List<HeroInfo>>(){}.getType(); //deserialize list
            return gson.fromJson(jsonHeroInfo, listType);
        }
    }

    private void showList(List<Hero> heroList, List<HeroInfo> heroInfoList) {
        recyclerView = findViewById(R.id.recycler_view); //search for recycler_view in activity main by id
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this); //horizontal / vertical
        recyclerView.setLayoutManager(layoutManager);


        // define an adapter and give input into ListAdapter
        mAdapter = new ListAdapter(heroList, heroInfoList); //Manages the data model and adapts it to the individual entries in the widget
        recyclerView.setAdapter(mAdapter); //Assigning it to the recycler
    }

    private void makeApiCall(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        EpicSevenApi EpicSevenApi = retrofit.create(EpicSevenApi.class);
        Call<RestEpicSevenResponse> call = EpicSevenApi.getHeroResponse();
        call.enqueue(new Callback<RestEpicSevenResponse>() {
            @Override
            public void onResponse(Call<RestEpicSevenResponse> call, Response<RestEpicSevenResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Hero> heroList = response.body().getResults();

                    heroInfoList = new ArrayList<>();
                    saveList(heroList);

                    int cpt = 0;
                    for(Hero hero : heroList) {
                        if(cpt == heroList.size()-1){
                            Log.d("COMPTEUR", "COMPTEUR");
                            isLastHero = true;
                        }
                        makeApiCall2(hero.get_id());
                        cpt++;
                    }
                    showList(heroList, heroInfoList);
                }
            }

            @Override
            public void onFailure(Call<RestEpicSevenResponse> call, Throwable t) {
                showError();
            }
        });
    }

    private void makeApiCall2(String id){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        EpicSevenApi EpicSevenApi = retrofit.create(EpicSevenApi.class);

        Call<RestHeroInfoResponse> call = EpicSevenApi.getHeroInfoResponse(id); //on récupere les infos du héro id
        call.enqueue(new Callback<RestHeroInfoResponse>() {

            @Override
            public void onResponse(Call<RestHeroInfoResponse> call, Response<RestHeroInfoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<HeroInfo> heroInfoListTemp = response.body().getResults();

                    HeroInfo heroInfo = heroInfoListTemp.get(0); //convertir liste en objet heroInfo

                    heroInfoList.add(heroInfo);

                    if (isLastHero) {
                        saveHeroInfoList(heroInfoList);
                        Toast.makeText(getApplicationContext(),"API Success 2", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RestHeroInfoResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "API Error 2", Toast.LENGTH_SHORT).show();
                    //showError();
                }
        });
    }

    private void saveList(List<Hero> heroList) {
        String jsonString = gson.toJson(heroList); //convert hero list into json format which is a String type

        sharedPreferences
                .edit()
                .putString(Constants.KEY_HERO_LIST, jsonString)  //clé, String
                .apply();

        //Toast.makeText(getApplicationContext(),"List saved", Toast.LENGTH_SHORT).show();
    }

    private void saveHeroInfoList(List<HeroInfo> heroInfoList) {
        String jsonString = gson.toJson(heroInfoList); //convert hero list into json format which is a String type

        sharedPreferences
                .edit()
                .putString(Constants.KEY_HERO_INFO_LIST, jsonString)  //clé, String
                .apply();

        Toast.makeText(getApplicationContext(),"List saved", Toast.LENGTH_SHORT).show();
    }

    private void showError(){
        Toast.makeText(getApplicationContext(),"API Error", Toast.LENGTH_SHORT).show();
    }
}