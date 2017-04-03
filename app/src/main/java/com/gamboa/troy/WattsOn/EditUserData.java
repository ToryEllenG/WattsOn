package com.gamboa.troy.WattsOn;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;


public class EditUserData extends AppCompatActivity {

    EditText changeUsername, changeFname, changeLname, changeEmail, changePhone;
    Toolbar editToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_data);

        //custom toolbar
        editToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(editToolbar);
        getSupportActionBar().setTitle("Update Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editToolbar.setTitleTextColor(Color.WHITE);

        changeUsername = (EditText)findViewById(R.id.changeUsername);
        changeFname = (EditText)findViewById(R.id.changeFname);
        changeLname  = (EditText)findViewById(R.id.changeLname);
        changeEmail = (EditText)findViewById(R.id.changeEmail);
        changePhone  = (EditText)findViewById(R.id.changePhone);

        //set the hint to the specific fields of the logged in user
        changeUsername.setHint(getIntent().getExtras().getString("username"));
        changeFname.setHint(getIntent().getExtras().getString("firstName"));
        changeLname.setHint(getIntent().getExtras().getString("lastName"));
        changeEmail.setHint(getIntent().getExtras().getString("email"));
        changePhone.setHint(getIntent().getExtras().getString("phone"));


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
