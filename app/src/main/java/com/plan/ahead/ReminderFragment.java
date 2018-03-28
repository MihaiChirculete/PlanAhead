package com.plan.ahead;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Andrey on 28/03/2018.
 */

public class ReminderFragment extends Fragment {
    Toolbar tb;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        tb = (Toolbar)getActivity().findViewById(R.id.toolbar);
        tb.setSubtitle("Memento");

        return inflater.inflate(R.layout.fragment_reminder, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}