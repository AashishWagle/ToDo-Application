package com.example.todo_2do.Fragment;


import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.todo_2do.AppExecutors;
import com.example.todo_2do.R;
import com.example.todo_2do.database.Task;
import com.example.todo_2do.database.TaskRoomDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private String item;
    private SeekBar mSekbar;
    private int progress_value;
    private String priorityLevel = "level";

    private Toolbar mTool;

    //View items
    private EditText mTitle;
    private EditText mDate;
    private EditText mTime;
    private Spinner mRepeat;
    private SeekBar mPriority;

    // Constant for default task id to be used when not in update mode
    private static final int DEFAULT_TODO_ID = -1;

    private int mTodoId = DEFAULT_TODO_ID;

    // Member variable for the Database
    private TaskRoomDatabase taskDb;

    public AddTaskFragment() {
        // Required empty public constructor
    }

    public static AddTaskFragment newInstance(){
        return new AddTaskFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        taskDb = TaskRoomDatabase.getDatabase(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView =  inflater.inflate(R.layout.fragment_add_task, container, false);

        //getting all view items
        mTitle = rootView.findViewById(R.id.editTitle);
        mDate = rootView.findViewById(R.id.editDate);
        mTime = rootView.findViewById(R.id.editTime);
        mRepeat = rootView.findViewById(R.id.spinnerRepeat);
        mPriority = rootView.findViewById(R.id.seekBar);

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
            this.priorityLevel = "low";
        }
        else if(progress_value>30 && progress_value<=60){
            mSekbar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.medium_prior), PorterDuff.Mode.MULTIPLY);
            mSekbar.getThumb().setColorFilter(getResources().getColor(R.color.medium_prior),PorterDuff.Mode.SRC_IN);
            this.priorityLevel = "medium";
        }
        else if(progress_value>60 && progress_value<=100){
            mSekbar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.high_prior), PorterDuff.Mode.MULTIPLY);
            mSekbar.getThumb().setColorFilter(getResources().getColor(R.color.high_prior),PorterDuff.Mode.SRC_IN);
            this.priorityLevel = "high";
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu,inflater);
        //Inflating menu; this adds item to the action bar
        inflater.inflate(R.menu.menu_sub,menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        onDoneButtonClick();
        return true;
    }

    private void onDoneButtonClick(){
        String taskTitle = mTitle.getText().toString();
        String taskDate = mDate.getText().toString();
        String taskTime = mTime.getText().toString();
        String taskRepeat = mRepeat.getSelectedItem().toString();
        String taskImp = priorityLevel;

        if(taskTitle== null || taskDate== null || taskTime == null){
            Toast toast = Toast.makeText(getActivity(),"Fileds cannot be empty",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        final Task task = new Task(taskTitle,taskDate,taskTime,taskRepeat,taskImp);
        AppExecutors.getInstance().diskIO().execute(
                new Runnable() {
                    @Override
                    public void run() {
                        if(mTodoId == DEFAULT_TODO_ID)
                            taskDb.taskDao().insert(task);
                        else{
                            task.setId(mTodoId);
                            taskDb.taskDao().update(task);
                        }
                    }
                }
        );
        getActivity().finish();
    }

}
