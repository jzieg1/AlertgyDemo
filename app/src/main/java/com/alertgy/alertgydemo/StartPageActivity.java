package com.alertgy.alertgydemo;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.R.attr.data;
import static android.R.attr.start;

public class StartPageActivity extends AppCompatActivity {

    private int i = 0;//counter for data loop

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        GlucoseDataGenerator glucoseData = new GlucoseDataGenerator(70,1,1);
        List<Entry> entries = new ArrayList<Entry>();//data entries used on glucose graph
        for(int i=0; i<48; i++){
            entries.add(new Entry(i, (float)glucoseData.getGlucoseValue(i)));
        }

        LineChart startChart = (LineChart) findViewById(R.id.startChart);
        LineDataSet dataSet = new LineDataSet(entries, "Glucose Data");//DataSet used on glucose graph
        LineData lineData = new LineData(dataSet);

        XAxis xAxis = startChart.getXAxis();
        xAxis.setEnabled(false);
        YAxis rightAxis = startChart.getAxisRight();
        rightAxis.setEnabled(false);
        Description des = startChart.getDescription();
        des.setEnabled(false);
        Legend leg = startChart.getLegend();
        leg.setEnabled(false);
        dataSet.setColor(4);
        dataSet.setValueTextColor(3);
        startChart.setData(lineData);
        startChart.invalidate();

        //YAxis yAxis = startChart.getAxisLeft();
        //yAxis.setValueFormatter(formatter);
        TextView textView = (TextView) findViewById(R.id.glucoseText);
        String formattedData = String.format("%.2f"+" mg/dL", glucoseData.getGlucoseValue(47));
        textView.setText(formattedData);


    }

    public void onClickDone(View view){
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(StartPageActivity.this,WarningPageActivity.class);
                startActivity(intent);
                MediaPlayer mp = MediaPlayer.create(StartPageActivity.this, R.raw.low_glucose_alarm);
                mp.start();
            }
        },5000);
    }
}
