package com.jet2travel.employeedata.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jet2travel.employeedata.models.Employee;
import com.jet2travel.employeedata.utils.AppConstant;

@Database(entities = { Employee.class }, version = 1)
public abstract class EmployeeDatabase  extends RoomDatabase {

    public abstract DaoAccess getEmployeeDao();

    private static EmployeeDatabase employeeDB;


    public static EmployeeDatabase getInstance(Context context) {
        if (null == employeeDB) {
            employeeDB = buildDatabaseInstance(context);
        }
        return employeeDB;
    }

    private static EmployeeDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                EmployeeDatabase.class,
                AppConstant.DATABASE_NAME)
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        employeeDB = null;
    }

}
