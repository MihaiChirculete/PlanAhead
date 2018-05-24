package com.plan.ahead;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.plan.ahead.Classes.CustomDialog;

/**
 * Created by Andrey on 28/03/2018.
 */

public class ReminderFragment extends Fragment {
    Toolbar tb;
    FloatingActionButton fabAddEvent;
    RecyclerView rView;

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

    void onButtonClick(View v, CustomDialog dialog, CheckBox[] buttons, int clickedButton) {

        Button datePicker = (Button) dialog.findViewById(R.id.spinnerStartDate);
        LinearLayout daysLayout = (LinearLayout) dialog.findViewById(R.id.daysCheckboxes);

        if(clickedButton == 0)
        {
            buttons[1].setChecked(false);
            buttons[2].setChecked(false);
            daysLayout.setVisibility(View.GONE);
            datePicker.setVisibility(View.VISIBLE);
        }
        else if(clickedButton == 1)
        {
            buttons[0].setChecked(false);
            buttons[2].setChecked(false);
            daysLayout.setVisibility(View.GONE);
            datePicker.setVisibility(View.GONE);
        }
        else if(clickedButton == 2)
        {
            buttons[0].setChecked(false);
            buttons[1].setChecked(false);
            daysLayout.setVisibility(View.VISIBLE);
            datePicker.setVisibility(View.GONE);
        }
        if(!buttons[clickedButton].isChecked())
        {
            datePicker.setVisibility(View.GONE);
            daysLayout.setVisibility(View.GONE);
        }
    }

    void addNewReminder(View v)
    {
        // custom dialog
        final CustomDialog dialog = new CustomDialog(getContext());
        dialog.setContentView(R.layout.dialog_add_reminder);
        dialog.setTitle("Add new event");

        // set the custom dialog components - text, image and button

        final CheckBox buttons[] = new CheckBox[3];

        buttons[0] = dialog.findViewById(R.id.dateCheckBox);
        buttons[1] = dialog.findViewById(R.id.dailyCheckBox);
        buttons[2] = dialog.findViewById(R.id.weeklyCheckBox);

        buttons[0].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onButtonClick(view, dialog, buttons, 0);
            }
        });
        buttons[1].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onButtonClick(view, dialog, buttons, 1);
            }
        });
        buttons[2].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onButtonClick(view, dialog, buttons, 2);
            }
        });
        dialog.findViewById(R.id.spinnerStartDate).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                CustomDialog.pickDate(view, getActivity());
            }
        });

        dialog.findViewById(R.id.spinnerStartHour).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                CustomDialog.pickTime(view, getActivity());
            }
        });

        dialog.findViewById(R.id.confirmBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add
            }
        });

        dialog.show();
    }
}