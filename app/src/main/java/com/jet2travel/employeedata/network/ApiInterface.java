package com.jet2travel.employeedata.network;

import com.jet2travel.employeedata.models.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("v1/employees")
    Call<ApiResponse> getEmployeeList();
}
