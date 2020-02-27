
package com.jet2travel.employeedata.employeeProfileData;


import com.jet2travel.employeedata.models.Employee;

import java.util.List;

public class EmployeeListPresenter implements IEmployeeContract.Presenter, IEmployeeContract.Model.OnFinishedListener {

    private IEmployeeContract.View employeeListView;

    private IEmployeeContract.Model employeeListModel;

    public EmployeeListPresenter(IEmployeeContract.View employeeListView) {
        this.employeeListView = employeeListView;
        employeeListModel = new EmployeeModel();
    }

    @Override
    public void onDestroy() {
        this.employeeListView = null;
    }

    @Override
    public void getMoreData(int pageNo) {

        if (employeeListView != null) {
            employeeListView.showProgress();
        }
        employeeListModel.getEmployeeList(this, pageNo);
    }

    @Override
    public void requestDataFromServer() {

        if (employeeListView != null) {
            employeeListView.showProgress();
        }
        employeeListModel.getEmployeeList(this, 1);
    }

    @Override
    public void requestDataDatabase() {
        employeeListView.showDataFromDatabase();
    }

    @Override
    public void onFinished(List<Employee> employees) {
        employeeListView.setDataToRecyclerView(employees);
        if (employeeListView != null) {
            employeeListView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        employeeListView.onResponseFailure(t);
        if (employeeListView != null) {
            employeeListView.hideProgress();
        }
    }
}
