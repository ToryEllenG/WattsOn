package com.gamboa.troy.WattsOn;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import java.util.Random;
/**
 * Created by root on 2/24/17.
 */

public class FragmentMonitor extends Fragment {

    private final Handler mHandler = new Handler();
    private Runnable mTimer;
    private double graph1LastXValue = 5d;
    private LineGraphSeries<DataPoint> mSeries;
    private Spinner spinner1, spinner2;
    private Button stats;

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
        //initiate graph
        GraphView graph1 = (GraphView) view.findViewById(R.id.graph1);

        //initiate series
        mSeries = new LineGraphSeries<>();
        mSeries.setDrawDataPoints(true);
        mSeries.setDrawBackground(true);

        //Set properties of graph & set series
        graph1.addSeries(mSeries);
        graph1.getViewport().setXAxisBoundsManual(true);
        graph1.getViewport().setMinX(0);
        graph1.getViewport().setMaxX(5);
        graph1.getViewport().setMinY(0);
        graph1.getViewport().setMaxY(5);

        //Labels for the axes
        graph1.setTitle("Energy Usage");
        graph1.getGridLabelRenderer().setVerticalAxisTitle("Consumption (kWh)");
        graph1.getGridLabelRenderer().setHorizontalAxisTitle("Time (s)");

        //Calls id's of spinners. Spinner lists are populated in /res/values/strings.xml do something with these later
        spinner1 = (Spinner) view.findViewById(R.id.spinnerTimeInterval);
        spinner2 = (Spinner) view.findViewById(R.id.spinnerRoom);

        //Statistics button
        stats = (Button) view.findViewById(R.id.viewStatisticsBT);

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
        mTimer = new Runnable() {
            @Override
            public void run() {
                graph1LastXValue += 0.25d;
                mSeries.appendData(new DataPoint(graph1LastXValue, getRandom()), true, 22);
                mHandler.postDelayed(this, 330);
            }
        };
        mHandler.postDelayed(mTimer, 2000);
    }

    @Override
    public void onPause() {
        mHandler.removeCallbacks(mTimer);
        super.onPause();
    }

    double mLastRandom = 2;
    Random mRand = new Random();
    private double getRandom() {
        return mLastRandom += mRand.nextDouble()*0.5 - 0.25;
    }
}
