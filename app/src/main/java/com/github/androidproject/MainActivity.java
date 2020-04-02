package com.github.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Main view related to activity_main.xml

        showList();
        makeApiCall();
    }

    private void showList() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view); //search for recycler_view in activity main by id
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this); //horizontal / vertical
        recyclerView.setLayoutManager(layoutManager);


        //creating the content of the list input
        List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }

        //new comment
        // define an adapter and give input into ListAdapter
        mAdapter = new ListAdapter(input); //Manages the data model and adapts it to the individual entries in the widget
        recyclerView.setAdapter(mAdapter); //Assigning it to the recycler
    }

    private void makeApiCall(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

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
                    Toast.makeText(getApplicationContext(),"API Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RestEpicSevenResponse> call, Throwable t) {
                showError();
            }
        });
    }

    private void showError(){
        Toast.makeText(getApplicationContext(),"API Error", Toast.LENGTH_SHORT).show();
    }
}

