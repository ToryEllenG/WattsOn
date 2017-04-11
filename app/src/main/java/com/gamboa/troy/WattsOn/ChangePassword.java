package com.gamboa.troy.WattsOn;

import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ChangePassword extends AppCompatActivity {

    EditText oldPass, newPass, newPassConfirm;
    Button changePassBtn;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);


        //toolbar
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Change Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);

        oldPass = (EditText)findViewById(R.id.oldPass);
        newPass = (EditText)findViewById(R.id.newPass);
        newPassConfirm = (EditText)findViewById(R.id.newPassConfirm);


        changePassBtn = (Button)findViewById(R.id.changeBT);

        changePassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = getIntent().getExtras().getString("username");
                String password = getIntent().getExtras().getString("password");
                String oldPassword = oldPass.getText().toString();
                String newPassword = newPass.getText().toString();
                String passConfirm = newPassConfirm.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //Call JSON "response" as shown in PHP API
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success){
                                //toast if successful update and move back to user profile
                                Toast loginSuccess = Toast.makeText(ChangePassword.this, "Password Successfully Changed!", Toast.LENGTH_SHORT);
                                loginSuccess.show();
                                finish();
                            }

                            //if there is no connection to db
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ChangePassword.this);
                                builder.setMessage("Incorrect Password!")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                if(oldPassword.isEmpty() || newPassword.isEmpty() || passConfirm.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter your information!", Toast.LENGTH_LONG).show();
                }
                else if(!passConfirm.equals(newPassword)) {
                    Toast.makeText(getApplicationContext()," Passwords do not match! ", Toast.LENGTH_SHORT).show();
                }
                else {
                    ChangePassRequest changePassRequest = new ChangePassRequest(username, oldPassword, password, newPassword, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(ChangePassword.this);
                    queue.add(changePassRequest);
                }
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;

    }
}








