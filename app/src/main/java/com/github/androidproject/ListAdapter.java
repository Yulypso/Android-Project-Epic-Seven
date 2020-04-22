package com.github.androidproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Hero> values; //liste des héros
    private List<HeroInfo> HIValues; //liste des infos sur les héros.
    private Hero currentHero;
    private HeroInfo currentHeroInfo;


    public static final String EXTRA_TEXT_NAME = "com.github.androidproject.EXTRA_TEXT_NAME";
    public static final String EXTRA_TEXT_IMAGE = "com.github.androidproject.EXTRA_TEXT_IMAGE";
    public static final String EXTRA_TEXT_FULL_IMAGE = "com.github.androidproject.EXTRA_TEXT_FULL_IMAGE";


    ListAdapter(List<Hero> myDataset, List<HeroInfo> heroInfoList) { //constructor
        values = myDataset;
        HIValues = heroInfoList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtHeader;
        TextView txtFooter;
        View layout;
        ImageView imageView;
        LinearLayout linearLayout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = v.findViewById(R.id.firstLine);
            txtFooter = v.findViewById(R.id.secondLine);
            imageView = v.findViewById(R.id.icon);
            linearLayout = v.findViewById(R.id.linearLayout);
        }
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        currentHero = values.get(position);
        holder.txtHeader.setText(currentHero.getName());
        holder.txtFooter.setText(currentHero.getRarity().toString() + "★    " + currentHero.getAttribute() + "     " + currentHero.getClassRole());

        addIcon(currentHero, holder);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("position", ""+ position);
                currentHero = values.get(position);
                currentHeroInfo = searchHeroInfoById(currentHero);
                Log.d("HeroInfo", ""+ currentHeroInfo.get_id());

                openActivityInformation(v, currentHero, currentHeroInfo);
            }
        });
    }

    public HeroInfo searchHeroInfoById(Hero hero){
        for(HeroInfo hi : HIValues) {
            if(hi.get_id().equals(hero.get_id())){
                return hi;
            }
        }
        return null;
    }

    public void addIcon(Hero currentHero, ViewHolder holder){

        boolean imageFound = false;
        if(HIValues != null){
            for(int i=0; i<HIValues.size(); i++){
                if(currentHero.get_id().equals(HIValues.get(i).get_id())){
                    Picasso.get().load(HIValues.get(i).getAssets().getIcon()).into(holder.imageView);
                    imageFound = true;
                }

                if(!currentHero.get_id().equals(HIValues.get(i).get_id()) && i == HIValues.size()-1 && !imageFound){
                    Picasso.get().load("https://www.app.asso.fr/wp-content/uploads/2018/04/informer-140x140.png").into(holder.imageView);
                    imageFound = false;
                }
            }
        }
    }

    public void openActivityInformation(View view, Hero hero, HeroInfo heroInfo){
        Intent intent = new Intent(view.getContext(), ActivityInformation.class);

        /**HeroInfo to add on the second Activity**/
        String name = hero.getName();
        intent.putExtra(EXTRA_TEXT_NAME, name);

        String imageUrl = heroInfo.getAssets().getImage();
        intent.putExtra(EXTRA_TEXT_IMAGE, imageUrl);

        String imageFullUrl = (hero.getModelURL());
        intent.putExtra(EXTRA_TEXT_FULL_IMAGE, imageFullUrl);


        /******************************************/
        view.getContext().startActivity(intent);
    }

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

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        // System.out.println("number of item : "+ values.size()); //to Test LogCat display
        return values.size();
    } //the adapter return the total number of items list

}