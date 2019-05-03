package com.example.todo_2do;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Task.class},version = 1,exportSchema = false)
public abstract class TaskRoomDatabase extends RoomDatabase {

    public abstract TaskDAO taskDao();

    private static TaskRoomDatabase INSTANCE;

    public static TaskRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (TaskRoomDatabase.class){
                if(INSTANCE == null){
                    //create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),TaskRoomDatabase.class,"task_database")
                            .fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
