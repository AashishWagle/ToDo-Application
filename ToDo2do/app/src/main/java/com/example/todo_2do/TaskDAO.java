package com.example.todo_2do;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TaskDAO {

    @Insert
    void insert (Task task);

    @Query("DELETE from task_table")
    void deleteAll();

    @Query("SELECT * from task_table ORDER BY id desc")
    LiveData<List<Task>> getAllTask();

}
