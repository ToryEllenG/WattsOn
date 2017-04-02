package com.gamboa.troy.WattsOn;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Troygbv on 2/1/17.
 */

public class LoginActivity extends AppCompatActivity{

    EditText etUsername, etPassword;
    Button btnLogin, btnRegister, btnAbout;
    Toolbar loginToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //custom login toolbar
        loginToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(loginToolbar);
        getSupportActionBar().setTitle(getString(R.string.please_login));
        loginToolbar.setTitleTextColor(Color.WHITE);

         etUsername = (EditText) findViewById(R.id.ETusername);
         etPassword = (EditText) findViewById(R.id.ETpassword);
         btnLogin = (Button) findViewById(R.id.BTsubmit);
         btnRegister = (Button) findViewById(R.id.BTregister);
         btnAbout = (Button) findViewById(R.id.btAbout);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(openRegister);
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openAbout = new Intent(LoginActivity.this, AboutActivity.class);
                startActivity(openAbout);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //Call JSON "response" as shown in PHP API
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            //if user is registered, go to Main Activity, and put Extra string "username" for welcome message
                            if (success){
                                String username = jsonResponse.getString("username");
                                Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                                //Bundle to store username
                                Bundle extras = new Bundle();
                                extras.putString("username", username);
                                loginIntent.putExtras(extras);
                                startActivity(loginIntent);
                            }

                            //if there is no connection to db
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Invalid Login Credentials.")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                };

                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        etUsername.setText("");
        etPassword.setText("");
    }

    @Override
    public void onBackPressed() {
    }
}
