package com.plan.ahead;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TimeUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.plan.ahead.Classes.CustomDialog;
import com.plan.ahead.Storage.StorageUtils;
import com.plan.ahead.Utilities.DateStringUtils;
import com.plan.ahead.Utilities.Scheduler;
import com.plan.ahead.Utilities.TimeStringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Created by mihai on 27.02.2018.
 */

public class MyScheduleFragment extends Fragment {

    WeekView mWeekView;
    Toolbar tb;
    FloatingActionButton fabAddEvent;
    List<WeekViewEvent> events;
    int currentYear, currentMonth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the routine_item for this fragment
        return inflater.inflate(R.layout.fragment_my_schedule, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Calendar currentTime = Calendar.getInstance();
        currentYear = currentTime.getTime().getYear();
        currentMonth = currentTime.getTime().getMonth();

        events = StorageUtils.loadEvents();

        tb = (Toolbar)getActivity().findViewById(R.id.toolbar);
        fabAddEvent = (FloatingActionButton)getActivity().findViewById(R.id.fabAddEvent);

        fabAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewEvent(view);
            }
        });

        // Get a reference for the week view in the routine_item.
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
                tb.setSubtitle(getResources().getString(R.string.year) + newYear + " " + getResources().getString(R.string.month) + (newMonth - 1));
                currentYear = newYear;
                currentMonth = newMonth;

                // Populate the week view with some events.
                return StorageUtils.loadEvents();
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
        final CustomDialog dialog = new CustomDialog(getContext());
        dialog.setContentView(R.layout.dialog_add_event);
        dialog.setTitle("Add new event");

        // set the custom dialog components - text, image and button
        final EditText eventNameBox = (EditText) dialog.findViewById(R.id.eventNameBox);
        final Button location = (Button) dialog.findViewById(R.id.btnLocation);
        final Button startTimeSpinner = (Button) dialog.findViewById(R.id.spinnerStartHour);
        final Button stopTimeSpinner = (Button) dialog.findViewById(R.id.spinnerStopHour);
        final Button startDateSpinner = (Button) dialog.findViewById(R.id.spinnerStartDate);
        final Button stopDateSpinner = (Button) dialog.findViewById(R.id.spinnerStopDate);
        final Button confirmBtn = (Button)dialog.findViewById(R.id.confirmBtn);

        location.setVisibility(View.GONE);

        startTimeSpinner.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                CustomDialog.pickTime(view, getActivity());
            }
        });

        stopTimeSpinner.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                CustomDialog.pickTime(view, getActivity());
            }
        });

        startDateSpinner.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                CustomDialog.pickDate(view, getActivity());
            }
        });

        stopDateSpinner.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                CustomDialog.pickDate(view, getActivity());
            }
        });


        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean valid = true;

                String t = startTimeSpinner.getText().toString();
                String t2 = stopTimeSpinner.getText().toString();
                String d = startDateSpinner.getText().toString();
                String d2 = stopDateSpinner.getText().toString();

                if(!t.isEmpty() && !t2.isEmpty())
                {
                    if(TimeStringUtils.timeCompare(t, t2) != 1) {
                        valid = false;
                        Toast.makeText(getActivity().getApplicationContext(), "End time must be greater than start time", Toast.LENGTH_LONG).show();
                    }
                    if(DateStringUtils.dateCompare(d, d2) == -1) {
                        valid = false;
                        Toast.makeText(getActivity().getApplicationContext(), "End date must be greater than start date", Toast.LENGTH_LONG).show();
                    }
                    if(eventNameBox.getText().toString().isEmpty()) {
                        valid = false;
                        Toast.makeText(getActivity().getApplicationContext(), "You must enter an event name", Toast.LENGTH_LONG).show();
                    }

                    if(valid)
                    {
                        Calendar startTime = Calendar.getInstance();
                        String startDateS = startDateSpinner.getText().toString();
                        String startTimeS = startTimeSpinner.getText().toString();
                        startTime.set(DateStringUtils.getYear(startDateS), DateStringUtils.getMonth(startDateS), DateStringUtils.getDay(startDateS), TimeStringUtils.getHour(startTimeS), TimeStringUtils.getMinutes(startTimeS));

                        Calendar endTime = Calendar.getInstance();
                        String stopDateS = stopDateSpinner.getText().toString();
                        String stopTimeS = stopTimeSpinner.getText().toString();
                        endTime.set(DateStringUtils.getYear(stopDateS), DateStringUtils.getMonth(stopDateS), DateStringUtils.getDay(stopDateS), TimeStringUtils.getHour(stopTimeS), TimeStringUtils.getMinutes(stopTimeS));

                        //Toast.makeText(getActivity().getApplicationContext(), String.valueOf(startTime.getTimeInMillis()), Toast.LENGTH_LONG).show();

                        WeekViewEvent event = new WeekViewEvent(Scheduler.getAvailableId(), eventNameBox.getText().toString(), startTime, endTime);
                        StorageUtils.addEventAndSave(event);
                        dialog.cancel();

                        // refresh the fragment
                        Fragment frg = getActivity().getSupportFragmentManager().findFragmentById(R.id.main_fragment);
                        final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.detach(frg);
                        ft.attach(frg);
                        ft.commit();
                    }
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
    }
}
