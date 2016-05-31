package com.example.sebastian.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by sebastian on 30/05/16.
 */
public class DateDialog extends DialogFragment {

    EditText txtDate;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Obtener fecha actual

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Retornar en nueva instancia del dialogo selector de fecha
        return new DatePickerDialog(
                getActivity(),
                (DatePickerDialog.OnDateSetListener) getActivity(),
                year,
                month,
                day);
    }


    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        String date = year +"-"+ monthOfYear + "-" + dayOfMonth;
        txtDate.setText(date);






    }
}
