package com.github.androidproject.controller;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.androidproject.Constants;
import com.github.androidproject.R;
import com.github.androidproject.models.Hero;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

public class RetrieveHeroModel extends AppCompatActivity{

    private static Hero hero;

    public RetrieveHeroModel(Hero hero) {
        RetrieveHeroModel.hero = hero;
    }

    private Exception exception;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        URL uneURL = null;

        try {
            String modelURL = "https://assets.epicsevendb.com/herofull/" + hero.get_id() + ".png";
            uneURL = new URL(modelURL);
            HttpURLConnection httpConnexion = (HttpURLConnection) uneURL.openConnection();
            httpConnexion.setRequestMethod("HEAD");
            httpConnexion.connect();
            Log.d("Search ", modelURL);

            int code = httpConnexion.getResponseCode();
            if (code == 200) {
                Log.d("Info ", "Page Found");
                hero.setModelURL(modelURL);
                Log.d("Info ", "retrieved: " + hero.getModelURL());
                httpConnexion.disconnect();
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}