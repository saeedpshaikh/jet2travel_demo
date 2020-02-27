package com.jet2travel.employeedata.models;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class Employee implements Serializable {
    @PrimaryKey
    private int id;
    @Nullable
    @ColumnInfo(name = "employee_name")
    @SerializedName("employee_name")
    private String employeeName;
    @Nullable
    @ColumnInfo(name = "employee_salary")
    @SerializedName("employee_salary")
    private String employeeSalary;
    @Nullable
    @ColumnInfo(name = "employee_age")
    @SerializedName("employee_age")
    private int employeeAge;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(String employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public int getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(int employeeAge) {
        this.employeeAge = employeeAge;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @SerializedName("profile_image")
    private String profileImage;


}
