package com.jet2travel.employeedata.employeeProfileDetail;

public interface IEmployeeDetailContract {
    interface View {
        void sendData();
    }

    interface Presenter {
        void onDestroy();

        void requestData();
    }
}
