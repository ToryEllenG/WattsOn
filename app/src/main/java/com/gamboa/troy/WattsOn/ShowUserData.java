package com.gamboa.troy.WattsOn;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

public class ShowUserData extends AppCompatActivity {

    Toolbar profileToolbar;
    TextView usernameView, firstNameView, lastNameView, emailView, phoneView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_data);

        //custom about toolbar
        profileToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(profileToolbar);
        getSupportActionBar().setTitle("User Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        profileToolbar.setTitleTextColor(Color.WHITE);

        usernameView = (TextView) findViewById(R.id.unameResponseTV);
        firstNameView = (TextView) findViewById(R.id.fnameResponseTV);
        lastNameView = (TextView) findViewById(R.id.lnameResponseTV);
        emailView = (TextView) findViewById(R.id.emailResponseTV);
        phoneView = (TextView) findViewById(R.id.phoneResponseTV);

        String username =  getIntent().getExtras().getString("username");

           Response.Listener<String> responseListener = new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {

                   //Call JSON "response" as shown in PHP API
                   try {
                       JSONObject jsonResponse = new JSONObject(response);

                       //pull user data based on the logged in user.
                       String username = jsonResponse.getString("username");
                       String firstName = jsonResponse.getString("firstName");
                       String lastName = jsonResponse.getString("lastName");
                       String email = jsonResponse.getString("email");
                       String phone = jsonResponse.getString("phone");

                       //set TextView fields to the logged in user's information
                       usernameView.setText(username);
                       firstNameView.setText(firstName);
                       lastNameView.setText(lastName);
                       emailView.setText(email);
                       phoneView.setText(phone);

                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
               }
           };

           UserDataRequest userDataRequest = new UserDataRequest(username,responseListener);
           RequestQueue queue = Volley.newRequestQueue(ShowUserData.this);
           queue.add(userDataRequest);

}

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}