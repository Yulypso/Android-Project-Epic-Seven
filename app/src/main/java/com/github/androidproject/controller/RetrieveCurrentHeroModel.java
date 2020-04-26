package com.github.androidproject.controller;

import android.os.AsyncTask;
import android.util.Log;

import com.github.androidproject.models.Hero;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class RetrieveCurrentHeroModel extends AsyncTask<String, Void, String> {

    private static Hero hero;

    public RetrieveCurrentHeroModel(Hero hero) {
        RetrieveCurrentHeroModel.hero = hero;
    }

    private Exception exception;

    protected String doInBackground(String... urls) {
        URL uneURL = null;

        try {
            String modelURL = "https://assets.epicsevendb.com/herofull/" + hero.get_id() + ".png";
            uneURL = new URL(modelURL);
            HttpURLConnection httpConnexion = (HttpURLConnection) uneURL.openConnection();
            httpConnexion.setRequestMethod("HEAD");
            httpConnexion.connect();
            Log.d("Search ",modelURL);

            int code = httpConnexion.getResponseCode();
            if (code == 200) {
                Log.d("Info ","Page Found");
                hero.setModelURL(modelURL);
                Log.d("Info ","retrieved: " + hero.getModelURL());
                httpConnexion.disconnect();
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}