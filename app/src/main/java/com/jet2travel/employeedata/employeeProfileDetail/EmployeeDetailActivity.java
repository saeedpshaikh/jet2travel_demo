package com.jet2travel.employeedata.employeeProfileDetail;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jet2travel.employeedata.R;
import com.jet2travel.employeedata.models.Employee;
import com.jet2travel.employeedata.utils.AppConstant;

public class EmployeeDetailActivity extends AppCompatActivity implements IEmployeeDetailContract.View {

    private ImageView imgEmployeeProfile;
    private TextView txtEmployeeName;
    private TextView txtEmployeeSalary;
    private TextView txtEmployeeAge;
    private Activity activity;
    private EmployeeDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail_profile);
        getSupportActionBar().setTitle(getString(R.string.employee_details));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity = this;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeData(Employee employee) {
        if (employee != null) {
            if (employee.getEmployeeName() != null) {
                txtEmployeeName.setText(employee.getEmployeeName());
            }
            if (employee.getEmployeeSalary() != null) {
                txtEmployeeSalary.setText(employee.getEmployeeSalary() + "$");
            }
            if (Integer.toString(employee.getEmployeeAge()) != null) {
                txtEmployeeAge.setText(Integer.toString(employee.getEmployeeAge()));
            }
            if (!TextUtils.isEmpty(employee.getProfileImage())) {
                showProfileImage(employee.getProfileImage());
            }
        }
    }


    private void showProfileImage(String profileImage) {
            Glide.with(activity)
                    .load(profileImage)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(imgEmployeeProfile);
        }

}

