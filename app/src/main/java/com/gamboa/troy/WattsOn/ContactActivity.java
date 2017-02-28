package com.gamboa.troy.WattsOn;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Contact Us");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

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
}
