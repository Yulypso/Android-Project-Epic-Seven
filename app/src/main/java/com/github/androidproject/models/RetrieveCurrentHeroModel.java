package com.github.androidproject.models;

import android.os.AsyncTask;
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
        int ch;

        try {
            String modelURL = "https://assets.epicsevendb.com/herofull/" + hero.get_id() + ".png";
            uneURL = new URL(modelURL);
            HttpURLConnection httpConnexion = (HttpURLConnection) uneURL.openConnection();
            httpConnexion.setRequestMethod("HEAD");
            httpConnexion.connect();

            System.out.println("Search: " + modelURL);

            int code = httpConnexion.getResponseCode();
            if (code == 200) {
                System.out.println("Page Found");
                hero.setModelURL(modelURL);
                System.out.print("retrieved: " + hero.getModelURL());
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