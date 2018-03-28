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

    void addNewReminder(View v)
    {
        // custom dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_add_reminder);
        dialog.setTitle("Add new event");

        // set the custom dialog components - text, image and button
        final Button startTimeSpinner = (Button) dialog.findViewById(R.id.spinnerStartHour);
        final Button stopTimeSpinner = (Button) dialog.findViewById(R.id.spinnerStopHour);
        final Button confirmBtn = (Button)dialog.findViewById(R.id.confirmBtn);

        startTimeSpinner.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                pickTime(view);
            }
        });

        stopTimeSpinner.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                pickTime(view);
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t = startTimeSpinner.getText().toString();
                String t2 = stopTimeSpinner.getText().toString();
                if(!t.isEmpty() && !t2.isEmpty())
                {
                    if(TimeStringUtils.timeCompare(t, t2) != 1)
                        Toast.makeText(getActivity().getApplicationContext(), "End time must be greater than start time", Toast.LENGTH_LONG).show();
                    // else add the event
                }
                else
                    Toast.makeText(getActivity().getApplicationContext(), "Interval not set!", Toast.LENGTH_LONG).show();
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