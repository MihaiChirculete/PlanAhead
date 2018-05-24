package com.plan.ahead.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.plan.ahead.Data.RoutineEntryDaily;
import com.plan.ahead.R;

import java.util.List;

/**
 * Created by Madalina on 5/24/2018.
 */

public class RoutineDailyAdapter extends RecyclerView.Adapter<RoutineDailyAdapter.ViewHolder> {

    private List<RoutineEntryDaily> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title, intervals;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.routineTitle);
            intervals = (TextView) v.findViewById(R.id.routineFreqDaily);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RoutineDailyAdapter(List<RoutineEntryDaily> myDataset) {
        mDataset = myDataset;
    }

    public RoutineDailyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.routine_item, parent, false);

        RoutineDailyAdapter.ViewHolder vh = new RoutineDailyAdapter.ViewHolder(itemView);
        return vh;
    }

    // Replace the contents of a view (invoked by the routine_item manager)
    @Override
    public void onBindViewHolder(RoutineDailyAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.title.setText(mDataset.get(position).getRoutineName());
        holder.intervals.setText(mDataset.get(position).getIntervals()[0].getStartTime() + " " + mDataset.get(position).getIntervals()[0].getFinishTime());
    }

    // Return the size of your dataset (invoked by the routine_item manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}