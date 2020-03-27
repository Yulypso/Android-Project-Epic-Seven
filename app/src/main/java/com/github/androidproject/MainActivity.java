package com.github.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity { //main activity

    //defining variables
    private RecyclerView recyclerView; //To display a collection of data
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Main view related to activity_main.xml

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
}

