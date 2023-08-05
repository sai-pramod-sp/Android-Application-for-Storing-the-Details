package com.example.rdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {EmpEntity.class}, version = 1)
public abstract class EmpdatabaseHelper extends RoomDatabase {

    public abstract EmpDao empDao();

    private static EmpdatabaseHelper empdatabaseHelper;

    public static synchronized EmpdatabaseHelper getEmpdatabaseHelper(Context context){
        if(empdatabaseHelper == null){
            empdatabaseHelper = Room.databaseBuilder(context, EmpdatabaseHelper.class, "empdetails").build();
            return empdatabaseHelper;
        }
        return empdatabaseHelper;
    }
}
