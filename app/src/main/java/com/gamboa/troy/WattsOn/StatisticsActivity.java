package com.gamboa.troy.WattsOn;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;


/**
 * Created by Troygbv on 1/30/2017.
 */

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(R.string.titleStats);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);


        GraphView graph2 = (GraphView) findViewById(R.id.graph2);
        //Series 1
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 4),
                new DataPoint(1, 6),
                new DataPoint(2, 7),
                new DataPoint(3, 2),
                new DataPoint(4, 5)
        });
        graph2.addSeries(series);
        //Series 2
        BarGraphSeries<DataPoint> series2 = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 2),
                new DataPoint(1, 8),
                new DataPoint(2, 3),
                new DataPoint(3, 5),
                new DataPoint(4, 7)
        });
        graph2.addSeries(series2);

        //style
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.RED;
            }
        });

        series.setSpacing(25);
        series2.setSpacing(25);
    }


}
