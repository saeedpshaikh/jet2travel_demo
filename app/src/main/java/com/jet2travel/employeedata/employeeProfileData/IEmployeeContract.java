package com.jet2travel.employeedata.employeeProfileData;

import com.jet2travel.employeedata.models.Employee;

import java.util.List;

public interface IEmployeeContract {
    interface Model {
        interface OnFinishedListener {
            void onFinished(List<Employee> employees);

            void onFailure(Throwable t);
        }

        void getEmployeeList(OnFinishedListener onFinishedListener, int pageNo);
    }

    interface View {
        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<Employee> employees);

        void onResponseFailure(Throwable throwable);

        void showDataFromDatabase();

    }

    interface Presenter {
        void onDestroy();

        void getMoreData(int pageNo);

        void requestDataFromServer();

        void requestDataDatabase();
    }
}
