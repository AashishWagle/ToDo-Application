package com.example.todo_2do;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private EditText date;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstance){
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);

        date = (EditText)getActivity().findViewById(R.id.editDate);

        return new DatePickerDialog(getActivity(), this, yy, mm, dd);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        populateSetDate(year,month+1,dayOfMonth);
    }
    public void populateSetDate(int year, int month, int day) {
        date.setText(month+"/"+day+"/"+year);
    }
}
