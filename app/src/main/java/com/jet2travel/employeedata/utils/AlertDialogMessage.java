package com.jet2travel.employeedata.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.jet2travel.employeedata.R;

public class AlertDialogMessage {

    public interface AlertListener {
        /**
         * Used to get the click event of Yes button Of Alert dialog.
         */
        void onDialogYesClick(String id);

        /**
         * Used to get the click event of No button Of Alert dialog.
         */
        void onDialogNoClick(String id);
    }

    public static void showAlertDialogWithButtons(Activity activity, final String title, String msg, final AlertListener alertListener) {
        final Dialog dialogOkayCancel;
        dialogOkayCancel = new Dialog(activity);
        dialogOkayCancel.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogOkayCancel.setContentView(R.layout.dialog_custom_yes_no);
        dialogOkayCancel.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button dialogButtonYes = (Button) dialogOkayCancel.findViewById(R.id.btn_dialog_confirm);
        TextView dialogButtonNo = (TextView) dialogOkayCancel.findViewById(R.id.txt_disagree);
        TextView tvMsg = (TextView) dialogOkayCancel.findViewById(R.id.txt_dialog_alert_message);
        TextView tvTitle = (TextView) dialogOkayCancel.findViewById(R.id.txt_dialog_title);
        dialogButtonNo.setPaintFlags(dialogButtonNo.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvTitle.setText(title);
        tvMsg.setText(msg);
        dialogButtonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogOkayCancel.dismiss();
                if (alertListener != null) {
                    alertListener.onDialogYesClick("");
                }
            }
        });
        dialogButtonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogOkayCancel.dismiss();
                if (alertListener != null) {
                    alertListener.onDialogNoClick("");
                }
            }
        });
        dialogOkayCancel.show();
    }

}
