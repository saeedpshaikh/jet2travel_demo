package com.jet2travel.employeedata.employeeProfileData;

import android.util.Log;

import com.jet2travel.employeedata.models.ApiResponse;
import com.jet2travel.employeedata.models.Employee;
import com.jet2travel.employeedata.network.ApiClient;
import com.jet2travel.employeedata.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeModel implements IEmployeeContract.Model {
    String TAG = "EmpList";

    @Override
    public void getEmployeeList(final OnFinishedListener onFinishedListener, int pageNo) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse> call = apiService.getEmployeeList();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                List<Employee> employees = response.body().getEmployees();
                onFinishedListener.onFinished(employees);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);
            }
        });

    }
}
