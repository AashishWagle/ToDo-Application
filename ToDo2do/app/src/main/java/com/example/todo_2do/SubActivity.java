package com.example.todo_2do;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.todo_2do.Fragment.AddTaskFragment;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        displayFragment();

    }

    public void displayFragment(){
        AddTaskFragment addFragment = AddTaskFragment.newInstance();
        //Get fragmentManager and start a transaction
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //Add the SimpleFragment
        fragmentTransaction.add(R.id.fragementContainer,addFragment).addToBackStack(null).commit();
    }


}
