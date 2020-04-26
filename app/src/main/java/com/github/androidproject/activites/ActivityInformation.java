package com.github.androidproject.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.androidproject.adapters.ListAdapter;
import com.github.androidproject.R;
import com.github.androidproject.models.Hero;
import com.github.androidproject.models.HeroInfo;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.Objects;

public class ActivityInformation extends AppCompatActivity {

    public static String wallpaper = "https://assets.epicsevendb.com/website/summon/gacha_get_bg0_e7db.jpg";
    public static String layout = "https://assets.epicsevendb.com/website/summon/gacha_get_bg1.png";

    Intent intent;

    private static int totalRelations;

    Hero currentHero;
    HeroInfo currentHeroInfo;

    HeroInfo HeroInfoRelation1;
    HeroInfo HeroInfoRelation2;
    HeroInfo HeroInfoRelation3;
    HeroInfo HeroInfoRelation4;
    HeroInfo HeroInfoRelation5;
    HeroInfo HeroInfoRelation6;
    HeroInfo HeroInfoRelation7;
    HeroInfo HeroInfoRelation8;
    HeroInfo HeroInfoRelation9;
    HeroInfo HeroInfoRelation10;


    String imageUrl;
    String imageFullUrl;

    ImageView wallpaperView;
    ImageView layoutView;
    TextView textViewInf;
    TextView textRoleView;
    TextView textElementView;
    TextView textZodiacView;
    TextView get_lineView;
    TextView storyView;

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

    ImageView relation1View;
    ImageView relation2View;
    ImageView relation3View;
    ImageView relation4View;
    ImageView relation5View;
    ImageView relation6View;
    ImageView relation7View;
    ImageView relation8View;
    ImageView relation9View;
    ImageView relation10View;

    TextView relation1TextView;
    TextView relation2TextView;
    TextView relation3TextView;
    TextView relation4TextView;
    TextView relation5TextView;
    TextView relation6TextView;
    TextView relation7TextView;
    TextView relation8TextView;
    TextView relation9TextView;
    TextView relation10TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Log.d("Info", "coucou je suis arrivé à l'activity 2 !");
        intent = getIntent();
        currentHero = intent.getParcelableExtra("Hero");
        currentHeroInfo = intent.getParcelableExtra("HeroInfo");
        totalRelations = Objects.requireNonNull(intent.getExtras()).getInt("totalRelations");

        getHeroInfoRelations(intent);

        imageUrl = intent.getStringExtra(ListAdapter.EXTRA_TEXT_IMAGE);
        imageFullUrl = intent.getStringExtra(ListAdapter.EXTRA_TEXT_FULL_IMAGE);

        imageViewImage = (ImageView) findViewById(R.id.imageViewImage);
        imageFullUrlView = (ImageView) findViewById(R.id.imageFullUrlView);
        wallpaperView = (ImageView) findViewById(R.id.wallpaperView);
        layoutView = (ImageView) findViewById(R.id.layoutViewImage);
        textViewInf = (TextView) findViewById(R.id.textViewInf);
        textRoleView = (TextView) findViewById(R.id.textRoleView);
        textElementView = (TextView) findViewById(R.id.textElementView);
        textZodiacView = (TextView) findViewById(R.id.textZodiacView);
        get_lineView = (TextView) findViewById(R.id.get_lineView);
        storyView = (TextView) findViewById(R.id.storyView);

        relation1TextView = (TextView) findViewById(R.id.relation1TextView);
        relation2TextView = (TextView) findViewById(R.id.relation2TextView);
        relation3TextView = (TextView) findViewById(R.id.relation3TextView);
        relation4TextView = (TextView) findViewById(R.id.relation4TextView);
        relation5TextView = (TextView) findViewById(R.id.relation5TextView);
        relation6TextView = (TextView) findViewById(R.id.relation6TextView);
        relation7TextView = (TextView) findViewById(R.id.relation7TextView);
        relation8TextView = (TextView) findViewById(R.id.relation8TextView);
        relation9TextView = (TextView) findViewById(R.id.relation9TextView);
        relation10TextView = (TextView) findViewById(R.id.relation10TextView);

        starViewImage1 = (ImageView) findViewById(R.id.starViewImage1);
        starViewImage2 = (ImageView) findViewById(R.id.starViewImage2);
        starViewImage3 = (ImageView) findViewById(R.id.starViewImage3);
        starViewImage4 = (ImageView) findViewById(R.id.starViewImage4);
        starViewImage5 = (ImageView) findViewById(R.id.starViewImage5);
        roleViewImage = (ImageView) findViewById(R.id.roleViewImage);
        elementViewImage = (ImageView) findViewById(R.id.elementViewImage);
        zodiacViewImage = (ImageView) findViewById(R.id.zodiacViewImage);

        relation1View = (ImageView) findViewById(R.id.relation1View);
        relation2View = (ImageView) findViewById(R.id.relation2View);
        relation3View = (ImageView) findViewById(R.id.relation3View);
        relation4View = (ImageView) findViewById(R.id.relation4View);
        relation5View = (ImageView) findViewById(R.id.relation5View);
        relation6View = (ImageView) findViewById(R.id.relation6View);
        relation7View = (ImageView) findViewById(R.id.relation7View);
        relation8View = (ImageView) findViewById(R.id.relation8View);
        relation9View = (ImageView) findViewById(R.id.relation9View);
        relation10View = (ImageView) findViewById(R.id.relation10View);

        DisplayWallpaper();
        DisplayLayoutView();
        DisplayHeroName(currentHero);
        DisplayRarity(currentHero);
        DisplayRole(currentHero);
        DisplayElement(currentHero);
        DisplayZodiac(currentHero);
        DisplayImageUrl();
        DisplayImageFullUrl();
        DisplayStory(currentHeroInfo);
        DisplayGetLineView(currentHeroInfo);
        DisplayRelationship();
        DisplayRelationshipInformation(currentHeroInfo);
    }

    @SuppressLint("SetTextI18n")
    private void DisplayRelationshipInformation(HeroInfo currentHeroInfo) {
        if (totalRelations > 0) {
            switch (totalRelations) {
                default:
                    break;
                case 1:
                    relation1TextView.setText("[" + HeroInfoRelation1.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(0).getDescription());
                    break;
                case 2:
                    relation1TextView.setText("[" + HeroInfoRelation1.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(0).getDescription());
                    relation2TextView.setText("[" + HeroInfoRelation2.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(1).getDescription());
                    break;
                case 3:
                    relation1TextView.setText("[" + HeroInfoRelation1.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(0).getDescription());
                    relation2TextView.setText("[" + HeroInfoRelation2.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(1).getDescription());
                    relation3TextView.setText("[" + HeroInfoRelation3.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(2).getDescription());
                    break;
                case 4:
                    relation1TextView.setText("[" + HeroInfoRelation1.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(0).getDescription());
                    relation2TextView.setText("[" + HeroInfoRelation2.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(1).getDescription());
                    relation3TextView.setText("[" + HeroInfoRelation3.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(2).getDescription());
                    relation4TextView.setText("[" + HeroInfoRelation4.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(3).getDescription());
                    break;
                case 5:
                    relation1TextView.setText("[" + HeroInfoRelation1.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(0).getDescription());
                    relation2TextView.setText("[" + HeroInfoRelation2.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(1).getDescription());
                    relation3TextView.setText("[" + HeroInfoRelation3.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(2).getDescription());
                    relation4TextView.setText("[" + HeroInfoRelation4.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(3).getDescription());
                    relation5TextView.setText("[" + HeroInfoRelation5.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(4).getDescription());
                    break;
                case 6:
                    relation1TextView.setText("[" + HeroInfoRelation1.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(0).getDescription());
                    relation2TextView.setText("[" + HeroInfoRelation2.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(1).getDescription());
                    relation3TextView.setText("[" + HeroInfoRelation3.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(2).getDescription());
                    relation4TextView.setText("[" + HeroInfoRelation4.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(3).getDescription());
                    relation5TextView.setText("[" + HeroInfoRelation5.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(4).getDescription());
                    relation6TextView.setText("[" + HeroInfoRelation6.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(5).getDescription());
                    break;
                case 7:
                    relation1TextView.setText("[" + HeroInfoRelation1.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(0).getDescription());
                    relation2TextView.setText("[" + HeroInfoRelation2.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(1).getDescription());
                    relation3TextView.setText("[" + HeroInfoRelation3.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(2).getDescription());
                    relation4TextView.setText("[" + HeroInfoRelation4.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(3).getDescription());
                    relation5TextView.setText("[" + HeroInfoRelation5.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(4).getDescription());
                    relation6TextView.setText("[" + HeroInfoRelation6.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(5).getDescription());
                    relation7TextView.setText("[" + HeroInfoRelation7.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(6).getDescription());
                    break;
                case 8:
                    relation1TextView.setText("[" + HeroInfoRelation1.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(0).getDescription());
                    relation2TextView.setText("[" + HeroInfoRelation2.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(1).getDescription());
                    relation3TextView.setText("[" + HeroInfoRelation3.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(2).getDescription());
                    relation4TextView.setText("[" + HeroInfoRelation4.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(3).getDescription());
                    relation5TextView.setText("[" + HeroInfoRelation5.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(4).getDescription());
                    relation6TextView.setText("[" + HeroInfoRelation6.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(5).getDescription());
                    relation7TextView.setText("[" + HeroInfoRelation7.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(6).getDescription());
                    relation8TextView.setText("[" + HeroInfoRelation8.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(7).getDescription());
                    break;
                case 9:
                    relation1TextView.setText("[" + HeroInfoRelation1.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(0).getDescription());
                    relation2TextView.setText("[" + HeroInfoRelation2.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(1).getDescription());
                    relation3TextView.setText("[" + HeroInfoRelation3.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(2).getDescription());
                    relation4TextView.setText("[" + HeroInfoRelation4.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(3).getDescription());
                    relation5TextView.setText("[" + HeroInfoRelation5.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(4).getDescription());
                    relation6TextView.setText("[" + HeroInfoRelation6.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(5).getDescription());
                    relation7TextView.setText("[" + HeroInfoRelation7.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(6).getDescription());
                    relation8TextView.setText("[" + HeroInfoRelation8.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(7).getDescription());
                    relation9TextView.setText("[" + HeroInfoRelation9.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(8).getDescription());
                    break;
                case 10:
                    relation1TextView.setText("[" + HeroInfoRelation1.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(0).getDescription());
                    relation2TextView.setText("[" + HeroInfoRelation2.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(1).getDescription());
                    relation3TextView.setText("[" + HeroInfoRelation3.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(2).getDescription());
                    relation4TextView.setText("[" + HeroInfoRelation4.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(3).getDescription());
                    relation5TextView.setText("[" + HeroInfoRelation5.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(4).getDescription());
                    relation6TextView.setText("[" + HeroInfoRelation6.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(5).getDescription());
                    relation7TextView.setText("[" + HeroInfoRelation7.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(6).getDescription());
                    relation8TextView.setText("[" + HeroInfoRelation8.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(7).getDescription());
                    relation9TextView.setText("[" + HeroInfoRelation9.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(8).getDescription());
                    relation10TextView.setText("[" + HeroInfoRelation10.getName() + "]" + "\n" + currentHeroInfo.getRelationships().get(0).getRelations().get(9).getDescription());
                    break;
            }
        }
    }

    private void DisplayRelationship() {
        if (totalRelations > 0) {
            switch (totalRelations) {
                default:
                    break;
                case 1:
                    Picasso.get().load(HeroInfoRelation1.getAssets().getIcon()).into(relation1View);
                    relation2View.setVisibility(View.GONE);
                    relation2TextView.setVisibility(View.GONE);
                    relation3View.setVisibility(View.GONE);
                    relation3TextView.setVisibility(View.GONE);
                    relation4View.setVisibility(View.GONE);
                    relation4TextView.setVisibility(View.GONE);
                    relation5View.setVisibility(View.GONE);
                    relation5TextView.setVisibility(View.GONE);
                    relation6View.setVisibility(View.GONE);
                    relation6TextView.setVisibility(View.GONE);
                    relation7View.setVisibility(View.GONE);
                    relation7TextView.setVisibility(View.GONE);
                    relation8View.setVisibility(View.GONE);
                    relation8TextView.setVisibility(View.GONE);
                    relation9View.setVisibility(View.GONE);
                    relation9TextView.setVisibility(View.GONE);
                    relation10View.setVisibility(View.GONE);
                    relation10TextView.setVisibility(View.GONE);
                    break;
                case 2:
                    Picasso.get().load(HeroInfoRelation1.getAssets().getIcon()).into(relation1View);
                    Picasso.get().load(HeroInfoRelation2.getAssets().getIcon()).into(relation2View);
                    relation3View.setVisibility(View.GONE);
                    relation3TextView.setVisibility(View.GONE);
                    relation4View.setVisibility(View.GONE);
                    relation4TextView.setVisibility(View.GONE);
                    relation5View.setVisibility(View.GONE);
                    relation5TextView.setVisibility(View.GONE);
                    relation6View.setVisibility(View.GONE);
                    relation6TextView.setVisibility(View.GONE);
                    relation7View.setVisibility(View.GONE);
                    relation7TextView.setVisibility(View.GONE);
                    relation8View.setVisibility(View.GONE);
                    relation8TextView.setVisibility(View.GONE);
                    relation9View.setVisibility(View.GONE);
                    relation9TextView.setVisibility(View.GONE);
                    relation10View.setVisibility(View.GONE);
                    relation10TextView.setVisibility(View.GONE);
                    break;
                case 3:
                    Picasso.get().load(HeroInfoRelation1.getAssets().getIcon()).into(relation1View);
                    Picasso.get().load(HeroInfoRelation2.getAssets().getIcon()).into(relation2View);
                    Picasso.get().load(HeroInfoRelation3.getAssets().getIcon()).into(relation3View);
                    relation4View.setVisibility(View.GONE);
                    relation4TextView.setVisibility(View.GONE);
                    relation5View.setVisibility(View.GONE);
                    relation5TextView.setVisibility(View.GONE);
                    relation6View.setVisibility(View.GONE);
                    relation6TextView.setVisibility(View.GONE);
                    relation7View.setVisibility(View.GONE);
                    relation7TextView.setVisibility(View.GONE);
                    relation8View.setVisibility(View.GONE);
                    relation8TextView.setVisibility(View.GONE);
                    relation9View.setVisibility(View.GONE);
                    relation9TextView.setVisibility(View.GONE);
                    relation10View.setVisibility(View.GONE);
                    relation10TextView.setVisibility(View.GONE);
                    break;
                case 4:
                    Picasso.get().load(HeroInfoRelation1.getAssets().getIcon()).into(relation1View);
                    Picasso.get().load(HeroInfoRelation2.getAssets().getIcon()).into(relation2View);
                    Picasso.get().load(HeroInfoRelation3.getAssets().getIcon()).into(relation3View);
                    Picasso.get().load(HeroInfoRelation4.getAssets().getIcon()).into(relation4View);
                    relation5View.setVisibility(View.GONE);
                    relation5TextView.setVisibility(View.GONE);
                    relation6View.setVisibility(View.GONE);
                    relation6TextView.setVisibility(View.GONE);
                    relation7View.setVisibility(View.GONE);
                    relation7TextView.setVisibility(View.GONE);
                    relation8View.setVisibility(View.GONE);
                    relation8TextView.setVisibility(View.GONE);
                    relation9View.setVisibility(View.GONE);
                    relation9TextView.setVisibility(View.GONE);
                    relation10View.setVisibility(View.GONE);
                    relation10TextView.setVisibility(View.GONE);
                    break;
                case 5:
                    Picasso.get().load(HeroInfoRelation1.getAssets().getIcon()).into(relation1View);
                    Picasso.get().load(HeroInfoRelation2.getAssets().getIcon()).into(relation2View);
                    Picasso.get().load(HeroInfoRelation3.getAssets().getIcon()).into(relation3View);
                    Picasso.get().load(HeroInfoRelation4.getAssets().getIcon()).into(relation4View);
                    Picasso.get().load(HeroInfoRelation5.getAssets().getIcon()).into(relation5View);
                    relation6View.setVisibility(View.GONE);
                    relation6TextView.setVisibility(View.GONE);
                    relation7View.setVisibility(View.GONE);
                    relation7TextView.setVisibility(View.GONE);
                    relation8View.setVisibility(View.GONE);
                    relation8TextView.setVisibility(View.GONE);
                    relation9View.setVisibility(View.GONE);
                    relation9TextView.setVisibility(View.GONE);
                    relation10View.setVisibility(View.GONE);
                    relation10TextView.setVisibility(View.GONE);
                    break;
                case 6:
                    Picasso.get().load(HeroInfoRelation1.getAssets().getIcon()).into(relation1View);
                    Picasso.get().load(HeroInfoRelation2.getAssets().getIcon()).into(relation2View);
                    Picasso.get().load(HeroInfoRelation3.getAssets().getIcon()).into(relation3View);
                    Picasso.get().load(HeroInfoRelation4.getAssets().getIcon()).into(relation4View);
                    Picasso.get().load(HeroInfoRelation5.getAssets().getIcon()).into(relation5View);
                    Picasso.get().load(HeroInfoRelation6.getAssets().getIcon()).into(relation6View);
                    relation7View.setVisibility(View.GONE);
                    relation7TextView.setVisibility(View.GONE);
                    relation8View.setVisibility(View.GONE);
                    relation8TextView.setVisibility(View.GONE);
                    relation9View.setVisibility(View.GONE);
                    relation9TextView.setVisibility(View.GONE);
                    relation10View.setVisibility(View.GONE);
                    relation10TextView.setVisibility(View.GONE);
                    break;
                case 7:
                    Picasso.get().load(HeroInfoRelation1.getAssets().getIcon()).into(relation1View);
                    Picasso.get().load(HeroInfoRelation2.getAssets().getIcon()).into(relation2View);
                    Picasso.get().load(HeroInfoRelation3.getAssets().getIcon()).into(relation3View);
                    Picasso.get().load(HeroInfoRelation4.getAssets().getIcon()).into(relation4View);
                    Picasso.get().load(HeroInfoRelation5.getAssets().getIcon()).into(relation5View);
                    Picasso.get().load(HeroInfoRelation6.getAssets().getIcon()).into(relation6View);
                    Picasso.get().load(HeroInfoRelation7.getAssets().getIcon()).into(relation7View);
                    relation8View.setVisibility(View.GONE);
                    relation8TextView.setVisibility(View.GONE);
                    relation9View.setVisibility(View.GONE);
                    relation9TextView.setVisibility(View.GONE);
                    relation10View.setVisibility(View.GONE);
                    relation10TextView.setVisibility(View.GONE);
                    break;
                case 8:
                    Picasso.get().load(HeroInfoRelation1.getAssets().getIcon()).into(relation1View);
                    Picasso.get().load(HeroInfoRelation2.getAssets().getIcon()).into(relation2View);
                    Picasso.get().load(HeroInfoRelation3.getAssets().getIcon()).into(relation3View);
                    Picasso.get().load(HeroInfoRelation4.getAssets().getIcon()).into(relation4View);
                    Picasso.get().load(HeroInfoRelation5.getAssets().getIcon()).into(relation5View);
                    Picasso.get().load(HeroInfoRelation6.getAssets().getIcon()).into(relation6View);
                    Picasso.get().load(HeroInfoRelation7.getAssets().getIcon()).into(relation7View);
                    Picasso.get().load(HeroInfoRelation8.getAssets().getIcon()).into(relation8View);
                    relation9View.setVisibility(View.GONE);
                    relation9TextView.setVisibility(View.GONE);
                    relation10View.setVisibility(View.GONE);
                    relation10TextView.setVisibility(View.GONE);
                    break;
                case 9:
                    Picasso.get().load(HeroInfoRelation1.getAssets().getIcon()).into(relation1View);
                    Picasso.get().load(HeroInfoRelation2.getAssets().getIcon()).into(relation2View);
                    Picasso.get().load(HeroInfoRelation3.getAssets().getIcon()).into(relation3View);
                    Picasso.get().load(HeroInfoRelation4.getAssets().getIcon()).into(relation4View);
                    Picasso.get().load(HeroInfoRelation5.getAssets().getIcon()).into(relation5View);
                    Picasso.get().load(HeroInfoRelation6.getAssets().getIcon()).into(relation6View);
                    Picasso.get().load(HeroInfoRelation7.getAssets().getIcon()).into(relation7View);
                    Picasso.get().load(HeroInfoRelation8.getAssets().getIcon()).into(relation8View);
                    Picasso.get().load(HeroInfoRelation9.getAssets().getIcon()).into(relation9View);
                    relation10View.setVisibility(View.GONE);
                    relation10TextView.setVisibility(View.GONE);
                    break;
                case 10:
                    Picasso.get().load(HeroInfoRelation1.getAssets().getIcon()).into(relation1View);
                    Picasso.get().load(HeroInfoRelation2.getAssets().getIcon()).into(relation2View);
                    Picasso.get().load(HeroInfoRelation3.getAssets().getIcon()).into(relation3View);
                    Picasso.get().load(HeroInfoRelation4.getAssets().getIcon()).into(relation4View);
                    Picasso.get().load(HeroInfoRelation5.getAssets().getIcon()).into(relation5View);
                    Picasso.get().load(HeroInfoRelation6.getAssets().getIcon()).into(relation6View);
                    Picasso.get().load(HeroInfoRelation7.getAssets().getIcon()).into(relation7View);
                    Picasso.get().load(HeroInfoRelation8.getAssets().getIcon()).into(relation8View);
                    Picasso.get().load(HeroInfoRelation9.getAssets().getIcon()).into(relation9View);
                    Picasso.get().load(HeroInfoRelation10.getAssets().getIcon()).into(relation10View);
                    break;
            }
        }
    }

    private void DisplayImageUrl() {
        Picasso.get().load(imageUrl).into(imageViewImage);
    }

    private void DisplayImageFullUrl() {
        Picasso.get().load(imageFullUrl).into(imageFullUrlView);
    }

    private void DisplayWallpaper() {
        Picasso.get().load(wallpaper).into(wallpaperView);
    }

    private void DisplayLayoutView() {
        Picasso.get().load(layout).into(layoutView);
    }

    @SuppressLint("SetTextI18n")
    private void DisplayStory(HeroInfo currentHeroInfo) {
        storyView.setText("\"" + currentHeroInfo.getStory() + "\"");
    }

    private void DisplayGetLineView(HeroInfo currentHeroInfo) {
        get_lineView.setText(currentHeroInfo.getGet_line());
    }

    private void DisplayHeroName(Hero currentHero) {
        textViewInf.setText(currentHero.getName());
    }

    private void DisplayZodiac(Hero currentHero) {
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

    private void DisplayElement(Hero currentHero) {
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

    public void DisplayRarity(Hero currentHero) {
        if (currentHero.getRarity() == 1) {
            starViewImage5.setImageResource(R.drawable.cm_icon_star);
            starViewImage4.setImageResource(0);
            starViewImage3.setImageResource(0);
            starViewImage2.setImageResource(0);
            starViewImage1.setImageResource(0);
        } else if (currentHero.getRarity() == 2) {
            starViewImage5.setImageResource(R.drawable.cm_icon_star);
            starViewImage4.setImageResource(R.drawable.cm_icon_star);
            starViewImage3.setImageResource(0);
            starViewImage2.setImageResource(0);
            starViewImage1.setImageResource(0);
        } else if (currentHero.getRarity() == 3) {
            starViewImage5.setImageResource(R.drawable.cm_icon_star);
            starViewImage4.setImageResource(R.drawable.cm_icon_star);
            starViewImage3.setImageResource(R.drawable.cm_icon_star);
            starViewImage2.setImageResource(0);
            starViewImage1.setImageResource(0);
        } else if (currentHero.getRarity() == 4) {
            starViewImage5.setImageResource(R.drawable.cm_icon_star);
            starViewImage4.setImageResource(R.drawable.cm_icon_star);
            starViewImage3.setImageResource(R.drawable.cm_icon_star);
            starViewImage2.setImageResource(R.drawable.cm_icon_star);
            starViewImage1.setImageResource(0);
        } else if (currentHero.getRarity() == 5) {
            starViewImage5.setImageResource(R.drawable.cm_icon_star);
            starViewImage4.setImageResource(R.drawable.cm_icon_star);
            starViewImage3.setImageResource(R.drawable.cm_icon_star);
            starViewImage2.setImageResource(R.drawable.cm_icon_star);
            starViewImage1.setImageResource(R.drawable.cm_icon_star);
        }
    }

    private void DisplayRole(Hero currentHero) {
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

    private void getHeroInfoRelations(Intent intent) {
        if (totalRelations > 0) {
            switch (totalRelations) {
                default:
                    break;
                case 1:
                    this.HeroInfoRelation1 = intent.getParcelableExtra("HeroInfo1");
                    break;
                case 2:
                    this.HeroInfoRelation1 = intent.getParcelableExtra("HeroInfo1");
                    this.HeroInfoRelation2 = intent.getParcelableExtra("HeroInfo2");
                    break;
                case 3:
                    this.HeroInfoRelation1 = intent.getParcelableExtra("HeroInfo1");
                    this.HeroInfoRelation2 = intent.getParcelableExtra("HeroInfo2");
                    this.HeroInfoRelation3 = intent.getParcelableExtra("HeroInfo3");
                    break;
                case 4:
                    this.HeroInfoRelation1 = intent.getParcelableExtra("HeroInfo1");
                    this.HeroInfoRelation2 = intent.getParcelableExtra("HeroInfo2");
                    this.HeroInfoRelation3 = intent.getParcelableExtra("HeroInfo3");
                    this.HeroInfoRelation4 = intent.getParcelableExtra("HeroInfo4");
                    break;
                case 5:
                    this.HeroInfoRelation1 = intent.getParcelableExtra("HeroInfo1");
                    this.HeroInfoRelation2 = intent.getParcelableExtra("HeroInfo2");
                    this.HeroInfoRelation3 = intent.getParcelableExtra("HeroInfo3");
                    this.HeroInfoRelation4 = intent.getParcelableExtra("HeroInfo4");
                    this.HeroInfoRelation5 = intent.getParcelableExtra("HeroInfo5");
                    break;
                case 6:
                    this.HeroInfoRelation1 = intent.getParcelableExtra("HeroInfo1");
                    this.HeroInfoRelation2 = intent.getParcelableExtra("HeroInfo2");
                    this.HeroInfoRelation3 = intent.getParcelableExtra("HeroInfo3");
                    this.HeroInfoRelation4 = intent.getParcelableExtra("HeroInfo4");
                    this.HeroInfoRelation5 = intent.getParcelableExtra("HeroInfo5");
                    this.HeroInfoRelation6 = intent.getParcelableExtra("HeroInfo6");
                    break;
                case 7:
                    this.HeroInfoRelation1 = intent.getParcelableExtra("HeroInfo1");
                    this.HeroInfoRelation2 = intent.getParcelableExtra("HeroInfo2");
                    this.HeroInfoRelation3 = intent.getParcelableExtra("HeroInfo3");
                    this.HeroInfoRelation4 = intent.getParcelableExtra("HeroInfo4");
                    this.HeroInfoRelation5 = intent.getParcelableExtra("HeroInfo5");
                    this.HeroInfoRelation6 = intent.getParcelableExtra("HeroInfo6");
                    this.HeroInfoRelation7 = intent.getParcelableExtra("HeroInfo7");
                    break;
                case 8:
                    this.HeroInfoRelation1 = intent.getParcelableExtra("HeroInfo1");
                    this.HeroInfoRelation2 = intent.getParcelableExtra("HeroInfo2");
                    this.HeroInfoRelation3 = intent.getParcelableExtra("HeroInfo3");
                    this.HeroInfoRelation4 = intent.getParcelableExtra("HeroInfo4");
                    this.HeroInfoRelation5 = intent.getParcelableExtra("HeroInfo5");
                    this.HeroInfoRelation6 = intent.getParcelableExtra("HeroInfo6");
                    this.HeroInfoRelation7 = intent.getParcelableExtra("HeroInfo7");
                    this.HeroInfoRelation8 = intent.getParcelableExtra("HeroInfo8");
                    break;
                case 9:
                    this.HeroInfoRelation1 = intent.getParcelableExtra("HeroInfo1");
                    this.HeroInfoRelation2 = intent.getParcelableExtra("HeroInfo2");
                    this.HeroInfoRelation3 = intent.getParcelableExtra("HeroInfo3");
                    this.HeroInfoRelation4 = intent.getParcelableExtra("HeroInfo4");
                    this.HeroInfoRelation5 = intent.getParcelableExtra("HeroInfo5");
                    this.HeroInfoRelation6 = intent.getParcelableExtra("HeroInfo6");
                    this.HeroInfoRelation7 = intent.getParcelableExtra("HeroInfo7");
                    this.HeroInfoRelation8 = intent.getParcelableExtra("HeroInfo8");
                    this.HeroInfoRelation9 = intent.getParcelableExtra("HeroInfo9");
                    break;
                case 10:
                    this.HeroInfoRelation1 = intent.getParcelableExtra("HeroInfo1");
                    this.HeroInfoRelation2 = intent.getParcelableExtra("HeroInfo2");
                    this.HeroInfoRelation3 = intent.getParcelableExtra("HeroInfo3");
                    this.HeroInfoRelation4 = intent.getParcelableExtra("HeroInfo4");
                    this.HeroInfoRelation5 = intent.getParcelableExtra("HeroInfo5");
                    this.HeroInfoRelation6 = intent.getParcelableExtra("HeroInfo6");
                    this.HeroInfoRelation7 = intent.getParcelableExtra("HeroInfo7");
                    this.HeroInfoRelation8 = intent.getParcelableExtra("HeroInfo8");
                    this.HeroInfoRelation9 = intent.getParcelableExtra("HeroInfo9");
                    this.HeroInfoRelation10 = intent.getParcelableExtra("HeroInfo10");
                    break;
            }
        }
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
