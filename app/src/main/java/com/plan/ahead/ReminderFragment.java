package com.plan.ahead;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.plan.ahead.Utilities.TimeStringUtils;

/**
 * Created by Andrey on 28/03/2018.
 */

public class ReminderFragment extends Fragment {
    Toolbar tb;
    FloatingActionButton fabAddEvent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reminder, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        tb = (Toolbar)getActivity().findViewById(R.id.toolbar);
        tb.setSubtitle("Memento");

        fabAddEvent = (FloatingActionButton)getActivity().findViewById(R.id.fabAddReminder);

        fabAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewReminder(view);
            }
        });

        super.onActivityCreated(savedInstanceState);
    }

    void onDateClick(View v, Dialog dialog) {

        CheckBox date = (CheckBox) dialog.findViewById(R.id.dateCheckBox);
        Spinner datePicker = (Spinner) dialog.findViewById(R.id.spinnerStartDate);
        CheckBox everyDay = (CheckBox) dialog.findViewById(R.id.everyDayCheckBox);
        CheckBox everyWeek = (CheckBox) dialog.findViewById(R.id.everyWeekCheckBox);
        LinearLayout daysLayout = (LinearLayout) dialog.findViewById(R.id.daysCheckboxes);

        if(date.isChecked())
        {
            everyDay.setChecked(false);
            everyWeek.setChecked(false);
            daysLayout.setVisibility(View.GONE);
            datePicker.setVisibility(View.VISIBLE);
        }
        else
            datePicker.setVisibility(View.VISIBLE);

    }
    void onEveryDayClick(View v, Dialog dialog) {

        CheckBox date = (CheckBox) dialog.findViewById(R.id.dateCheckBox);
        Spinner datePicker = (Spinner) dialog.findViewById(R.id.spinnerStartDate);
        CheckBox everyDay = (CheckBox) dialog.findViewById(R.id.everyDayCheckBox);
        CheckBox everyWeek = (CheckBox) dialog.findViewById(R.id.everyWeekCheckBox);
        LinearLayout daysLayout = (LinearLayout) dialog.findViewById(R.id.daysCheckboxes);

        if(everyDay.isChecked())
        {
            date.setChecked(false);
            everyWeek.setChecked(false);
            daysLayout.setVisibility(View.GONE);
            datePicker.setVisibility(View.GONE);
        }
    }

    void onEveryWeekClick(View v, Dialog dialog) {
        CheckBox date = (CheckBox) dialog.findViewById(R.id.dateCheckBox);
        Spinner datePicker = (Spinner) dialog.findViewById(R.id.spinnerStartDate);
        CheckBox everyDay = (CheckBox) dialog.findViewById(R.id.everyDayCheckBox);
        CheckBox everyWeek = (CheckBox) dialog.findViewById(R.id.everyWeekCheckBox);
        LinearLayout daysLayout = (LinearLayout) dialog.findViewById(R.id.daysCheckboxes);

        if(everyWeek.isChecked())
        {
            everyDay.setChecked(false);
            date.setChecked(false);
            daysLayout.setVisibility(View.VISIBLE);
            datePicker.setVisibility(View.GONE);
        }
        else
            daysLayout.setVisibility(View.GONE);
    }

    void addNewReminder(View v)
    {
        // custom dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_add_reminder);
        dialog.setTitle("Add new event");

        // set the custom dialog components - text, image and button
        final Button startTimeSpinner = (Button) dialog.findViewById(R.id.spinnerStartHour);

        final Button confirmBtn = (Button)dialog.findViewById(R.id.confirmBtn);

        dialog.findViewById(R.id.dateCheckBox).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onDateClick(view, dialog);
            }
        });
        dialog.findViewById(R.id.everyDayCheckBox).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onEveryDayClick(view, dialog);
            }
        });
        dialog.findViewById(R.id.everyWeekCheckBox).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onEveryWeekClick(view, dialog);
            }
        });

        startTimeSpinner.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                pickTime(view);
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add
            }
        });

        dialog.show();
    }

    private void pickTime(final View v)
    {
        // custom dialog
        final Dialog dialog = new Dialog(getActivity());
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
}