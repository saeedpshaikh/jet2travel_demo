package com.jet2travel.employeedata.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.jet2travel.employeedata.models.Employee;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

/**
 * Room dao
 */

@Dao
public interface DaoAccess {

    @Query("SELECT * FROM Employee")
    List<Employee> fetchAllEmployeeData();

    @Insert(onConflict = REPLACE)
    Long[] addEmployeeData(List<Employee> employees);

    @Query("DELETE FROM Employee")
    public void deleteRecord();

    @Delete
    void delete(Employee employee);
}
