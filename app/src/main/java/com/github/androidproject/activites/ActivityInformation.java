package com.github.androidproject.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.androidproject.adapters.ListAdapter;
import com.github.androidproject.R;
import com.github.androidproject.models.Hero;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.Objects;

public class ActivityInformation extends AppCompatActivity {

    public static String wallpaper = "https://assets.epicsevendb.com/website/summon/gacha_get_bg0_e7db.jpg";
    public static String layout = "https://assets.epicsevendb.com/website/summon/gacha_get_bg1.png";

    Intent intent;

    Hero currentHero;

    String name;
    String imageUrl;
    String imageFullUrl;

    ImageView wallpaperView;
    ImageView layoutView;
    TextView textViewInf;
    TextView textRoleView;
    TextView textElementView;
    TextView textZodiacView;

    ImageView imageViewImage;
    ImageView imageFullUrlView;
    ImageView starViewImage1;
    ImageView starViewImage2;
    ImageView starViewImage3;
    ImageView starViewImage4;
    ImageView starViewImage5;
    ImageView roleViewImage;
    ImageView elementViewImage;
    ImageView zodiacViewImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        intent = getIntent();
        currentHero = intent.getParcelableExtra("Hero");



        imageUrl = intent.getStringExtra(ListAdapter.EXTRA_TEXT_IMAGE);
        imageFullUrl = intent.getStringExtra(ListAdapter.EXTRA_TEXT_FULL_IMAGE);
        name = intent.getStringExtra(ListAdapter.EXTRA_TEXT_NAME);

        wallpaperView = (ImageView) findViewById(R.id.wallpaperView);
        layoutView = (ImageView) findViewById(R.id.layoutViewImage);
        textViewInf = (TextView) findViewById(R.id.textViewInf);
        imageViewImage = (ImageView) findViewById(R.id.imageViewImage);
        imageFullUrlView = (ImageView) findViewById(R.id.imageFullUrlView);
        textRoleView = (TextView) findViewById(R.id.textRoleView);
        textElementView = (TextView) findViewById(R.id.textElementView);
        textZodiacView = (TextView) findViewById(R.id.textZodiacView);

        starViewImage1 = (ImageView) findViewById(R.id.starViewImage1);
        starViewImage2 = (ImageView) findViewById(R.id.starViewImage2);
        starViewImage3 = (ImageView) findViewById(R.id.starViewImage3);
        starViewImage4 = (ImageView) findViewById(R.id.starViewImage4);
        starViewImage5 = (ImageView) findViewById(R.id.starViewImage5);
        roleViewImage = (ImageView) findViewById(R.id.roleViewImage);
        elementViewImage = (ImageView) findViewById(R.id.elementViewImage);
        zodiacViewImage = (ImageView) findViewById(R.id.zodiacViewImage);

        Picasso.get().load(wallpaper).into(wallpaperView);
        Picasso.get().load(layout).into(layoutView);
        Picasso.get().load(imageUrl).into(imageViewImage);
        Picasso.get().load(imageFullUrl).into(imageFullUrlView);


        textViewInf.setText(name);

        DisplayRarity(currentHero);
        DisplayRole(currentHero);
        DisplayElement(currentHero);
        DisplayZodiac(currentHero);
    }

    private void DisplayZodiac(Hero currentHero){
        switch (currentHero.getZodiac()) {
            case "ram":
                zodiacViewImage.setImageResource(R.drawable.aries);
                break;
            case "bull":
                zodiacViewImage.setImageResource(R.drawable.taurus);
                break;
            case "twins":
                zodiacViewImage.setImageResource(R.drawable.gemini);
                break;
            case "crab":
                zodiacViewImage.setImageResource(R.drawable.cancer);
                break;
            case "lion":
                zodiacViewImage.setImageResource(R.drawable.leo);
                break;
            case "maiden":
                zodiacViewImage.setImageResource(R.drawable.virgo);
                break;
            case "scales":
                zodiacViewImage.setImageResource(R.drawable.libra);
                break;
            case "scorpion":
                zodiacViewImage.setImageResource(R.drawable.scorpio);
                break;
            case "archer":
                zodiacViewImage.setImageResource(R.drawable.sagittarius);
                break;
            case "goat":
                zodiacViewImage.setImageResource(R.drawable.capricorn);
                break;
            case "waterbearer":
                zodiacViewImage.setImageResource(R.drawable.aquarius);
                break;
            case "fish":
                zodiacViewImage.setImageResource(R.drawable.pisces);
                break;
        }
        textZodiacView.setText(currentHero.getZodiac());
    }

    private void DisplayElement(Hero currentHero){
        switch (currentHero.getAttribute()) {
            case "fire":
                elementViewImage.setImageResource(R.drawable.cm_icon_profire);
                break;
            case "wind":
                elementViewImage.setImageResource(R.drawable.cm_icon_proearth);
                break;
            case "ice":
                elementViewImage.setImageResource(R.drawable.cm_icon_proice);
                break;
            case "light":
                elementViewImage.setImageResource(R.drawable.cm_icon_promlight);
                break;
            case "dark":
                elementViewImage.setImageResource(R.drawable.cm_icon_promdark);
                break;
        }
        textElementView.setText(currentHero.getAttribute());
    }

    public void DisplayRarity(Hero currentHero){
        if(currentHero.getRarity() == 1) {
            starViewImage5.setImageResource(R.drawable.cm_icon_star);
            starViewImage4.setImageResource(0);
            starViewImage3.setImageResource(0);
            starViewImage2.setImageResource(0);
            starViewImage1.setImageResource(0);
        } else if (currentHero.getRarity() == 2){
            starViewImage5.setImageResource(R.drawable.cm_icon_star);
            starViewImage4.setImageResource(R.drawable.cm_icon_star);
            starViewImage3.setImageResource(0);
            starViewImage2.setImageResource(0);
            starViewImage1.setImageResource(0);
        }else if (currentHero.getRarity() == 3){
            starViewImage5.setImageResource(R.drawable.cm_icon_star);
            starViewImage4.setImageResource(R.drawable.cm_icon_star);
            starViewImage3.setImageResource(R.drawable.cm_icon_star);
            starViewImage2.setImageResource(0);
            starViewImage1.setImageResource(0);
        }else if (currentHero.getRarity() == 4){
            starViewImage5.setImageResource(R.drawable.cm_icon_star);
            starViewImage4.setImageResource(R.drawable.cm_icon_star);
            starViewImage3.setImageResource(R.drawable.cm_icon_star);
            starViewImage2.setImageResource(R.drawable.cm_icon_star);
            starViewImage1.setImageResource(0);
        }else if (currentHero.getRarity() == 5){
            starViewImage5.setImageResource(R.drawable.cm_icon_star);
            starViewImage4.setImageResource(R.drawable.cm_icon_star);
            starViewImage3.setImageResource(R.drawable.cm_icon_star);
            starViewImage2.setImageResource(R.drawable.cm_icon_star);
            starViewImage1.setImageResource(R.drawable.cm_icon_star);
        }
    }

    private void DisplayRole(Hero currentHero){
        switch (currentHero.getRole()) {
            case "knight":
                roleViewImage.setImageResource(R.drawable.cm_icon_role_knight);
                break;
            case "warrior":
                roleViewImage.setImageResource(R.drawable.cm_icon_role_warrior);
                break;
            case "assassin":
                roleViewImage.setImageResource(R.drawable.cm_icon_role_thief);
                break;
            case "mage":
                roleViewImage.setImageResource(R.drawable.cm_icon_role_mage);
                break;
            case "manauser":
                roleViewImage.setImageResource(R.drawable.cm_icon_role_soulweaver);
                break;
            case "ranger":
                roleViewImage.setImageResource(R.drawable.cm_icon_role_ranger);
                break;
            case "material":
                roleViewImage.setImageResource(R.drawable.cm_icon_role_material);
                break;
        }
        textRoleView.setText(currentHero.getRole());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
