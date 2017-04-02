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
        ImageButton isatButton = (ImageButton)findViewById(R.id.imageView2);

        isatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.jmu.edu/isat/"));
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
