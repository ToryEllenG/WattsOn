package com.gamboa.troy.WattsOn;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Troygbv on 2/2/17.
 */

public class RegisterActivity extends AppCompatActivity{

    EditText ETfirstname, ETlastname, ETemail, ETusername, ETpassword, ETconfirm, ETphone_number;
    Button btnSignUp;
    Toolbar registerToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTitle(getString(R.string.accountRegister));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //custom about toolbar
        registerToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(registerToolbar);
        getSupportActionBar().setTitle("Please Register an Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        registerToolbar.setTitleTextColor(Color.WHITE);

        //Text Fields
        ETfirstname = (EditText) findViewById(R.id.ETfirstname);
        ETlastname = (EditText) findViewById(R.id.ETlastname);
        ETemail = (EditText) findViewById(R.id.ETemail);
        ETusername = (EditText) findViewById(R.id.ETusername);
        ETpassword = (EditText) findViewById(R.id.ETpassword);
        ETconfirm = (EditText) findViewById(R.id.ETconfirm);
        ETphone_number = (EditText) findViewById(R.id.ETphonenumber);
        //Button
        btnSignUp = (Button)findViewById(R.id.BTsignup);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    final String first_name = ETfirstname.getText().toString();
                    final String last_name = ETlastname.getText().toString();
                    final String email = ETemail.getText().toString().trim();

                    //String to check that email is in correct format with Regular expression
                    final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                    final String username = ETusername.getText().toString();
                    final String password = ETpassword.getText().toString();
                    final String confirm = ETconfirm.getText().toString();
                    final String phone_number = ETphone_number.getText().toString();

                    //Initiate Listener
                    Response.Listener<String> responseListener = new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            try {
                                //Call json "response" as shown in PHP API
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                //Open Login Activity & attempt to verify password and confirm
                                if (success) {
                                    Intent openLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                                    Toast registerSuccess = Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_LONG);
                                    registerSuccess.show();
                                    startActivity(openLogin);
                                }
                                //If no connection can be made to db
                                else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                    builder.setMessage("Register Failed!")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    //call RegisterRequest class created in RegisterRequest.java and validate empty fields, email format, and confirm password
                if(first_name.isEmpty() || last_name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() || confirm.isEmpty() || phone_number.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please Enter your information!", Toast.LENGTH_LONG).show();
                    }

                else if(!email.matches(emailPattern)){
                   Toast.makeText(getApplicationContext()," Please enter Email in the form of 'email@email.com'. ", Toast.LENGTH_LONG).show();
                 }
                else if(!confirm.equals(password)){
                    Toast.makeText(getApplicationContext()," Passwords do not match! ", Toast.LENGTH_SHORT).show();
                }
               // else if(PhoneNumberUtils.isGlobalPhoneNumber(phone_number)) {
              //      Toast.makeText(getApplicationContext()," Please enter Phone Number in the form of ###-###-####", Toast.LENGTH_LONG).show();

             //   }
                else {
                    //if all fields succeed, call new volley queue and register to the database
                    RegisterRequest registerRequest = new RegisterRequest(first_name, last_name, email, username, password, phone_number, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(registerRequest);
                }


                }

        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        ETfirstname.setText("");
        ETlastname.setText("");
        ETemail.setText("");
        ETusername.setText("");
        ETpassword.setText("");
        ETconfirm.setText("");
        ETphone_number.setText("");

    }
}
