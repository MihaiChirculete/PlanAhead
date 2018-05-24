package com.plan.ahead.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.plan.ahead.Data.MementoEntryWDate;
import com.plan.ahead.Data.MementoEntryWeekly;
import com.plan.ahead.R;

import java.util.List;

/**
 * Created by Madalina on 5/24/2018.
 */

public class MementoWeeklyAdapter extends RecyclerView.Adapter<MementoWeeklyAdapter.ViewHolder> {

    private List<MementoEntryWeekly> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title, time, days;

        public ViewHolder(View v) {
            super(v);
            title = (TextView)v.findViewById(R.id.mementoTitle);
            time = (TextView)v.findViewById(R.id.mementoRemindAt);
            days = (TextView) v.findViewById(R.id.mementoFreq);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MementoWeeklyAdapter(List<MementoEntryWeekly> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the routine_item manager)
    @Override
    public MementoWeeklyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.memento_item, parent, false);

        MementoWeeklyAdapter.ViewHolder vh = new MementoWeeklyAdapter.ViewHolder(itemView);
        return vh;
    }

    // Replace the contents of a view (invoked by the routine_item manager)
    @Override
    public void onBindViewHolder(MementoWeeklyAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.title.setText(mDataset.get(position).getMemName());
        holder.time.setText(mDataset.get(position).getMemRemindHour());

        boolean[] daysB = mDataset.get(position).getDays();

        String daysText = new String();

        for(int i = 0; i < daysB.length; i++){
            if(daysB[i])
                switch (i){
                    case 0: daysText += "Mon ";
                            break;
                    case 1: daysText += "Tue ";
                        break;
                    case 2: daysText += "Wed ";
                        break;
                    case 3: daysText += "Thu ";
                        break;
                    case 4: daysText += "Fri ";
                        break;
                    case 5: daysText += "Sat ";
                        break;
                    case 6: daysText += "Sun ";
                        break;
                    default: daysText += "";
                }
        }
        holder.days.setText(daysText);
    }

    // Return the size of your dataset (invoked by the routine_item manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
