package com.gamboa.troy.WattsOn;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class HouseRegisterActivity extends AppCompatActivity {

    EditText ETaddress, ETcity, ETzip;
    Spinner SPstate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_register);

        ETaddress = (EditText) findViewById(R.id.ETaddress);
        ETcity = (EditText) findViewById(R.id.ETcity);
        SPstate = (Spinner) findViewById(R.id.SPstate);
        ETzip = (EditText) findViewById(R.id.ETzip);

        final Button BTHouseRegister = (Button) findViewById(R.id.BTHouseRegister);


        BTHouseRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String street_address = ETaddress.getText().toString();
                final String city = ETcity.getText().toString();
                final String state = SPstate.getSelectedItem().toString(); //Come back to this
                final String zipcode = ETzip.getText().toString();


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
                                Intent openMain = new Intent(HouseRegisterActivity.this, MainActivity.class);
                                Toast registerSuccess = Toast.makeText(HouseRegisterActivity.this, "House Data Registered!", Toast.LENGTH_LONG);
                                registerSuccess.show();
                                startActivity(openMain);
                            }
                            //If no connection can be made to db
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(HouseRegisterActivity.this);
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

                    //if all fields succeed, call new volley queue and register to the database
                   if(!street_address.isEmpty() || !city.isEmpty() || !zipcode.isEmpty()) {
                       HouseRegisterRequest registerRequest = new HouseRegisterRequest(street_address, city, state, zipcode, responseListener);
                       RequestQueue houseQueue = Volley.newRequestQueue(HouseRegisterActivity.this);
                       houseQueue.add(registerRequest);
                   }
                    else {
                       Toast.makeText(getApplicationContext(),"Please Enter Your Information!", Toast.LENGTH_SHORT).show();
                   }

                   }



            });

        }

    @Override
    protected void onResume() {
        super.onResume();
        ETaddress.setText("");
        ETcity.setText("");
        ETzip.setText("");
    }
}

