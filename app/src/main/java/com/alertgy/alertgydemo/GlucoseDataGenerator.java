package com.alertgy.alertgydemo;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by jonathanzieg on 6/23/17.
 */

public class GlucoseDataGenerator {
    private LinkedList glucoseValues = new LinkedList();
    /*
    public int describeContents(){
        return 0;
    }

    public void writeToParcel(Parcel out, int flags){
        out.writeList(glucoseValues);
    }

    public static final Parcelable.Creator<GlucoseDataGenerator> CREATOR
            = new Parcelable.Creator<GlucoseDataGenerator>() {
        public GlucoseDataGenerator createFromParcel(Parcel in) {
            return new GlucoseDataGenerator(in);
        }

        public GlucoseDataGenerator[] newArray(int size) {
            return new GlucoseDataGenerator[size];
        }
    };

    private GlucoseDataGenerator(Parcel in) {
        glucoseValues = in.readList();
    }
    */

    public GlucoseDataGenerator(double Base, double Rate, double Trend){

        Random generator = new Random();//used to generate random numbers
        double base = Base;//base glucose level,graph starts at this number
        double number = 0;//holds each random number generated
        double trend = Trend;//determines if glucose level is increasing or decreasing.
        double rate = Rate;//adjusts rate of change of glucose level by multiplying the random number generated

        glucoseValues.add(base);//sets base as first element in list
        for(int i = 1;i<48;i++) {//288 readings would be collected through a full day

            //adds a new value to linked list based on random number generated
            number = (generator.nextDouble()*rate*trend)+(Double)(glucoseValues.peekLast());
            glucoseValues.add(number);
        }

    }

    public double getGlucoseValue(int i){
        return (double)(glucoseValues.get(i));
    }

    //public double getAlarmValue(){
        /*EditText text = (EditText) findViewById(R.id.singleAlarm);//makes EditText object from singleAlarm in AlarmActivity
        Double alarmValue = Double.parseDouble(text.getText().toString());//turns string entered into EditText view into double
        return alarmValue;
        */
    //}

}
