package com.github.androidproject.presentation.ui.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.androidproject.Constants;
import com.github.androidproject.Singletons;
import com.github.androidproject.data.EpicSevenApi;
import com.github.androidproject.presentation.ui.models.Hero;
import com.github.androidproject.presentation.ui.models.HeroInfo;
import com.github.androidproject.presentation.ui.models.RestEpicSevenResponse;
import com.github.androidproject.presentation.ui.models.RestHeroInfoResponse;
import com.github.androidproject.presentation.ui.view.ActivityInformation;
import com.github.androidproject.presentation.ui.view.MainActivity;
import com.github.androidproject.presentation.ui.view.PopUp;
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
    private HeroInfo currentHeroInfo;
    private static List<Hero> heroListFull;
    private static int totalRelations = 0;

    public MainController(MainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences) {
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart() {

        //deleteDataInCache();

        try {
            heroList = getDataFromCache(Constants.KEY_HERO_LIST);
            heroInfoList = getDataFromCache(Constants.KEY_HERO_INFO_LIST);

            assert heroList != null;
            Log.d("Test", "Taille liste" + heroList.size());
            Log.d("Test", "Taille liste" + heroInfoList.size());
        } catch (Exception e) {
            Log.d("Exception", "Impossible de récuperer les données depuis le cache");
        }

        if (heroList != null && heroInfoList != null) {
            this.heroListFull = new ArrayList<>(heroList);
            view.showList(heroList, heroInfoList);
            Toast.makeText(view.getApplicationContext(), "Load from Cache", Toast.LENGTH_SHORT).show();
        } else {
            makeApiCall(); //if no data from cache, we make an ApiCall to get Data from API
        }
    }

    private void makeApiCall() {
        Call<RestEpicSevenResponse> call = Singletons.getEpicSevenApi().getHeroResponse();
        call.enqueue(new Callback<RestEpicSevenResponse>() {
            @Override
            public void onResponse(Call<RestEpicSevenResponse> call, Response<RestEpicSevenResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    heroList = response.body().getResults();
                    heroInfoList = new ArrayList<>();

                    for (Hero hero : heroList) {
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

    private void makeApiCall2(final Hero hero) {
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
                } else {
                    notRetrievedHeroList.add(hero); //heroes api couldn't fetch
                }

                if (heroInfoList.size() + notRetrievedHeroList.size() == heroList.size()) {
                    saveList(Constants.KEY_HERO_LIST, heroList);
                    saveList(Constants.KEY_HERO_INFO_LIST, heroInfoList);
                    MainController.heroListFull = new ArrayList<>(heroList);
                    Toast.makeText(view.getApplicationContext(), "API Success 2", Toast.LENGTH_SHORT).show();
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

        if (storageKey.equals(Constants.KEY_HERO_INFO_LIST)) {
            Toast.makeText(view.getApplicationContext(), "List saved", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteDataInCache() {
        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(Constants.KEY_HERO_LIST);
            editor.remove(Constants.KEY_HERO_INFO_LIST);
            editor.apply();
        } catch (Exception e) {
            Log.d("Exception", "Failed to remove data in cache");
        }
    }

    private List getDataFromCache(String storageKey) {
        String data = sharedPreferences.getString(storageKey, null);

        if (data == null) {
            return null;
        } else {

            Type listType;
            if (storageKey.equals(Constants.KEY_HERO_LIST)) {
                listType = new TypeToken<List<Hero>>() {
                }.getType();//deserialize list
            } else {
                listType = new TypeToken<List<HeroInfo>>() {
                }.getType();
            }
            return gson.fromJson(data, listType);
        }
    }

    public void onItemClick(Hero currentHero) {
        currentHeroInfo = searchHeroInfoById(currentHero);
        if (currentHeroInfo != null) {
            Log.d("HeroInfo", "" + currentHeroInfo.get_id());
            openActivityInformation(view, currentHero, currentHeroInfo, heroInfoList);
        } else {
            Intent intent2 = new Intent(view, PopUp.class);
            intent2.putExtra("currentHeroMissing", currentHero);
            view.startActivity(intent2);
        }
    }

    private HeroInfo searchHeroInfoById(Hero hero) {
        for (HeroInfo hi : heroInfoList) {
            if (hi.get_id().equals(hero.get_id())) {
                return hi;
            }
        }
        return null;
    }

    private void openActivityInformation(MainActivity view, Hero hero, HeroInfo heroInfo, List<HeroInfo> heroInfoList) {

        Intent intent = new Intent(view, ActivityInformation.class);
        intent.putExtra(Constants.KEY_HERO, hero);
        intent.putExtra(Constants.KEY_HEROINFO, heroInfo);

        String imageUrl = heroInfo.getAssets().getImage();
        intent.putExtra(Constants.EXTRA_TEXT_IMAGE, imageUrl);

        String imageFullUrl = (hero.getModelURL());
        intent.putExtra(Constants.EXTRA_TEXT_FULL_IMAGE, imageFullUrl);

        List<HeroInfo> heroRelations = getRelations(heroInfoList, currentHeroInfo);
        sendRelations(intent, heroRelations);
        intent.putExtra(Constants.KEY_TOTALRELATIONS, totalRelations);

        view.startActivity(intent);
    }

    private List<HeroInfo> getRelations(List<HeroInfo> heroInfoList, HeroInfo currentHeroInfo) {
        List<HeroInfo> heroRelations = new ArrayList<>();
        totalRelations = currentHeroInfo.getRelationships().get(0).getRelations().size();

        if (totalRelations > 0) {
            for (HeroInfo HI : heroInfoList) {
                for (int i = 0; i < totalRelations; i++) {
                    for (Hero H : heroListFull) {
                        if ((H.get_id().equals(HI.get_id())) && (HI.getId().equals(currentHeroInfo.getRelationships().get(0).getRelations().get(i).getId())) && (!currentHeroInfo.getRelationships().get(0).getRelations().get(i).getId().contains("npc")) && (!currentHeroInfo.getRelationships().get(0).getRelations().get(i).getId().contains("m"))) {
                            heroRelations.add(HI);
                            System.out.println("ADDED ! " + HI.getId() + " " + HI.getName());
                        }
                    }
                }
            }
        }
        totalRelations = heroRelations.size();
        return heroRelations;
    }

    private void sendRelations(Intent intent, List<HeroInfo> heroRelations) {
        System.out.println("TOTAL RELATION after counter : " + totalRelations);
        if (totalRelations > 0) {
            switch (totalRelations) {
                default:
                    break;
                case 1:
                    if (heroRelations.get(0) != null)
                        intent.putExtra(Constants.HERO_INFO_1, heroRelations.get(0));
                    break;
                case 2:
                    if (heroRelations.get(0) != null)
                        intent.putExtra(Constants.HERO_INFO_1, heroRelations.get(0));
                    if (heroRelations.get(1) != null)
                        intent.putExtra(Constants.HERO_INFO_2, heroRelations.get(1));
                    break;
                case 3:
                    if (heroRelations.get(0) != null)
                        intent.putExtra(Constants.HERO_INFO_1, heroRelations.get(0));
                    if (heroRelations.get(1) != null)
                        intent.putExtra(Constants.HERO_INFO_2, heroRelations.get(1));
                    if (heroRelations.get(2) != null)
                        intent.putExtra(Constants.HERO_INFO_3, heroRelations.get(2));
                    break;
                case 4:
                    if (heroRelations.get(0) != null)
                        intent.putExtra(Constants.HERO_INFO_1, heroRelations.get(0));
                    if (heroRelations.get(1) != null)
                        intent.putExtra(Constants.HERO_INFO_2, heroRelations.get(1));
                    if (heroRelations.get(2) != null)
                        intent.putExtra(Constants.HERO_INFO_3, heroRelations.get(2));
                    if (heroRelations.get(3) != null)
                        intent.putExtra(Constants.HERO_INFO_4, heroRelations.get(3));
                    break;
                case 5:
                    if (heroRelations.get(0) != null)
                        intent.putExtra(Constants.HERO_INFO_1, heroRelations.get(0));
                    if (heroRelations.get(1) != null)
                        intent.putExtra(Constants.HERO_INFO_2, heroRelations.get(1));
                    if (heroRelations.get(2) != null)
                        intent.putExtra(Constants.HERO_INFO_3, heroRelations.get(2));
                    if (heroRelations.get(3) != null)
                        intent.putExtra(Constants.HERO_INFO_4, heroRelations.get(3));
                    if (heroRelations.get(4) != null)
                        intent.putExtra(Constants.HERO_INFO_5, heroRelations.get(4));
                    break;
                case 6:
                    if (heroRelations.get(0) != null)
                        intent.putExtra(Constants.HERO_INFO_1, heroRelations.get(0));
                    if (heroRelations.get(1) != null)
                        intent.putExtra(Constants.HERO_INFO_2, heroRelations.get(1));
                    if (heroRelations.get(2) != null)
                        intent.putExtra(Constants.HERO_INFO_3, heroRelations.get(2));
                    if (heroRelations.get(3) != null)
                        intent.putExtra(Constants.HERO_INFO_4, heroRelations.get(3));
                    if (heroRelations.get(4) != null)
                        intent.putExtra(Constants.HERO_INFO_5, heroRelations.get(4));
                    if (heroRelations.get(5) != null)
                        intent.putExtra(Constants.HERO_INFO_6, heroRelations.get(5));
                    break;
                case 7:
                    if (heroRelations.get(0) != null)
                        intent.putExtra(Constants.HERO_INFO_1, heroRelations.get(0));
                    if (heroRelations.get(1) != null)
                        intent.putExtra(Constants.HERO_INFO_2, heroRelations.get(1));
                    if (heroRelations.get(2) != null)
                        intent.putExtra(Constants.HERO_INFO_3, heroRelations.get(2));
                    if (heroRelations.get(3) != null)
                        intent.putExtra(Constants.HERO_INFO_4, heroRelations.get(3));
                    if (heroRelations.get(4) != null)
                        intent.putExtra(Constants.HERO_INFO_5, heroRelations.get(4));
                    if (heroRelations.get(5) != null)
                        intent.putExtra(Constants.HERO_INFO_6, heroRelations.get(5));
                    if (heroRelations.get(6) != null)
                        intent.putExtra(Constants.HERO_INFO_7, heroRelations.get(6));
                    break;
                case 8:
                    if (heroRelations.get(0) != null)
                        intent.putExtra(Constants.HERO_INFO_1, heroRelations.get(0));
                    if (heroRelations.get(1) != null)
                        intent.putExtra(Constants.HERO_INFO_2, heroRelations.get(1));
                    if (heroRelations.get(2) != null)
                        intent.putExtra(Constants.HERO_INFO_3, heroRelations.get(2));
                    if (heroRelations.get(3) != null)
                        intent.putExtra(Constants.HERO_INFO_4, heroRelations.get(3));
                    if (heroRelations.get(4) != null)
                        intent.putExtra(Constants.HERO_INFO_5, heroRelations.get(4));
                    if (heroRelations.get(5) != null)
                        intent.putExtra(Constants.HERO_INFO_6, heroRelations.get(5));
                    if (heroRelations.get(6) != null)
                        intent.putExtra(Constants.HERO_INFO_7, heroRelations.get(6));
                    if (heroRelations.get(7) != null)
                        intent.putExtra(Constants.HERO_INFO_8, heroRelations.get(7));
                    break;
                case 9:
                    if (heroRelations.get(0) != null)
                        intent.putExtra(Constants.HERO_INFO_1, heroRelations.get(0));
                    if (heroRelations.get(1) != null)
                        intent.putExtra(Constants.HERO_INFO_2, heroRelations.get(1));
                    if (heroRelations.get(2) != null)
                        intent.putExtra(Constants.HERO_INFO_3, heroRelations.get(2));
                    if (heroRelations.get(3) != null)
                        intent.putExtra(Constants.HERO_INFO_4, heroRelations.get(3));
                    if (heroRelations.get(4) != null)
                        intent.putExtra(Constants.HERO_INFO_5, heroRelations.get(4));
                    if (heroRelations.get(5) != null)
                        intent.putExtra(Constants.HERO_INFO_6, heroRelations.get(5));
                    if (heroRelations.get(6) != null)
                        intent.putExtra(Constants.HERO_INFO_7, heroRelations.get(6));
                    if (heroRelations.get(7) != null)
                        intent.putExtra(Constants.HERO_INFO_8, heroRelations.get(7));
                    if (heroRelations.get(8) != null)
                        intent.putExtra(Constants.HERO_INFO_9, heroRelations.get(8));
                    break;
                case 10:
                    if (heroRelations.get(0) != null)
                        intent.putExtra(Constants.HERO_INFO_1, heroRelations.get(0));
                    if (heroRelations.get(1) != null)
                        intent.putExtra(Constants.HERO_INFO_2, heroRelations.get(1));
                    if (heroRelations.get(2) != null)
                        intent.putExtra(Constants.HERO_INFO_3, heroRelations.get(2));
                    if (heroRelations.get(3) != null)
                        intent.putExtra(Constants.HERO_INFO_4, heroRelations.get(3));
                    if (heroRelations.get(4) != null)
                        intent.putExtra(Constants.HERO_INFO_5, heroRelations.get(4));
                    if (heroRelations.get(5) != null)
                        intent.putExtra(Constants.HERO_INFO_6, heroRelations.get(5));
                    if (heroRelations.get(6) != null)
                        intent.putExtra(Constants.HERO_INFO_7, heroRelations.get(6));
                    if (heroRelations.get(7) != null)
                        intent.putExtra(Constants.HERO_INFO_8, heroRelations.get(7));
                    if (heroRelations.get(8) != null)
                        intent.putExtra(Constants.HERO_INFO_9, heroRelations.get(8));
                    if (heroRelations.get(9) != null)
                        intent.putExtra(Constants.HERO_INFO_10, heroRelations.get(9));
                    break;
            }
        }
    }
}
