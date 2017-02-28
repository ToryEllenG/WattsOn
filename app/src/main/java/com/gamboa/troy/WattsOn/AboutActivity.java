package com.gamboa.troy.WattsOn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Troygbv on 2/7/17.
 */

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(getString(R.string.aboutApp));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ImageButton isatButton = (ImageButton)findViewById(R.id.imageView2);

        isatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.jmu.edu/isat/"));
                startActivity(browserIntent);
            }
        });
    }
}
