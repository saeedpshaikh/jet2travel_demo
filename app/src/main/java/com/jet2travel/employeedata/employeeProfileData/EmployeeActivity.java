package com.jet2travel.employeedata.employeeProfileData;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jet2travel.employeedata.R;
import com.jet2travel.employeedata.adapter.UserAdapter;
import com.jet2travel.employeedata.application.ApplicationController;
import com.jet2travel.employeedata.database.EmployeeDatabase;
import com.jet2travel.employeedata.employeeProfileDetail.EmployeeDetailActivity;
import com.jet2travel.employeedata.models.Employee;
import com.jet2travel.employeedata.utils.AlertDialogMessage;
import com.jet2travel.employeedata.utils.AppConstant;
import com.jet2travel.employeedata.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

public class EmployeeActivity extends AppCompatActivity implements IEmployeeContract.View, EmployeeItemClickListener {
    private ProgressBar pbLoading;
    private EmployeeListPresenter employeeListPresenter;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView rvEmployee;
    private List<Employee> employeesList;
    private UserAdapter userAdapter;
    private EmployeeActivity activity;
    private EmployeeDatabase employeeDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_data);
        activity = this;
     //   getSupportActionBar().setTitle(getString(R.string.employee_title));

        setUpDatabase();
        initUI();
        //Initializing presenter
        employeeListPresenter = new EmployeeListPresenter(this);

        boolean isNetworkAvailable = UiUtils.isConnected(activity);
        if (!isNetworkAvailable) {
            if (employeeDatabase != null) {
                employeeListPresenter.requestDataDatabase();
            }
        } else {
            if (employeeDatabase != null) {
                employeeDatabase.getEmployeeDao().deleteRecord();
            }
            employeeListPresenter.requestDataFromServer();
        }
    }

    /**
     * This method will initialize the UI components
     */
    private void initUI() {
        pbLoading = findViewById(R.id.pb_loading);
        rvEmployee = findViewById(R.id.rv_employee_list);
        initializeRealerView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        employeeListPresenter.onDestroy();
    }


    private void initializeRealerView() {
        employeesList = new ArrayList<>();
        userAdapter = new UserAdapter(this, employeesList,employeeDatabase);
        mLayoutManager = new LinearLayoutManager(this);
        rvEmployee.setLayoutManager(mLayoutManager);
        rvEmployee.setItemAnimator(new DefaultItemAnimator());
        rvEmployee.setAdapter(userAdapter);
    }

    @Override
    public void showProgress() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerView(List<Employee> employees) {
        employeesList.addAll(employees);
        insertAllData();
        userAdapter.notifyDataSetChanged();
    }

    private void insertAllData() {
        if (employeesList != null && employeesList.size() > 0) {
            employeeDatabase.getEmployeeDao().addEmployeeData(employeesList);
        }
    }

    private void fetchAndSetAllData() {
        employeesList.addAll(employeeDatabase.getEmployeeDao().fetchAllEmployeeData());
        if (employeesList != null && employeesList.size() > 0) {
            userAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e("", throwable.getMessage());
        Toast.makeText(this, getString(R.string.api_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDataFromDatabase() {
        fetchAndSetAllData();
    }

    @Override
    public void onEmployeeItemClick(int position) {
        Employee employee = employeesList.get(position);
        if (employee != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(AppConstant.EMPLOYEE_DATA, employee);
            UiUtils.getInstance().goToActivity(activity, EmployeeDetailActivity.class, bundle);
        }
    }


    private void showExitApp() {
        AlertDialogMessage.showAlertDialogWithButtons(activity, getString(R.string.confirm), getString(R.string.exit_alert_message),
                new ExitDialogListener());
    }

    private class ExitDialogListener implements AlertDialogMessage.AlertListener {
        @Override
        public void onDialogYesClick(String id) {
            UiUtils.getInstance().clearActivityStack(ApplicationController.getActivityStack());
            finish();
        }

        @Override
        public void onDialogNoClick(String id) {

        }
    }

    @Override
    public void onBackPressed() {
        showExitApp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        if (id == R.id.action_sort_name) {
            userAdapter.sortList(AppConstant.NAME);
        }
        if (id == R.id.action_sort_age) {
            userAdapter.sortList(AppConstant.AGE);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setUpDatabase() {
        employeeDatabase = EmployeeDatabase.getInstance(activity);
    }

}
