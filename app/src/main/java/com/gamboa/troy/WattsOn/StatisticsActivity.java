package com.gamboa.troy.WattsOn;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by Troygbv on 1/30/2017.
 */

public class StatisticsActivity extends AppCompatActivity {

    Toolbar statsToolBar;
    TextView totalView, averageView, roomOneView, roomTwoView, roomThreeView, roomFourView;
    private PieChart pieChart;

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

        //fetch the values of the values from FragmentMonitor class
        String roomOne = getIntent().getExtras().getString("roomOne");
        String roomTwo = getIntent().getExtras().getString("roomTwo");
        String roomThree = getIntent().getExtras().getString("roomThree");
        String roomFour = getIntent().getExtras().getString("roomFour");

        //convert room strings to usable floats
        float roomOneFloat = + Float.valueOf(roomOne);
        float roomTwoFloat = Float.valueOf(roomTwo);
        float roomThreeFloat = Float.valueOf(roomThree);
        float roomFourFloat = Float.valueOf(roomFour);
        float totalFloat = roomOneFloat + roomTwoFloat + roomThreeFloat + roomFourFloat;
        float averageFloat = (roomOneFloat + roomTwoFloat + roomThreeFloat + roomFourFloat)/4;

        //calculate the total, average and percent usage by room into a String
        String total = Float.toString(totalFloat);
        String average = Float.toString(averageFloat);
        float roomOnePercent = roomOneFloat/totalFloat*100;
        float roomTwoPercent = roomTwoFloat/totalFloat*100;
        float roomThreePercent = roomThreeFloat/totalFloat*100;
        float roomFourPercent = roomFourFloat/totalFloat*100;

        //format to two decimal places
        String one = String.format(Locale.US, "%.2f", roomOnePercent);
        String two = String.format(Locale.US, "%.2f", roomTwoPercent);
        String three = String.format(Locale.US, "%.2f", roomThreePercent);
        String four = String.format(Locale.US, "%.2f", roomFourPercent);

        //concatenate the percent sign
        String percent = " %";
        String roomOnePercentV = one + percent;
        String roomTwoPercentV = two + percent;
        String roomThreePercentV = three + percent;
        String roomFourPercentV = four + percent;

        //Set the TextView fields to the calculations
        totalView.setText(total);
        averageView.setText(average);
        roomOneView.setText(roomOnePercentV);
        roomTwoView.setText(roomTwoPercentV);
        roomThreeView.setText(roomThreePercentV);
        roomFourView.setText(roomFourPercentV);

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
        pieChart.setHoleRadius(50);

        //set Animation
        pieChart.animateY(2000, Easing.EasingOption.EaseInOutQuad);

        //add float values to entry list
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(roomOnePercent, "1"));
        entries.add(new PieEntry(roomTwoPercent, "2"));
        entries.add(new PieEntry(roomThreePercent, "3"));
        entries.add(new PieEntry(roomFourPercent, "4"));

        //dataset properties
        PieDataSet set = new PieDataSet(entries, "");
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        set.setSliceSpace(3f);
        set.setSelectionShift(5f);
        set.setValueLinePart1OffsetPercentage(80.f);
        set.setValueLinePart1Length(0.1f);
        set.setValueLinePart2Length(0.1f);
        //set.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE); uncomment to put percent values outside of pie
        set.setValueTextColor(Color.BLACK);

        //data properties
        PieData data = new PieData(set);
        data.setValueTextSize(16f);
        data.setValueTextColor(Color.BLACK);
        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);
        pieChart.notifyDataSetChanged();
        pieChart.invalidate();

        //legend properties
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
