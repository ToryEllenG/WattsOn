package com.gamboa.troy.WattsOn;

import android.content.Intent;
import android.content.SharedPreferences;
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


public class EditUserData extends AppCompatActivity {

    EditText changeFname, changeLname, changeEmail, changePhone;
    TextView usernameView;
    Toolbar editToolbar;
    Button changeDataBT;
    public static final String PREFS_NAME = "MyPrefsFile";

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

        usernameView = (TextView) findViewById(R.id.changeUsername);
        changeFname = (EditText)findViewById(R.id.changeFname);
        changeLname  = (EditText)findViewById(R.id.changeLname);
        changeEmail = (EditText)findViewById(R.id.changeEmail);
        changePhone  = (EditText)findViewById(R.id.changePhone);

        changeDataBT = (Button)findViewById(R.id.changeInfo);

        usernameView.setText(getIntent().getExtras().getString("username"));

        //set the hint to the specific fields of the logged in user
        changeFname.setHint(getIntent().getExtras().getString("firstName"));
        changeLname.setHint(getIntent().getExtras().getString("lastName"));
        changeEmail.setHint(getIntent().getExtras().getString("email"));
        changePhone.setHint(getIntent().getExtras().getString("phone"));


        changeDataBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = usernameView.getText().toString();
                final String first_name = changeFname.getText().toString();
                final String last_name = changeLname.getText().toString();

                //String to check that email is in correct format with Regular expression
                final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                final String email = changeEmail.getText().toString();
                final String phone_number = changePhone.getText().toString();

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
                                Toast registerSuccess = Toast.makeText(EditUserData.this, "Data Successfully Changed!", Toast.LENGTH_LONG);
                                registerSuccess.show();

                                SharedPreferences newData = getSharedPreferences(PREFS_NAME, 0);
                                SharedPreferences.Editor editor = newData.edit();
                                editor.putString("usernameChange", username);
                                editor.apply();

                                finish(); //go back to previous activity
                            }
                            //If no connection can be made to db
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(EditUserData.this);
                                builder.setMessage("Update Failed!")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                if(first_name.isEmpty() && last_name.isEmpty() && email.isEmpty() && phone_number.isEmpty()) {
                    Toast.makeText(EditUserData.this, "Please Enter your information!", Toast.LENGTH_LONG).show();
                }

                //check email pattern
                else if(!email.matches(emailPattern)){
                    Toast.makeText(getApplicationContext()," Please enter Email in the form of 'email@email.com'. ", Toast.LENGTH_LONG).show();
                }

                else {
                    //if all fields succeed, call new volley queue and update to the database
                  EditUserDataRequest editUserDataRequest = new EditUserDataRequest(username, first_name, last_name, email, phone_number, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(EditUserData.this);
                    queue.add(editUserDataRequest);
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
