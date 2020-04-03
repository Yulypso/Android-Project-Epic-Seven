package com.github.androidproject;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Hero> values; //load in this variable the list from the constructor

    //Constructor
    ListAdapter(List<Hero> myDataset) { //constructor
        values = myDataset;
    }



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView txtHeader;
        TextView txtFooter;
        View layout;

        ViewHolder(View v) {
            //code can avoid the time-consuming findViewById() method to update the widgets with new data
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
        }
    }



    //method of ListAdapter
    public void add(int position, Hero item) {
        values.add(position, item);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, values.size());
    }

    private void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, values.size());
    }




    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //It is called whenever we need to show a new view.
        //1 call per List Elements/entry

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //view which has been hidden and needed to be animated on their way out

        View v = inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters from XML row_layout

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Hero currentHero = values.get(position);

        holder.txtHeader.setText(currentHero.getName());
        holder.txtFooter.setText(currentHero.getRarity().toString());


        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //each time clicked on txtHeader (not footer) we remove an element
                remove(position);
            }
        });

        holder.txtFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                //each time clicked on txtFooter we add a new element
                add(position, currentHero);
            }
        });
    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        System.out.println("number of item : "+ values.size()); //to Test LogCat display
        return values.size();
    } //the adapter return the total number of items list

}