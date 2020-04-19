package com.github.androidproject;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Hero> values; //liste des héros
    private List<List<HeroInfo>> HIValues; //liste des infos sur les héros.

    //Constructor
    ListAdapter(List<Hero> myDataset, List<List<HeroInfo>> listHeroInfoList) { //constructor
        values = myDataset;
        HIValues = listHeroInfoList;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView txtHeader;
        TextView txtFooter;
        View layout;
        ImageView imageView;
        String url = "https://assets.epicsevendb.com/_source/face/c1096_s.png";

        ViewHolder(View v) {
            //code can avoid the time-consuming findViewById() method to update the widgets with new data
            super(v);
            layout = v;
            txtHeader = v.findViewById(R.id.firstLine);
            txtFooter = v.findViewById(R.id.secondLine);
            imageView = v.findViewById(R.id.icon);
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

        return new ViewHolder(v);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Hero currentHero = values.get(position);
        //final List<HeroInfo> currentHeroInfo = HIValues.get(position);

        //System.out.println(currentHero.getName());
        holder.txtHeader.setText(currentHero.getName());
        holder.txtFooter.setText(currentHero.getRarity().toString() + "★    " + currentHero.getAttribute() + "     " + currentHero.getClassRole());

       // for(int i=0; i<HIValues.size(); i++) {
       //         System.out.println(HIValues.get(i).get(0).getName());
       // }

       for(int i=0; i<HIValues.size(); i++){
           System.out.println(currentHero.get_id().toString() + "    " + HIValues.get(i).get(0).get_id().toString());
            if(currentHero.get_id().toString().equals(HIValues.get(i).get(0).get_id().toString())){
                Picasso.get().load(HIValues.get(i).get(0).getAssets().getIcon().toString()).into(holder.imageView);
            }
        }
    /*
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
    */
    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
       // System.out.println("number of item : "+ values.size()); //to Test LogCat display
        return values.size();
    } //the adapter return the total number of items list

}