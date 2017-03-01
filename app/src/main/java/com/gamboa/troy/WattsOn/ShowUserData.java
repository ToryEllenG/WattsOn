package com.gamboa.troy.WattsOn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowUserData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_data);
//fix this whole activity
        TextView usernameTV = (TextView)findViewById(R.id.showUnameTV);
        Bundle extras = getIntent().getExtras();
        String username = extras.getString("username");
        usernameTV.setText(username);

        TextView passwordTV = (TextView)findViewById(R.id.showPwordTV);
        String password = extras.getString("username");
        passwordTV.setText(password);
    }
}
