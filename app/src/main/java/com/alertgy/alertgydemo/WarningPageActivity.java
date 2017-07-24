package com.alertgy.alertgydemo;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;


public class WarningPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning_page);

        GlucoseDataGenerator glucoseData = new GlucoseDataGenerator(70,1,-1);
        List<Entry> entries = new ArrayList<Entry>();//data entries used on glucose graph
        for(int i=0; i<48; i++){

            entries.add(new Entry(i, (float)glucoseData.getGlucoseValue(i)));
        }
        Entry info = entries.get(46);
        //float data = (float)info.getData();
        //dataSet.addEntry(new Entry(i, (float)(Math.random() * 100 + 50)));

        LineChart warningChart = (LineChart) findViewById(R.id.warningChart);
        LineDataSet dataSet = new LineDataSet(entries, "Glucose Data");//DataSet used on glucose graph
        LineData lineData = new LineData(dataSet);

        XAxis xAxis = warningChart.getXAxis();
        xAxis.setEnabled(false);
        YAxis rightAxis = warningChart.getAxisRight();
        rightAxis.setEnabled(false);
        Description des = warningChart.getDescription();
        Legend leg = warningChart.getLegend();
        leg.setEnabled(false);
        des.setEnabled(false);
        dataSet.setColor(4);
        dataSet.setValueTextColor(3);
        warningChart.setData(lineData);
        TextView textView = (TextView) findViewById(R.id.glucoseLowText);
        String formattedData = String.format("%.2f"+" mg/dL", glucoseData.getGlucoseValue(47));
        textView.setText(formattedData);
    }

    public void onClickAcknowledged(View view){
        String message = "Susan's blood sugar is dangerously low. Her current location is 3655 N 1st St, San Jose, CA.";
        String phoneNo = "4077301399";

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(SetupActivity.getPhoneNumber(), null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS Sent!",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "SMS failed, please try again later!",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        Intent intent = new Intent(WarningPageActivity.this, HelpPageActivity.class);
        startActivity(intent);

    }

}
