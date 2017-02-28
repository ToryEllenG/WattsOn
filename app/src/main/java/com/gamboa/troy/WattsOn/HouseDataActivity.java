package com.gamboa.troy.WattsOn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Troygbv on 2/13/17.
 */

public class HouseDataActivity extends AppCompatActivity {

    //Variables for API call
    TextView results;
    String JsonURL = "http://54.152.50.236/HouseData.php";
    String jsonResponse = "";
    //Define Queue
    RequestQueue requestQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTitle(getString(R.string.applianceList));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_data);
        //Create RequestQueue through Volley
        requestQueue = Volley.newRequestQueue(this);
        //Put results in a TextView embedded within a scrollview
        results = (TextView) findViewById(R.id.jsonData);
        //Call JSON Request Method
        fetchHouseData();
    }

    //initiate JSON Request Method
    private void fetchHouseData() {

        JsonArrayRequest req = new JsonArrayRequest(JsonURL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            jsonResponse = "";
                            //loop through each response in the json
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject HouseData = (JSONObject) response.get(i);

                                String houseID = HouseData.getString("house_id");
                                String address = HouseData.getString("street_address");
                                String city = HouseData.getString("city");
                                String state = HouseData.getString("state");
                                String zip = HouseData.getString("zipcode");

                                //provide spacing and call parsed values
                                jsonResponse += "\n";
                                jsonResponse += "House ID: " + houseID + "\n\n";
                                jsonResponse += "address: " + address + "\n\n";
                                jsonResponse += "city: " + city + "\n\n";
                                jsonResponse += "state: " + state + "\n\n";
                                jsonResponse += "zipcode: " + zip   + "\n\n";

                            }
                            // Adds the jsonResponse string to the TextView "results"
                            results.setText(jsonResponse);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");


                    }
                }

        );
        requestQueue.add(req);
    }


}

