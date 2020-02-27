
package com.jet2travel.employeedata.adapter;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jet2travel.employeedata.R;
import com.jet2travel.employeedata.database.EmployeeDatabase;
import com.jet2travel.employeedata.employeeProfileData.EmployeeActivity;
import com.jet2travel.employeedata.models.Employee;
import com.jet2travel.employeedata.utils.AlertDialogMessage;
import com.jet2travel.employeedata.utils.AppConstant;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private EmployeeActivity employeeActivity;
    private List<Employee> employees;
    private EmployeeDatabase employeeDatabase;


    public UserAdapter(EmployeeActivity employeeActivity, List<Employee> employees, EmployeeDatabase employeeDatabase) {
        this.employeeActivity = employeeActivity;
        this.employees = employees;
        this.employeeDatabase = employeeDatabase;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_user_data, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Employee employee = employees.get(position);
        setFirstName(holder.txtUserName, employee);
        showProfileImage(employee, holder.imgEmployeeThumb);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employeeActivity.onEmployeeItemClick(position);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExitApp(employee);
            }
        });


    }

    @Override
    public int getItemCount() {
        return employees.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtUserName;
        public TextView txtUserAge;
        public ImageView imgEmployeeThumb;
        public Button btnDelete;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtUserName = itemView.findViewById(R.id.txt_user_name);
            txtUserAge = itemView.findViewById(R.id.txt_user_age);
            imgEmployeeThumb = itemView.findViewById(R.id.img_user_profile);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }

    private void showProfileImage(Employee employee, ImageView imgEmployee) {
        if (!TextUtils.isEmpty(employee.getProfileImage())) {
            Glide.with(employeeActivity)
                    .load(employee.getProfileImage())
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
                    .into(imgEmployee);
        }
    }


    private void setFirstName(TextView txtUserName, Employee employee) {
        if (employee != null && employee.getEmployeeName() != null) {
            txtUserName.setText(employee.getEmployeeName());
        }
    }

    private void showExitApp(Employee employee) {
        AlertDialogMessage.showAlertDialogWithButtons(employeeActivity, employeeActivity.getString(R.string.confirm), employeeActivity.getString(R.string.delete_alert_message),
                new DeleteDialogListener(employee));
    }

    public void deleteRecord(Employee employee) {

        if (employees.size() > 0) {
            employeeDatabase.getEmployeeDao().delete(employee);
            for (Iterator<Employee> it = employees.iterator(); it.hasNext(); ) {
                Employee emp = it.next();
                if (emp.getEmployeeName().equals(employee.getEmployeeName())) {
                    it.remove();
                }
            }
            notifyDataSetChanged();
        }
    }

    public void sortList(String action) {
        if (employees!=null && employees.size() > 0) {
            if (action.equals(AppConstant.NAME)) {
                Collections.sort(employees, new Comparator<Employee>() {
                    @Override
                    public int compare(final Employee objectlhs, final Employee objectrhs) {
                        return objectlhs.getEmployeeName().compareTo(objectrhs.getEmployeeName());
                    }
                });
            } else {
                Collections.sort(employees, new Comparator<Employee>() {
                    @Override
                    public int compare(final Employee objectlhs, final Employee objectrhs) {
                        Integer ageEmployeeLhs = objectlhs.getId();
                        Integer ageEmployeeRhs = objectrhs.getId();
                        return ageEmployeeLhs.compareTo(ageEmployeeRhs);
                    }
                });
            }
            notifyDataSetChanged();
        }
    }


    private class DeleteDialogListener implements AlertDialogMessage.AlertListener {
        Employee employee;
        DeleteDialogListener(Employee employee) {
            this.employee = employee;
        }
        @Override
        public void onDialogYesClick(String id) {
            deleteRecord(employee);
        }
        @Override
        public void onDialogNoClick(String id) {

        }
    }
}
