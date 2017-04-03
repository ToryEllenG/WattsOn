package com.gamboa.troy.WattsOn;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;


/**
 * Created by Troygbv on 1/30/2017.
 */

public class StatisticsActivity extends AppCompatActivity {

    Toolbar statsToolBar;
    TextView totalView, averageView, roomOneView, roomTwoView, roomThreeView, roomFourView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(R.string.titleStats);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        //custom toolbar
        statsToolBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(statsToolBar);
        getSupportActionBar().setTitle("Statistics");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        statsToolBar.setTitleTextColor(Color.WHITE);

        totalView = (TextView)findViewById(R.id.totalTV);
        averageView = (TextView)findViewById(R.id.averageTV);
        roomOneView = (TextView)findViewById(R.id.roomOnePercent);
        roomTwoView = (TextView)findViewById(R.id.roomTwoPercent);
        roomThreeView = (TextView)findViewById(R.id.roomThreePercent);
        roomFourView = (TextView)findViewById(R.id.roomFourPercent);

        String roomOne = getIntent().getExtras().getString("roomOne");
        String roomTwo = getIntent().getExtras().getString("roomTwo");
        String roomThree = getIntent().getExtras().getString("roomThree");
        String roomFour = getIntent().getExtras().getString("roomFour");

        //convert room strings to usable floats
        float roomOneFloat = Float.valueOf(roomOne);
        float roomTwoFloat = Float.valueOf(roomTwo);
        float roomThreeFloat = Float.valueOf(roomThree);
        float roomFourFloat = Float.valueOf(roomFour);
        float totalFloat = roomOneFloat + roomTwoFloat + roomThreeFloat + roomFourFloat;
        float averageFloat = (roomOneFloat + roomTwoFloat + roomThreeFloat + roomFourFloat)/4;

        String total = Float.toString(totalFloat);
        String average = Float.toString(averageFloat);
        String roomOnePercent = Float.toString((roomOneFloat/totalFloat)*100);
        String roomTwoPercent = Float.toString((roomTwoFloat/totalFloat)*100);
        String roomThreePercent = Float.toString((roomThreeFloat/totalFloat)*100);
        String roomFourPercent = Float.toString((roomFourFloat/totalFloat)*100);


        totalView.setText(total);
        averageView.setText(average);
        roomOneView.setText(roomOnePercent);
        roomTwoView.setText(roomTwoPercent);
        roomThreeView.setText(roomThreePercent);
        roomFourView.setText(roomFourPercent);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
