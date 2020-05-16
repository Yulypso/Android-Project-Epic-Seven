package com.github.androidproject.presentation.ui.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.github.androidproject.R;
import com.github.androidproject.presentation.ui.models.Hero;

public class PopUp extends Activity {

    Intent intent;
    Hero currentHero;

    TextView popUpView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popupwindow);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width*.8), (int) (height*.2));

        intent = getIntent();
        currentHero = intent.getParcelableExtra("currentHeroMissing");
        popUpView = findViewById(R.id.popUpView);
        DisplayMessage(currentHero);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @SuppressLint("SetTextI18n")
    public void DisplayMessage(Hero currentHero){
        popUpView.setText("We are Sorry !\n"+currentHero.getName()+"'s\ninformation is not available !");
    }
}
