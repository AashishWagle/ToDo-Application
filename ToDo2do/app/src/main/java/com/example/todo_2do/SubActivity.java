package com.example.todo_2do;

import android.content.ClipData;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SubActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        displayFragment();

    }

    public void displayFragment(){
        AddTask addFragment = AddTask.newInstance();
        //Get fragmentManager and start a transaction
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //Add the SimpleFragment
        fragmentTransaction.add(R.id.fragementContainer,addFragment).addToBackStack(null).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflating menu; this adds item to the action bar
        getMenuInflater().inflate(R.menu.menu_sub,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        
        return true;
    }


}
