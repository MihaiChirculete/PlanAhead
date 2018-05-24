package com.plan.ahead.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.plan.ahead.Data.ToDoEntry;
import com.plan.ahead.R;

import java.util.List;

/**
 * Created by mihai on 28.03.2018.
 */

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {
    private List<ToDoEntry> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title, description, time;

        public ViewHolder(View v) {
            super(v);
            title = (TextView)v.findViewById(R.id.title);
            description = (TextView)v.findViewById(R.id.description);
            time = (TextView)v.findViewById(R.id.time);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ToDoAdapter(List<ToDoEntry> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the routine_item manager)
    @Override
    public ToDoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.to_do_list_item, parent, false);

        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    // Replace the contents of a view (invoked by the routine_item manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.title.setText(mDataset.get(position).getEntryTitle());
        holder.description.setText(mDataset.get(position).getEntryDescription());
        holder.time.setText(mDataset.get(position).getEntryTimeInterval());

    }

    // Return the size of your dataset (invoked by the routine_item manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
