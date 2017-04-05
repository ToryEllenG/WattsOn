package com.gamboa.troy.WattsOn;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Troygbv on 2/7/17.
 */

public class AboutActivity extends AppCompatActivity {

    Toolbar aboutToolbar;
    ImageButton instagram, facebook, twitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(getString(R.string.aboutApp));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //custom about toolbar
        aboutToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(aboutToolbar);
        getSupportActionBar().setTitle("About this Application");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        aboutToolbar.setTitleTextColor(Color.WHITE);

        //image button actions
        instagram = (ImageButton)findViewById(R.id.instagramBT);
        facebook = (ImageButton) findViewById(R.id.facebookBT);
        twitter = (ImageButton)findViewById(R.id.twitterBT);


        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/Watts_On306/?hl=en"));
                startActivity(browserIntent);
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/WattsOn306"));
                startActivity(browserIntent);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://twitter.com/Watts_On306"));
                startActivity(browserIntent);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
