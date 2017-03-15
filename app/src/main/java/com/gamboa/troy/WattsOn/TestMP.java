package com.gamboa.troy.WattsOn;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

public class TestMP extends AppCompatActivity implements OnChartValueSelectedListener{

    private LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_mp);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        chart = (LineChart) findViewById(R.id.chart);

        chart.setOnChartValueSelectedListener(this);

        //description text
        chart.getDescription().setEnabled(true);

        //enable touch gestures and scaling / dragging
        chart.setTouchEnabled(true);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setDrawGridBackground(true);

        //if it is disabled, scaling can be done on separate axes
        chart.setPinchZoom(true);

        //set background color
        chart.setBackgroundColor(Color.LTGRAY);
        LineData data = new LineData();
        data.setValueTextColor(Color.WHITE);

        //empty data addition
        chart.setData(data);

        //legends
        Legend l = chart.getLegend();

        l.setForm(Legend.LegendForm.LINE);
        l.setTextColor(Color.WHITE);

        XAxis x1 = chart.getXAxis();
        x1.setTextColor(Color.WHITE);
        x1.setDrawGridLines(false);
        x1.setAvoidFirstLastClipping(true);
        x1.setEnabled(true);

        YAxis y1 = chart.getAxisLeft();
        y1.setTextColor(Color.WHITE);
        y1.setDrawGridLines(true);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i("Entry Selected", e.toString());

    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing Selected", "Nothing Selected.");

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
