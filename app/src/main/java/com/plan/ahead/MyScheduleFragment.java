package com.plan.ahead;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TimeUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.plan.ahead.Storage.StorageUtils;
import com.plan.ahead.Utilities.TimeStringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by mihai on 27.02.2018.
 */

public class MyScheduleFragment extends Fragment {

    WeekView mWeekView;
    Toolbar tb;
    FloatingActionButton fabAddEvent;
    List<WeekViewEvent> events;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_schedule, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        events = StorageUtils.loadEvents();

        tb = (Toolbar)getActivity().findViewById(R.id.toolbar);
        fabAddEvent = (FloatingActionButton)getActivity().findViewById(R.id.fabAddEvent);

        fabAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewEvent(view);
            }
        });

        // Get a reference for the week view in the layout.
        mWeekView = (WeekView) getActivity().findViewById(R.id.weekView);

        // Set an action when any event is clicked.
        mWeekView.setOnEventClickListener(new WeekView.EventClickListener() {
            @Override
            public void onEventClick(WeekViewEvent event, RectF eventRect) {
                Toast.makeText(getActivity().getApplicationContext(), event.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        MonthLoader.MonthChangeListener mMonthChangeListener = new MonthLoader.MonthChangeListener() {
            @Override
            public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {
                tb.setSubtitle(getResources().getString(R.string.year) + newYear + getResources().getString(R.string.month) + newMonth);

                // Populate the week view with some events.
                events = new ArrayList<WeekViewEvent>();
                /*
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, 3);
                startTime.set(Calendar.MINUTE, 0);
                startTime.set(Calendar.MONTH, newMonth-1);
                startTime.set(Calendar.YEAR, newYear);
                Calendar endTime = (Calendar) startTime.clone();
                endTime.add(Calendar.HOUR, 1);
                endTime.set(Calendar.MONTH, newMonth-1);
                WeekViewEvent event = new WeekViewEvent(1, "Test event", startTime, endTime);
                event.setColor(getResources().getColor(R.color.eventColor01));
                events.add(event);
                */


                return events;


                //return StorageUtils.loadEvents();
            }
        };

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(mMonthChangeListener);

        // Set long press listener for events.
        mWeekView.setEventLongPressListener(new WeekView.EventLongPressListener() {
            @Override
            public void onEventLongPress(WeekViewEvent event, RectF eventRect) {

            }
        });
    }


    void addNewEvent(View v)
    {
        // custom dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_add_event);
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

    @Override
    public void onDestroy() {
        super.onDestroy();

        StorageUtils.saveEvents(events);
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
