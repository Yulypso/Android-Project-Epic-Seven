package com.github.androidproject.presentation.ui.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.androidproject.Constants;
import com.github.androidproject.R;
import com.github.androidproject.presentation.ui.models.Hero;
import com.github.androidproject.presentation.ui.models.HeroInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements Filterable {

    private List<Hero> heroList; //liste des héros
    private List<Hero> heroListFull;

    private List<HeroInfo> heroInfoList; //liste des infos sur les héros.

    private Hero currentHero;


    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Hero item);
    }

    public ListAdapter(List<Hero> heroList, List<HeroInfo> heroInfoList, OnItemClickListener listener) { //constructor
        this.heroList = heroList;
        this.heroInfoList = heroInfoList;
        this.heroListFull = new ArrayList<>(heroList);
        this.listener = listener;

        for(Hero hero: heroList){
            Log.d("Hero", hero.getName());
        }

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtHeader;
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
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        currentHero = heroList.get(position);
        DisplayName(holder, currentHero);
        DisplayRarity(holder, currentHero);
        DisplayElement(holder, currentHero);
        DisplayRole(holder, currentHero);
        DisplayDescription(holder, currentHero);
        addIcon(currentHero, holder);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                currentHero = heroList.get(position);
                listener.onItemClick(currentHero);
            }
        });
    }

    private void DisplayName(ViewHolder holder, Hero currentHero) {
        holder.txtHeader.setText(currentHero.getName());
    }

    private void DisplayRarity(ViewHolder holder, Hero currentHero) {
        if (currentHero.getRarity() == 1) {
            holder.starViewImage5.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage4.setImageResource(0);
            holder.starViewImage3.setImageResource(0);
            holder.starViewImage2.setImageResource(0);
            holder.starViewImage1.setImageResource(0);
        } else if (currentHero.getRarity() == 2) {
            holder.starViewImage5.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage4.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage3.setImageResource(0);
            holder.starViewImage2.setImageResource(0);
            holder.starViewImage1.setImageResource(0);
        } else if (currentHero.getRarity() == 3) {
            holder.starViewImage5.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage4.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage3.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage2.setImageResource(0);
            holder.starViewImage1.setImageResource(0);
        } else if (currentHero.getRarity() == 4) {
            holder.starViewImage5.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage4.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage3.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage2.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage1.setImageResource(0);
        } else if (currentHero.getRarity() == 5) {
            holder.starViewImage5.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage4.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage3.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage2.setImageResource(R.drawable.cm_icon_star);
            holder.starViewImage1.setImageResource(R.drawable.cm_icon_star);
        }
    }

    private void DisplayElement(ViewHolder holder, Hero currentHero) {
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

    private void DisplayRole(ViewHolder holder, Hero currentHero) {
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

    private void DisplayDescription(ViewHolder holder, Hero currentHero) {
        for (HeroInfo heroInfo : heroInfoList) {
            if (heroInfo.get_id().equals(currentHero.get_id())) {
                holder.descriptionView.setText(heroInfo.getDescription());
            }
        }
    }

    private void addIcon(Hero currentHero, ViewHolder holder) {
        boolean imageFound = false;
        if (heroInfoList != null) {
            for (int i = 0; i < heroInfoList.size(); i++) {
                if (currentHero.get_id().equals(heroInfoList.get(i).get_id())) {
                    Picasso.get().load(heroInfoList.get(i).getAssets().getIcon()).into(holder.imageView);
                    imageFound = true;
                }

                if (!currentHero.get_id().equals(heroInfoList.get(i).get_id()) && i == heroInfoList.size() - 1 && !imageFound) {
                    holder.imageView.setImageResource(R.drawable.iconmissing);
                    imageFound = false;
                }
            }
        }
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        // System.out.println("number of item : "+ values.size()); //to Test LogCat display
        return heroList.size();
    } //the adapter return the total number of items list

    @Override
    public Filter getFilter() {
        return heroFilter;
    }

    Filter heroFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Hero> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0 || charSequence.toString().isEmpty()) { //canEdit
                filteredList.addAll(heroListFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase();

                for (Hero hero : heroListFull) {
                    if (hero.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(hero);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            heroList.clear();
            heroList.addAll((Collection<? extends Hero>) filterResults.values);
            notifyDataSetChanged();
        }
    };
}