package com.jet2travel.employeedata.employeeProfileDetail;

public class EmployeeDetailPresenter implements IEmployeeDetailContract.Presenter {

    private IEmployeeDetailContract.View employeeListView;

    public EmployeeDetailPresenter(IEmployeeDetailContract.View detailView) {
        this.employeeListView = detailView;
    }

    @Override
    public void onDestroy() {
        employeeListView = null;
    }

    @Override
    public void requestData() {
        employeeListView.sendData();
    }
}
