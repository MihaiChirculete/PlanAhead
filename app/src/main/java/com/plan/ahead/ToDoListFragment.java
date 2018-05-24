package com.plan.ahead;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.plan.ahead.Adapters.ToDoAdapter;
import com.plan.ahead.Data.ToDoEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 19/03/2018.
 */

public class ToDoListFragment extends Fragment {
    private Toolbar tb;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<ToDoEntry> entries;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_to_do_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        entries = new ArrayList<ToDoEntry>();
        ToDoEntry e = new ToDoEntry();
        e.setEntryTitle("Test");
        entries.add(e);

        tb = (Toolbar)getActivity().findViewById(R.id.toolbar);
        tb.setSubtitle(getResources().getString(R.string.nav_to_do_list));

        recyclerView = (RecyclerView)getActivity().findViewById(R.id.listRecyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the routine_item size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear routine_item manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        // specify an adapter (see also next example)
        adapter = new ToDoAdapter(entries);

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }
}
