package com.gamboa.troy.WattsOn;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class ShowUserData extends AppCompatActivity {

    Toolbar profileToolbar;
    TextView usernameView, firstNameView, lastNameView, emailView, phoneView;

    Button editData, changePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_data);

        //custom toolbar
        profileToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(profileToolbar);
        getSupportActionBar().setTitle("User Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        profileToolbar.setTitleTextColor(Color.WHITE);

        editData = (Button) findViewById(R.id.editBT);
        changePass = (Button) findViewById(R.id.changePassBT);

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
                       final String username = jsonResponse.getString("username");
                       final String firstName = jsonResponse.getString("firstName");
                       final String lastName = jsonResponse.getString("lastName");
                       final String pass = jsonResponse.getString("password");
                       final String email = jsonResponse.getString("email");
                       final String phone = jsonResponse.getString("phone");

                       //set TextView fields to the logged in user's information
                       usernameView.setText(username);
                       firstNameView.setText(firstName);
                       lastNameView.setText(lastName);
                       emailView.setText(email);
                       phoneView.setText(phone);

                       //on button click, send the logged in user's information to the next activity
                       editData.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               Intent openEdit = new Intent(ShowUserData.this, EditUserData.class);
                               Bundle extras = new Bundle();
                               extras.putString("username", username);
                               extras.putString("firstName", firstName);
                               extras.putString("lastName", lastName);
                               extras.putString("email", email);
                               extras.putString("phone", phone);
                               openEdit.putExtras(extras);
                               startActivity(openEdit);
                           }
                       });

                       //intent to change Password activity
                       changePass.setOnClickListener(new View.OnClickListener(){
                           @Override
                           public void onClick(View v) {

                               Intent changePass = new Intent(ShowUserData.this, ChangePassword.class);
                               Bundle extras1 = new Bundle();
                               extras1.putString("username", username);
                               extras1.putString("password", pass);
                               changePass.putExtras(extras1);
                               startActivity(changePass);
                           }
                       });


                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
               }
           };

           ShowUserDataRequest showUserDataRequest = new ShowUserDataRequest(username,responseListener);
           RequestQueue queue = Volley.newRequestQueue(ShowUserData.this);
           queue.add(showUserDataRequest);



}



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}