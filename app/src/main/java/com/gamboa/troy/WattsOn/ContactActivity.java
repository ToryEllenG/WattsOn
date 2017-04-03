package com.gamboa.troy.WattsOn;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

public class ContactActivity extends AppCompatActivity {

    Toolbar contactToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Contact Us");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        //custom Contact toolbar
        contactToolBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(contactToolBar);
        getSupportActionBar().setTitle("Contact Information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        contactToolBar.setTitleTextColor(Color.WHITE);

        ImageButton instagram = (ImageButton)findViewById(R.id.imageView2);
        ImageButton twitter = (ImageButton)findViewById(R.id.imageView3);
        ImageButton facebook = (ImageButton)findViewById(R.id.imageView4);

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/Watts_On306/?hl=en"));
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

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/WattsOn306"));
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
