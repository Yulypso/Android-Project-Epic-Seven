package com.github.androidproject.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.androidproject.Constants;
import com.github.androidproject.interfaces.EpicSevenApi;
import com.github.androidproject.models.Hero;
import com.github.androidproject.models.HeroInfo;
import com.github.androidproject.adapters.ListAdapter;
import com.github.androidproject.R;
import com.github.androidproject.controller.RestEpicSevenResponse;
import com.github.androidproject.controller.RestHeroInfoResponse;
import com.github.androidproject.controller.RetrieveHeroModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
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
    static List<Hero> heroList;
    List<Hero> notRetrievedHeroList = new ArrayList<>();
    List heroInfoList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Main view related to activity_main.xml

        sharedPreferences = getSharedPreferences(Constants.KEY_APPLICATION_NAME, Context.MODE_PRIVATE);

        //deleteDataInCache(); //remove current saved list from cache => test api calls

        gson = new GsonBuilder() //create gson object to convert List into String (json type)
                .setLenient()
                .create();

        List heroList = null; //get data from cache

        try{
            heroList = getDataFromCache(Constants.KEY_HERO_LIST);
            heroInfoList = getDataFromCache(Constants.KEY_HERO_INFO_LIST);

            assert heroList != null;
            Log.d("Test", "Taille liste" + heroList.size());
            Log.d("Test", "Taille liste" + heroInfoList.size());
        } catch (Exception e) {
            Log.d("Exception", "Impossible de récuperer les données depuis le cache");
        }

        if(heroList != null && heroInfoList != null){ //if data from cache is not null, we have data, we shows it
            new RetrieveHeroModel(heroList).execute();
            showList(heroList, heroInfoList);
            Toast.makeText(getApplicationContext(),"Load from Cache", Toast.LENGTH_SHORT).show();
        } else {
            makeApiCall(); //if no data from cache, we make an ApiCall to get Data from API

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
                    heroList = response.body().getResults();
                    heroInfoList = new ArrayList<>();
                    //saveList(Constants.KEY_HERO_LIST, heroList);

                    for(Hero hero : heroList) {
                        makeApiCall2(hero);
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

    private void makeApiCall2(final Hero hero){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        EpicSevenApi EpicSevenApi = retrofit.create(EpicSevenApi.class);

        Call<RestHeroInfoResponse> call = EpicSevenApi.getHeroInfoResponse(hero.get_id()); //on récupere les infos du héro id
        call.enqueue(new Callback<RestHeroInfoResponse>() {
            @Override
            public void onResponse(Call<RestHeroInfoResponse> call, Response<RestHeroInfoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<HeroInfo> heroInfoListTemp = response.body().getResults();
                    HeroInfo heroInfo = heroInfoListTemp.get(0); //convertir liste en objet heroInfo

                    heroInfoList.add(heroInfo);
                }else{
                    notRetrievedHeroList.add(hero); //heroes api couldn't fetch
                }

                if (heroInfoList.size() + notRetrievedHeroList.size() == heroList.size()) {
                    Toast.makeText(getApplicationContext(),"API Success 2", Toast.LENGTH_SHORT).show();
                    new RetrieveHeroModel(heroList).execute();
                    saveList(Constants.KEY_HERO_LIST, heroList);
                    saveList(Constants.KEY_HERO_INFO_LIST, heroInfoList);
                }
            }

            @Override
            public void onFailure(Call<RestHeroInfoResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "API Error 2", Toast.LENGTH_SHORT).show();
                //showError();
            }
        });
    }

    private void saveList(String storageKey, List list) {
        String jsonString = gson.toJson(list); //convert hero list into json format which is a String type

        sharedPreferences
                .edit()
                .putString(storageKey, jsonString)  //clé, String
                .apply();

        if(storageKey.equals(Constants.KEY_HERO_INFO_LIST)){
            Toast.makeText(getApplicationContext(),"List saved", Toast.LENGTH_SHORT).show();
        }
    }

    private void showError(){
        Toast.makeText(getApplicationContext(),"API Error", Toast.LENGTH_SHORT).show();
    }
}