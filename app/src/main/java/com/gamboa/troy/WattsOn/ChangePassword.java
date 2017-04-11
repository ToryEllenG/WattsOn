package com.gamboa.troy.WattsOn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ChangePassword extends AppCompatActivity {

    EditText oldPass, newPass, newPassConfirm;
    Button changePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        oldPass = (EditText)findViewById(R.id.oldPass);
        newPass = (EditText)findViewById(R.id.newPass);
        newPassConfirm = (EditText)findViewById(R.id.newPassConfirm);

        changePass = (Button)findViewById(R.id.changeBT);







    }
}
