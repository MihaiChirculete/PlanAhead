package com.plan.ahead.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.plan.ahead.Data.RoutineEntryDaily;
import com.plan.ahead.Data.RoutineEntryWeekly;
import com.plan.ahead.R;

import java.util.List;

/**
 * Created by Madalina on 5/24/2018.
 */

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.ViewHolder> {

    private List<Object> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title, intervals, days;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.routineTitle);
            intervals = (TextView) v.findViewById(R.id.routineFreqDaily);
            days = (TextView) v.findViewById(R.id.routineFreqWeeklyDH);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RoutineAdapter(List<Object> myDataset) {
        mDataset = myDataset;
    }

    public RoutineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.routine_item, parent, false);

        RoutineAdapter.ViewHolder vh = new RoutineAdapter.ViewHolder(itemView);
        return vh;
    }

    // Replace the contents of a view (invoked by the routine_item manager)
    @Override
    public void onBindViewHolder(RoutineAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        Object obj = mDataset.get(position);

        if(obj instanceof RoutineEntryDaily)
        {
            holder.title.setText(((RoutineEntryDaily)obj).getRoutineName());
            holder.intervals.setText(((RoutineEntryDaily)obj).getIntervals()[0].getStartTime() + " " +((RoutineEntryDaily)obj).getIntervals()[0].getFinishTime());
            holder.intervals.setVisibility(View.VISIBLE);
        }
        if(obj instanceof RoutineEntryWeekly)
        {
            holder.title.setText(((RoutineEntryWeekly)obj).getRoutineName());
            holder.days.setText(((RoutineEntryWeekly)obj).getIntervals()[0].getStartTime() + " " +((RoutineEntryDaily)obj).getIntervals()[0].getFinishTime());
            holder.days.setVisibility(View.VISIBLE);
        }
    }

    // Return the size of your dataset (invoked by the routine_item manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}