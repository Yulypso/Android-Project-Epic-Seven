package com.github.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ActivityInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        Intent intent = getIntent();
        String text = intent.getStringExtra(ListAdapter.EXTRA_TEXT);


        TextView textViewInf = (TextView) findViewById(R.id.textViewInf);
        textViewInf.setText(text);
    }
}
