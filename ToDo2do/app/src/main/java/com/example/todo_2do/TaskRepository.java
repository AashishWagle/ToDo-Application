package com.example.todo_2do;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.todo_2do.database.Task;
import com.example.todo_2do.database.TaskDAO;
import com.example.todo_2do.database.TaskRoomDatabase;

import java.util.List;

public class TaskRepository {
    private TaskDAO mtaskDao;
    private LiveData<List<Task>> mAllTask;

    TaskRepository(Application application){
        TaskRoomDatabase db = TaskRoomDatabase.getDatabase(application);
        mtaskDao = db.taskDao();
        mAllTask = mtaskDao.getAllTask();
    }

    LiveData<List<Task>> getAllTasks(){
        return  this.mAllTask;
    }

    public void insert(Task task){
        new insertAsyncTask(mtaskDao).execute(task);
    }

    public class insertAsyncTask extends AsyncTask<Task,Void,Void>{

        private TaskDAO mAsyncTaskDao;

        insertAsyncTask(TaskDAO dao){
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(Task... tasks) {
            mAsyncTaskDao.insert(tasks[0]);
            return null;
        }
    }
}
