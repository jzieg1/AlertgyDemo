package com.alertgy.alertgydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SetupActivity extends AppCompatActivity {

    private static String phoneNumber = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
    }

    public void onClickContinue(View view){
        EditText textNumber = (EditText) findViewById(R.id.emergencyContact);
        phoneNumber = textNumber.getText().toString();
        Intent intent = new Intent(SetupActivity.this, StartPageActivity.class);
        startActivity(intent);
    }

    public static String getPhoneNumber(){
        return phoneNumber;
    }
}
