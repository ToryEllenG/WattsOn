package com.gamboa.troy.WattsOn;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by troygbv on 2/24/17.
 */

public class FragmentMonitor extends Fragment {

    private Button stats, clear;
    private BarChart mChart;
    RequestQueue requestQueue;
    TextView roomOne, roomTwo, roomThree, roomFour;
    //Json path for all four rooms. Change API to reflect current data
    String JsonURL = "http://54.152.50.236/getEnergy.php";
    String JsonURL2 = "http://54.152.50.236/getEnergy2.php";
    String JsonURL3 = "http://54.152.50.236/getEnergy3.php";
    String JsonURL4 = "http://54.152.50.236/getEnergy4.php";
   public float roomOneNumber, roomTwoNumber, roomThreeNumber, roomFourNumber;

    String jsonResponse, jsonResponse2, jsonResponse3, jsonResponse4;
    BarDataSet Bardataset, Bardataset2, Bardataset3, Bardataset4;
    BarData data;
    public List<BarEntry> barEntry;

    public FragmentMonitor(){
        //required empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monitor, container, false);

        //initiate Chart
        mChart = (BarChart)view.findViewById(R.id.chart);

        //request queue for volley
        requestQueue = Volley.newRequestQueue(getActivity());
        //Set TextView fields to see current usage
        roomOne = (TextView)view.findViewById(R.id.Room1DataTV);
        roomTwo = (TextView)view.findViewById(R.id.Room2DataTV);
        roomThree = (TextView)view.findViewById(R.id.Room3DataTV);
        roomFour = (TextView)view.findViewById(R.id.Room4DataTV);

        //Set Entry for chart
        barEntry = new ArrayList<>();

        //Show current energy value in Text field, convert value to float, and display in chart
        fetchRoomOne();
        fetchRoomTwo();
        fetchRoomThree();
        fetchRoomFour();

        mChart.animateY(3000);
        mChart.getDescription().setEnabled(false);
        mChart.setFitBars(true);
        mChart.notifyDataSetChanged();
        mChart.invalidate();

        //clear button
        clear = (Button) view.findViewById(R.id.clearBT);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call refresh later
                }

        });
        //stats button
        stats = (Button)view.findViewById(R.id.viewStatisticsBT);
        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openStats = new Intent(getActivity(), StatisticsActivity.class);
                startActivity(openStats);
            }
        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

    }

   //custom methods to parse Json based on Room number
    private void fetchRoomOne() {

        JsonArrayRequest req = new JsonArrayRequest(JsonURL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            jsonResponse = "";
                            //loop through each response in the json
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject HouseData = (JSONObject) response.get(i);

                                String kWh = HouseData.getString("kWh");

                                //convert to usable float type
                                roomOneNumber=Float.valueOf(kWh);

                                //provide and call parsed value
                                jsonResponse +=  kWh;
                            }
                            // Adds the jsonResponse string to the TextView "results"
                            roomOne.setText(jsonResponse);

                            //add a new bar entry including the parsed json value.
                            barEntry.add(new BarEntry(1, roomOneNumber));
                            Bardataset = new BarDataSet(barEntry, "Current Energy Consumption");
                            Bardataset.setColors(ColorTemplate.PASTEL_COLORS);
                            data = new BarData(Bardataset);

                            mChart.setData(data);

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity().getApplicationContext(),
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

    private void fetchRoomTwo() {

        JsonArrayRequest req = new JsonArrayRequest(JsonURL2,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            jsonResponse2 = "";
                            //loop through each response in the json
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject HouseData = (JSONObject) response.get(i);

                                String kWh = HouseData.getString("kWh");
                                roomTwoNumber= Float.valueOf(kWh);

                                //provide spacing and call parsed values
                                jsonResponse2 +=  kWh;
                            }
                            // Adds the jsonResponse string to the TextView "results"
                            roomTwo.setText(jsonResponse2);

                            barEntry.add(new BarEntry(2, roomTwoNumber));
                            Bardataset2 = new BarDataSet(barEntry, "Current Energy Consumption");
                            Bardataset2.setColors(ColorTemplate.PASTEL_COLORS);
                            data = new BarData(Bardataset2);

                            mChart.setData(data);

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity().getApplicationContext(),
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

    private void fetchRoomThree() {

        JsonArrayRequest req = new JsonArrayRequest(JsonURL3,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            jsonResponse3 = "";
                            //loop through each response in the json
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject HouseData = (JSONObject) response.get(i);

                                String kWh = HouseData.getString("kWh");
                                roomThreeNumber = Float.valueOf(kWh);

                                barEntry.add(new BarEntry(3, roomThreeNumber));
                                Bardataset3 = new BarDataSet(barEntry, "Current Energy Consumption");
                                Bardataset3.setColors(ColorTemplate.PASTEL_COLORS);
                                data = new BarData(Bardataset3);

                                mChart.setData(data);

                                //provide spacing and call parsed values
                                jsonResponse3 +=  kWh;
                            }
                            // Adds the jsonResponse string to the TextView "results"
                            roomThree.setText(jsonResponse3);

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity().getApplicationContext(),
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

    private void fetchRoomFour() {

        JsonArrayRequest req = new JsonArrayRequest(JsonURL4,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            jsonResponse4 = "";
                            //loop through each response in the json
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject HouseData = (JSONObject) response.get(i);

                                String kWh = HouseData.getString("kWh");
                                roomFourNumber = Float.valueOf(kWh);

                                barEntry.add(new BarEntry(4, roomFourNumber));
                                Bardataset4 = new BarDataSet(barEntry, "Current Energy Consumption");
                                Bardataset4.setColors(ColorTemplate.PASTEL_COLORS);
                                data = new BarData(Bardataset4);

                                mChart.setData(data);
                                //provide spacing and call parsed values
                                jsonResponse4 +=  kWh;
                            }
                            // Adds the jsonResponse string to the TextView "results"
                            roomFour.setText(jsonResponse4);

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity().getApplicationContext(),
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

    @Override
    public void onPause() {
        super.onPause();
    }

}
