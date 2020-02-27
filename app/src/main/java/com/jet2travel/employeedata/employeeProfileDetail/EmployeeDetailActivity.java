package com.jet2travel.employeedata.employeeProfileDetail;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jet2travel.employeedata.R;
import com.jet2travel.employeedata.models.Employee;
import com.jet2travel.employeedata.utils.AppConstant;

public class EmployeeDetailActivity extends AppCompatActivity implements IEmployeeDetailContract.View {

    private ImageView imgEmployeeProfile;
    private TextView txtEmployeeName;
    private TextView txtEmployeeSalary;
    private TextView txtEmployeeAge;
    private EmployeeDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail_profile);
        getSupportActionBar().setTitle(getString(R.string.employee_details));
        initUI();
        presenter = new EmployeeDetailPresenter(this);
        presenter.requestData();
    }


    private void initUI() {
          imgEmployeeProfile=findViewById(R.id.img_employee_profile_large);
          txtEmployeeName=findViewById(R.id.txt_employee_name);
          txtEmployeeSalary=findViewById(R.id.txt_employee_salary);
          txtEmployeeAge=findViewById(R.id.txt_employee_id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }


    @Override
    public void sendData() {
        Employee employee = (Employee) getIntent().getSerializableExtra(AppConstant.EMPLOYEE_DATA);
        initializeData(employee);
    }

    private void initializeData(Employee employee) {
        if (employee != null) {
            if (employee.getEmployeeName() != null) {
                txtEmployeeName.setText(employee.getEmployeeName());
            }
            if (employee.getEmployeeSalary() != null) {
                txtEmployeeSalary.setText(employee.getEmployeeSalary()+"$");
            }
            if (Integer.toString(employee.getEmployeeAge()) != null) {
                txtEmployeeAge.setText(Integer.toString(employee.getEmployeeAge()));
            }
        }
    }

}

