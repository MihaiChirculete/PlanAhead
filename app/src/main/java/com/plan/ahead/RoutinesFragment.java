package com.plan.ahead;

import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TimePicker;

import com.plan.ahead.Classes.CustomDialog;

/**
 * Created by Madalina on 3/28/2018.
 */

public class RoutinesFragment extends android.support.v4.app.Fragment {
    Toolbar tb;
    FloatingActionButton fabAddEvent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_routines, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        tb = (Toolbar)getActivity().findViewById(R.id.toolbar);
        tb.setSubtitle("Rutine");

        fabAddEvent = (FloatingActionButton)getActivity().findViewById(R.id.fabAddRoutine);

        fabAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewRoutine(view);
            }
        });

        super.onActivityCreated(savedInstanceState);
    }

    void onButtonClick(View v, CustomDialog dialog, CheckBox[] checks, int clickedButton) {
        RelativeLayout dailyLayout = (RelativeLayout) dialog.findViewById(R.id.dailyLayout);
        LinearLayout weeklyLayout = (LinearLayout) dialog.findViewById(R.id.weeklyLayout);

        RelativeLayout daysLayouts[] = new RelativeLayout[8];
        daysLayouts[0] = (RelativeLayout) dialog.findViewById(R.id.mondayLayout);
        daysLayouts[1] = (RelativeLayout) dialog.findViewById(R.id.tuesdayLayout);
        daysLayouts[2] = (RelativeLayout) dialog.findViewById(R.id.wednesdayLayout);
        daysLayouts[3] = (RelativeLayout) dialog.findViewById(R.id.thursdayLayout);
        daysLayouts[4] = (RelativeLayout) dialog.findViewById(R.id.fridayLayout);
        daysLayouts[5] = (RelativeLayout) dialog.findViewById(R.id.saturdayLayout);
        daysLayouts[6] = (RelativeLayout) dialog.findViewById(R.id.sundayLayout);
        daysLayouts[7] = (RelativeLayout) dialog.findViewById(R.id.sameTimetableLayout);

        if(clickedButton == 0)
        {
            if(checks[0].isChecked()) {
                dailyLayout.setVisibility(View.VISIBLE);
                checks[1].setChecked(false);
                weeklyLayout.setVisibility(View.GONE);
            }
            else
                dailyLayout.setVisibility((View.GONE));
        }
        else if(clickedButton == 1){
            if(checks[1].isChecked())
            {
                checks[0].setChecked(false);
                dailyLayout.setVisibility(View.GONE);
                weeklyLayout.setVisibility(View.VISIBLE);
            }
            else
                weeklyLayout.setVisibility((View.GONE));
        }
        else if(clickedButton < 9) {
            if(checks[clickedButton].isChecked()) {
                daysLayouts[clickedButton - 2].setVisibility(View.VISIBLE);
                checks[9].setChecked(false);
                daysLayouts[7].setVisibility(View.GONE);
            }
            else
                daysLayouts[clickedButton - 2].setVisibility(View.GONE);
        }else {
            for(int i = 2; i < 9; i++) {
                checks[i].setChecked(false);
                daysLayouts[i - 2].setVisibility(View.GONE);
            }
            daysLayouts[7].setVisibility(View.VISIBLE);
        }

    }

    void addNewRoutine(View v)
    {
        // custom dialog
        final CustomDialog dialog = new CustomDialog(getActivity());
        dialog.setContentView(R.layout.dialog_add_routine);
        dialog.setTitle("Add new routine");

        // set the custom dialog components - text, image and button

        final Button[] timeSpinners = new Button[18];
        timeSpinners[0] = (Button) dialog.findViewById(R.id.spinnerDailyStartHour);
        timeSpinners[1] = (Button) dialog.findViewById(R.id.spinnerDailyEndHour);
        timeSpinners[2] = (Button) dialog.findViewById(R.id.spinnerMondayStartHour);
        timeSpinners[3] = (Button) dialog.findViewById(R.id.spinnerMondayEndHour);
        timeSpinners[4] = (Button) dialog.findViewById(R.id.spinnerTuesdayStartHour);
        timeSpinners[5] = (Button) dialog.findViewById(R.id.spinnerTuesdayEndHour);
        timeSpinners[6] = (Button) dialog.findViewById(R.id.spinnerWednesdayStartHour);
        timeSpinners[7] = (Button) dialog.findViewById(R.id.spinnerWednesdayEndHour);
        timeSpinners[8] = (Button) dialog.findViewById(R.id.spinnerThursdayStartHour);
        timeSpinners[9] = (Button) dialog.findViewById(R.id.spinnerThursdayEndHour);
        timeSpinners[10] = (Button) dialog.findViewById(R.id.spinnerFridayStartHour);
        timeSpinners[11] = (Button) dialog.findViewById(R.id.spinnerFridayEndHour);
        timeSpinners[12] = (Button) dialog.findViewById(R.id.spinnerSaturdayStartHour);
        timeSpinners[13] = (Button) dialog.findViewById(R.id.spinnerSaturdayEndHour);
        timeSpinners[14] = (Button) dialog.findViewById(R.id.spinnerSundayStartHour);
        timeSpinners[15] = (Button) dialog.findViewById(R.id.spinnerSundayEndHour);
        timeSpinners[16] = (Button) dialog.findViewById(R.id.spinnerSameTimetableStartHour);
        timeSpinners[17] = (Button) dialog.findViewById(R.id.spinnerSameTimetableEndHour);

        final CheckBox[] checks = new CheckBox[10];
        checks[0] = (CheckBox) dialog.findViewById(R.id.dailyCheckBox);
        checks[1] = (CheckBox) dialog.findViewById(R.id.weeklyCheckBox);
        checks[2] = (CheckBox) dialog.findViewById(R.id.mondayCheckBox);
        checks[3] = (CheckBox) dialog.findViewById(R.id.tuesdayCheckBox);
        checks[4] = (CheckBox) dialog.findViewById(R.id.wednesdayCheckBox);
        checks[5] = (CheckBox) dialog.findViewById(R.id.thursdayCheckBox);
        checks[6] = (CheckBox) dialog.findViewById(R.id.fridayCheckBox);
        checks[7] = (CheckBox) dialog.findViewById(R.id.saturdayCheckBox);
        checks[8] = (CheckBox) dialog.findViewById(R.id.sundayCheckBox);
        checks[9] = (CheckBox) dialog.findViewById(R.id.sameTimetableCheckBox);



//        dialog.findViewById(R.id.dailyCheckBox).setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                onEveryDayClick(view, dialog);
//            }
//        });
//        dialog.findViewById(R.id.weeklyCheckBox).setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                onEveryWeekClick(view, dialog);
//            }
//        });

        for(Button b:timeSpinners)
        {
            b.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    CustomDialog.pickTime(view, getActivity());
                }
            });
        }

        dialog.findViewById(R.id.confirmBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add
            }
        });

        checks[0].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onButtonClick(view, dialog, checks, 0);
            }
        });
        checks[1].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onButtonClick(view, dialog, checks, 1);
            }
        });
        checks[2].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onButtonClick(view, dialog, checks, 2);
            }
        });
        checks[3].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onButtonClick(view, dialog, checks, 3);
            }
        });
        checks[4].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onButtonClick(view, dialog, checks, 4);
            }
        });
        checks[5].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onButtonClick(view, dialog, checks, 5);
            }
        });
        checks[6].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onButtonClick(view, dialog, checks, 6);
            }
        });
        checks[7].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onButtonClick(view, dialog, checks, 7);
            }
        });
        checks[8].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onButtonClick(view, dialog, checks, 8);
            }
        });
        checks[9].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onButtonClick(view, dialog, checks, 9);
            }
        });

        dialog.show();
    }

}
