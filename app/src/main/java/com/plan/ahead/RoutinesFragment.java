package com.plan.ahead;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Madalina on 3/28/2018.
 */

public class RoutinesFragment extends Fragment {

    Toolbar tb;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        tb = (Toolbar)getActivity().findViewById(R.id.toolbar);
        tb.setSubtitle("Rutine");

        return inflater.inflate(R.layout.fragment_routines, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
