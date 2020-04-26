package com.github.androidproject.controller;

import android.os.AsyncTask;

import com.github.androidproject.models.Hero;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

public class RetrieveHeroModel extends AsyncTask<String, Void, String> {

    private static List<Hero> heroList;

    public RetrieveHeroModel(List<Hero> heroList){
        RetrieveHeroModel.heroList = heroList;
    }

    private Exception exception;

    protected String doInBackground(String... urls) {
        URL uneURL=null;
        int ch;

        try {
            for(Hero hero : RetrieveHeroModel.heroList) {
                String modelURL = "https://assets.epicsevendb.com/herofull/" + hero.get_id() + ".png";
                uneURL = new URL(modelURL);
                HttpURLConnection httpConnexion = (HttpURLConnection) uneURL.openConnection();
                httpConnexion.setRequestMethod ("HEAD");
                httpConnexion.connect();

                System.out.println("Search: "+modelURL);

                int code = httpConnexion.getResponseCode();
                if(code == 200) {
                    System.out.println("Page Found");
                    hero.setModelURL(modelURL);
                    System.out.print("retrieved: "+hero.getModelURL());
                    httpConnexion.disconnect();
                }
            }
        }catch(MalformedURLException mue){
            mue.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}