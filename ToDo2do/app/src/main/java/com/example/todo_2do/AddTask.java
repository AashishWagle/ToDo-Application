package com.example.todo_2do;


import android.app.DatePickerDialog;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTask extends Fragment implements AdapterView.OnItemSelectedListener {

    private String item;
    private SeekBar mSekbar;
    private int progress_value;

    public AddTask() {
        // Required empty public constructor
    }

    public static AddTask newInstance(){
        return new AddTask();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView =  inflater.inflate(R.layout.fragment_add_task, container, false);

        Spinner spinner  = (Spinner) rootView.findViewById(R.id.spinnerRepeat);
        //on calender icon click
        ImageView imageViewDate = (ImageView) rootView.findViewById(R.id.ivDate);
        imageViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newfragment = new DateFragment();
                newfragment.show(getFragmentManager(),"DatePicker");
            }
        });
        //on clock icon click
        ImageView imageViewClock = (ImageView) rootView.findViewById(R.id.ivTime);
        imageViewClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newfragment = new TimeFragment();
                newfragment.show(getFragmentManager(),"TimePicker");
            }
        });

        spinner.setOnItemSelectedListener(this);

        //Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.repeatItems,
                android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //adding events to seekbar
        mSekbar = (SeekBar) rootView.findViewById(R.id.seekBar);
        mSekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = progress;
                setColor(progress_value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                setColor(progress_value);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                setColor(progress_value);
            }
        });

        return rootView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //on seleting a spinner item
        item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setColor(int progress_value){
        if(progress_value <=30) {
            mSekbar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.low_prior), PorterDuff.Mode.MULTIPLY);
            mSekbar.getThumb().setColorFilter(getResources().getColor(R.color.low_prior),PorterDuff.Mode.SRC_IN);
        }
        else if(progress_value>30 && progress_value<=60){
            mSekbar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.medium_prior), PorterDuff.Mode.MULTIPLY);
            mSekbar.getThumb().setColorFilter(getResources().getColor(R.color.medium_prior),PorterDuff.Mode.SRC_IN);
        }
        else if(progress_value>60 && progress_value<=100){
            mSekbar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.high_prior), PorterDuff.Mode.MULTIPLY);
            mSekbar.getThumb().setColorFilter(getResources().getColor(R.color.high_prior),PorterDuff.Mode.SRC_IN);
        }
    }

}
