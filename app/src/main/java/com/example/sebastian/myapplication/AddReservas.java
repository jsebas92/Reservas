package com.example.sebastian.myapplication;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by sebastian on 30/05/16.
 */
public class AddReservas extends AppCompatActivity {

    Calendar calendar = Calendar.getInstance();
    EditText edtIdcancha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add);

        Bundle bundle= getIntent().getExtras();
        String id = bundle.getString("idcancha");



        edtIdcancha = (EditText) findViewById(R.id.edtidCancha);
        edtIdcancha.setText(id.toString());




    }


}
