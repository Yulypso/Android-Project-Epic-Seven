package com.github.androidproject.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.androidproject.models.Hero;
import com.github.androidproject.models.HeroInfo;
import com.github.androidproject.R;
import com.github.androidproject.activites.ActivityInformation;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

    private List<Hero> values; //liste des héros
    private List<HeroInfo> HIValues; //liste des infos sur les héros.
    private Hero currentHero;
    private HeroInfo currentHeroInfo;


    public static final String EXTRA_TEXT_NAME = "com.github.androidproject.EXTRA_TEXT_NAME";
    public static final String EXTRA_TEXT_IMAGE = "com.github.androidproject.EXTRA_TEXT_IMAGE";
    public static final String EXTRA_TEXT_FULL_IMAGE = "com.github.androidproject.EXTRA_TEXT_FULL_IMAGE";


    public ListAdapter(List<Hero> myDataset, List<HeroInfo> heroInfoList) { //constructor
        values = myDataset;
        HIValues = heroInfoList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtHeader;
        TextView txtFooter;
        TextView descriptionView;
        View layout;
        ImageView imageView;
        ImageView starViewImage1;
        ImageView starViewImage2;
        ImageView starViewImage3;
        ImageView starViewImage4;
        ImageView starViewImage5;
        ImageView elementViewImage;
        ImageView roleViewImage;
        LinearLayout linearLayout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = v.findViewById(R.id.firstLine);
            imageView = v.findViewById(R.id.icon);
            linearLayout = v.findViewById(R.id.linearLayout);
            descriptionView = v.findViewById(R.id.descriptionView);
            starViewImage1 = v.findViewById(R.id.starViewImage1);
            starViewImage2 = v.findViewById(R.id.starViewImage2);
            starViewImage3 = v.findViewById(R.id.starViewImage3);
            starViewImage4 = v.findViewById(R.id.starViewImage4);
            starViewImage5 = v.findViewById(R.id.starViewImage5);
            elementViewImage = v.findViewById(R.id.elementViewImage);
            roleViewImage = v.findViewById(R.id.roleViewImage);
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
        DisplayName(holder, currentHero);
        DisplayRarity(holder, currentHero);
        DisplayElement(holder, currentHero);
        DisplayRole(holder, currentHero);
        DisplayDescription(holder, currentHero);
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
    private void DisplayName(ViewHolder holder, Hero currentHero){
        holder.txtHeader.setText(currentHero.getName());
    }

    private void DisplayRarity(ViewHolder holder, Hero currentHero){
        if(currentHero.getRarity() == 1) {
            holder.starViewImage5.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage4.setImageResource(0);
            holder.starViewImage3.setImageResource(0);
            holder.starViewImage2.setImageResource(0);
            holder.starViewImage1.setImageResource(0);
        } else if (currentHero.getRarity() == 2){
            holder.starViewImage5.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage4.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage3.setImageResource(0);
            holder.starViewImage2.setImageResource(0);
            holder.starViewImage1.setImageResource(0);
        }else if (currentHero.getRarity() == 3){
            holder.starViewImage5.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage4.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage3.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage2.setImageResource(0);
            holder.starViewImage1.setImageResource(0);
        }else if (currentHero.getRarity() == 4){
            holder.starViewImage5.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage4.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage3.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage2.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage1.setImageResource(0);
        }else if (currentHero.getRarity() == 5){
            holder.starViewImage5.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage4.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage3.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage2.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage1.setImageResource(R.drawable.cm_icon_star);
        }
    }

    private void DisplayElement(ViewHolder holder, Hero currentHero){
        switch (currentHero.getAttribute()) {
            case "fire":
                holder.elementViewImage.setImageResource(R.drawable.cm_icon_profire);
                break;
            case "wind":
                holder.elementViewImage.setImageResource(R.drawable.cm_icon_proearth);
                break;
            case "ice":
                holder.elementViewImage.setImageResource(R.drawable.cm_icon_proice);
                break;
            case "light":
                holder.elementViewImage.setImageResource(R.drawable.cm_icon_promlight);
                break;
            case "dark":
                holder.elementViewImage.setImageResource(R.drawable.cm_icon_promdark);
                break;
        }
    }

    private void DisplayRole(ViewHolder holder, Hero currentHero){
        switch (currentHero.getRole()) {
            case "knight":
                holder.roleViewImage.setImageResource(R.drawable.cm_icon_role_knight);
                break;
            case "warrior":
                holder.roleViewImage.setImageResource(R.drawable.cm_icon_role_warrior);
                break;
            case "assassin":
                holder.roleViewImage.setImageResource(R.drawable.cm_icon_role_thief);
                break;
            case "mage":
                holder.roleViewImage.setImageResource(R.drawable.cm_icon_role_mage);
                break;
            case "manauser":
                holder.roleViewImage.setImageResource(R.drawable.cm_icon_role_soulweaver);
                break;
            case "ranger":
                holder.roleViewImage.setImageResource(R.drawable.cm_icon_role_ranger);
                break;
            case "material":
                holder.roleViewImage.setImageResource(R.drawable.cm_icon_role_material);
                break;
        }
    }

    private void DisplayDescription(ViewHolder holder, Hero currentHero){
        for(HeroInfo heroInfo : HIValues) {
            if(heroInfo.get_id().equals(currentHero.get_id())){
                holder.descriptionView.setText(heroInfo.getDescription());
            }
        }
    }

    private HeroInfo searchHeroInfoById(Hero hero){
        for(HeroInfo hi : HIValues) {
            if(hi.get_id().equals(hero.get_id())){
                return hi;
            }
        }
        return null;
    }

    private void addIcon(Hero currentHero, ViewHolder holder){
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

    private void openActivityInformation(View view, Hero hero, HeroInfo heroInfo){
        Intent intent = new Intent(view.getContext(), ActivityInformation.class);
        intent.putExtra("Hero", hero);

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