package com.github.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ActivityInformation extends AppCompatActivity {

    public static String wallpaper = "https://assets.epicsevendb.com/website/summon/gacha_get_bg0_e7db.jpg";
    public static String layout = "https://assets.epicsevendb.com/website/summon/gacha_get_bg1.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        Intent intent = getIntent();

        ImageView wallpaperView = (ImageView) findViewById(R.id.wallpaperView);
        Picasso.get().load(wallpaper).into(wallpaperView);

        ImageView layoutView = (ImageView) findViewById(R.id.layoutViewImage);
        Picasso.get().load(layout).into(layoutView);

        String name = intent.getStringExtra(ListAdapter.EXTRA_TEXT_NAME);
        TextView textViewInf = (TextView) findViewById(R.id.textViewInf);
        textViewInf.setText(name);

        String imageUrl = intent.getStringExtra(ListAdapter.EXTRA_TEXT_IMAGE);
        ImageView imageViewImage = (ImageView) findViewById(R.id.imageViewImage);
        Picasso.get().load(imageUrl).into(imageViewImage);


    }
}
