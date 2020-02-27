package com.jet2travel.employeedata.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {
    private String status;
    @SerializedName("data")
    private List<Employee> employees;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
