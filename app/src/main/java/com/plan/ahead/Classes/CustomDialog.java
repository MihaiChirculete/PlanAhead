package com.plan.ahead.Classes;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.plan.ahead.R;

/**
 * Created by Andrey on 16/05/2018.
 */

public class CustomDialog extends Dialog {

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    static public void pickTime(final View v, Activity activity)
    {
        // custom dialog
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_time_picker);
        dialog.setTitle("Pick a time");

        final TimePicker tp = dialog.findViewById(R.id.timePicker);

        Button confirmBtn = dialog.findViewById(R.id.confirmBtn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Button)v).setText(tp.getCurrentHour() + ":" + tp.getCurrentMinute());
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    static public void pickDate(final View v, Activity activity)
    {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_date_picker);
        dialog.setTitle("Pick a date");

        final DatePicker dp = dialog.findViewById(R.id.datePicker);

        Button confirmBtn = dialog.findViewById(R.id.confirmBtn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Button)v).setText(dp.getDayOfMonth() + "/" + dp.getMonth() + "/" + dp.getYear());
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
