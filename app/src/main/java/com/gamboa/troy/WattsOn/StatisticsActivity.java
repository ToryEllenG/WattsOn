package com.gamboa.troy.WattsOn;

import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Troygbv on 1/30/2017.
 */

public class StatisticsActivity extends AppCompatActivity {

    Toolbar statsToolBar;
    TextView totalView, averageView, roomOneView, roomTwoView, roomThreeView, roomFourView;
    private PieChart pieChart;
    private Object Typeface;

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

        //calculate the total, average and percent usage by room into a String
        String total = Float.toString(totalFloat);
        String average = Float.toString(averageFloat);
        String roomOnePercent = Float.toString((roomOneFloat/totalFloat)*100);
        String roomTwoPercent = Float.toString((roomTwoFloat/totalFloat)*100);
        String roomThreePercent = Float.toString((roomThreeFloat/totalFloat)*100);
        String roomFourPercent = Float.toString((roomFourFloat/totalFloat)*100);

        //Set the TextView fields to the calculations
        totalView.setText(total);
        averageView.setText(average);
        roomOneView.setText(roomOnePercent);
        roomTwoView.setText(roomTwoPercent);
        roomThreeView.setText(roomThreePercent);
        roomFourView.setText(roomFourPercent);

        //initiate chart
        pieChart = (PieChart)findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawCenterText(true);
        pieChart.setCenterText("Total Percentage Use by Room");

        pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);


        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(Float.valueOf(roomOnePercent), "Room One"));
        entries.add(new PieEntry(Float.valueOf(roomTwoPercent), "Room Two"));
        entries.add(new PieEntry(Float.valueOf(roomThreePercent), "Room Three"));
        entries.add(new PieEntry(Float.valueOf(roomFourPercent), "Room Four"));

        PieDataSet set = new PieDataSet(entries, "");
        set.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.invalidate();

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
