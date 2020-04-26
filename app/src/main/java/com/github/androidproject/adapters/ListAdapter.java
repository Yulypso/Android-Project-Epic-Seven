package com.github.androidproject.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.androidproject.activites.MainActivity;
import com.github.androidproject.activites.PopUp;
import com.github.androidproject.models.Hero;
import com.github.androidproject.models.HeroInfo;
import com.github.androidproject.R;
import com.github.androidproject.activites.ActivityInformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

    private List<Hero> heroList; //liste des héros
    private List<HeroInfo> heroInfoList; //liste des infos sur les héros.

    private Hero currentHero;
    private HeroInfo currentHeroInfo;
    private static int totalRelations =0;

    public static final String EXTRA_TEXT_IMAGE = "com.github.androidproject.EXTRA_TEXT_IMAGE";
    public static final String EXTRA_TEXT_FULL_IMAGE = "com.github.androidproject.EXTRA_TEXT_FULL_IMAGE";

    public ListAdapter(List<Hero> myDataset, List<HeroInfo> heroInfoList) { //constructor
        heroList = myDataset;
        this.heroInfoList = heroInfoList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
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
        return new ViewHolder(v);
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

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("position", ""+ position);
                currentHero = heroList.get(position);
                currentHeroInfo = searchHeroInfoById(currentHero);

                if (currentHeroInfo != null) {
                    Log.d("HeroInfo", ""+ currentHeroInfo.get_id());
                    openActivityInformation(v, currentHero, currentHeroInfo, heroList, heroInfoList);
                }else{
                    Intent intent2 = new Intent(v.getContext(), PopUp.class);
                    intent2.putExtra("currentHeroMissing", currentHero);
                    v.getContext().startActivity(intent2);
                }
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
        for(HeroInfo heroInfo : heroInfoList) {
            if(heroInfo.get_id().equals(currentHero.get_id())){
                holder.descriptionView.setText(heroInfo.getDescription());
            }
        }
    }

    private HeroInfo searchHeroInfoById(Hero hero){
        for(HeroInfo hi : heroInfoList) {
            if(hi.get_id().equals(hero.get_id())){
                return hi;
            }
        }
        return null;
    }

    private void addIcon(Hero currentHero, ViewHolder holder){
        boolean imageFound = false;
        if(heroInfoList != null){
            for(int i = 0; i< heroInfoList.size(); i++){
                if(currentHero.get_id().equals(heroInfoList.get(i).get_id())){
                    Picasso.get().load(heroInfoList.get(i).getAssets().getIcon()).into(holder.imageView);
                    imageFound = true;
                }

                if(!currentHero.get_id().equals(heroInfoList.get(i).get_id()) && i == heroInfoList.size()-1 && !imageFound){
                    holder.imageView.setImageResource(R.drawable.iconmissing);
                    imageFound = false;
                }
            }
        }
    }


    private void openActivityInformation(View view, Hero hero, HeroInfo heroInfo, List<Hero> heroList, List<HeroInfo> heroInfoList){
        Intent intent = new Intent(view.getContext(), ActivityInformation.class);
        intent.putExtra("Hero", hero);
        intent.putExtra("HeroInfo", heroInfo);

        //On doit definir exceptionnellement ces String ici pour pouvoir les charger au préalable
        //dans toute la liste avant d'entrer dans la prochaine activity (images provenant de l'api rest)
        String imageUrl = heroInfo.getAssets().getImage();
        intent.putExtra(EXTRA_TEXT_IMAGE, imageUrl);

        String imageFullUrl = (hero.getModelURL());
        intent.putExtra(EXTRA_TEXT_FULL_IMAGE, imageFullUrl);

        List<HeroInfo> heroRelations = getRelations(heroInfoList, currentHeroInfo);
        sendRelations(intent, heroRelations);
        intent.putExtra("totalRelations", totalRelations);

        view.getContext().startActivity(intent);
    }

    private List<HeroInfo> getRelations(List<HeroInfo> heroInfoList, HeroInfo currentHeroInfo){
        List<HeroInfo> heroRelations = new ArrayList<>();
        totalRelations = currentHeroInfo.getRelationships().get(0).getRelations().size();

        if(totalRelations>0) {
            for (HeroInfo HI : heroInfoList) {
                for (int i = 0; i < totalRelations; i++) {
                    for(Hero H : heroList) {
                        if ((H.get_id().equals(HI.get_id())) && (HI.getId().equals(currentHeroInfo.getRelationships().get(0).getRelations().get(i).getId())) && (!currentHeroInfo.getRelationships().get(0).getRelations().get(i).getId().contains("npc")) && (!currentHeroInfo.getRelationships().get(0).getRelations().get(i).getId().contains("m"))) {
                            heroRelations.add(HI);
                            System.out.println("ADDED ! " + HI.getId() + " " + HI.getName());

                        }
                    }
                }
            }
        }
        totalRelations = heroRelations.size();
        return heroRelations;
    }

    private void sendRelations(Intent intent, List<HeroInfo> heroRelations) {
        System.out.println("TOTAL RELATION after counter : "+totalRelations);
        if(totalRelations>0) {
            switch (totalRelations) {
                default:
                    break;
                case 1:
                    if(heroRelations.get(0) != null)
                        intent.putExtra("HeroInfo1", heroRelations.get(0));
                    break;
                case 2:
                    if(heroRelations.get(0) != null)
                        intent.putExtra("HeroInfo1", heroRelations.get(0));
                    if(heroRelations.get(1) != null)
                        intent.putExtra("HeroInfo2", heroRelations.get(1));
                    break;
                case 3:
                    if(heroRelations.get(0) != null)
                        intent.putExtra("HeroInfo1", heroRelations.get(0));
                    if(heroRelations.get(1) != null)
                        intent.putExtra("HeroInfo2", heroRelations.get(1));
                    if(heroRelations.get(2) != null)
                        intent.putExtra("HeroInfo3", heroRelations.get(2));
                    break;
                case 4:
                    if(heroRelations.get(0) != null)
                        intent.putExtra("HeroInfo1", heroRelations.get(0));
                    if(heroRelations.get(1) != null)
                        intent.putExtra("HeroInfo2", heroRelations.get(1));
                    if(heroRelations.get(2) != null)
                        intent.putExtra("HeroInfo3", heroRelations.get(2));
                    if(heroRelations.get(3) != null)
                        intent.putExtra("HeroInfo4", heroRelations.get(3));
                    break;
                case 5:
                    if(heroRelations.get(0) != null)
                        intent.putExtra("HeroInfo1", heroRelations.get(0));
                    if(heroRelations.get(1) != null)
                        intent.putExtra("HeroInfo2", heroRelations.get(1));
                    if(heroRelations.get(2) != null)
                        intent.putExtra("HeroInfo3", heroRelations.get(2));
                    if(heroRelations.get(3) != null)
                        intent.putExtra("HeroInfo4", heroRelations.get(3));
                    if(heroRelations.get(4) != null)
                        intent.putExtra("HeroInfo5", heroRelations.get(4));
                    break;
                case 6:
                    if(heroRelations.get(0) != null)
                        intent.putExtra("HeroInfo1", heroRelations.get(0));
                    if(heroRelations.get(1) != null)
                        intent.putExtra("HeroInfo2", heroRelations.get(1));
                    if(heroRelations.get(2) != null)
                        intent.putExtra("HeroInfo3", heroRelations.get(2));
                    if(heroRelations.get(3) != null)
                        intent.putExtra("HeroInfo4", heroRelations.get(3));
                    if(heroRelations.get(4) != null)
                        intent.putExtra("HeroInfo5", heroRelations.get(4));
                    if(heroRelations.get(5) != null)
                        intent.putExtra("HeroInfo6", heroRelations.get(5));
                    break;
                case 7:
                    if(heroRelations.get(0) != null)
                        intent.putExtra("HeroInfo1", heroRelations.get(0));
                    if(heroRelations.get(1) != null)
                        intent.putExtra("HeroInfo2", heroRelations.get(1));
                    if(heroRelations.get(2) != null)
                        intent.putExtra("HeroInfo3", heroRelations.get(2));
                    if(heroRelations.get(3) != null)
                        intent.putExtra("HeroInfo4", heroRelations.get(3));
                    if(heroRelations.get(4) != null)
                        intent.putExtra("HeroInfo5", heroRelations.get(4));
                    if(heroRelations.get(5) != null)
                        intent.putExtra("HeroInfo6", heroRelations.get(5));
                    if(heroRelations.get(6) != null)
                        intent.putExtra("HeroInfo7", heroRelations.get(6));
                    break;
                case 8:
                    if(heroRelations.get(0) != null)
                        intent.putExtra("HeroInfo1", heroRelations.get(0));
                    if(heroRelations.get(1) != null)
                        intent.putExtra("HeroInfo2", heroRelations.get(1));
                    if(heroRelations.get(2) != null)
                        intent.putExtra("HeroInfo3", heroRelations.get(2));
                    if(heroRelations.get(3) != null)
                        intent.putExtra("HeroInfo4", heroRelations.get(3));
                    if(heroRelations.get(4) != null)
                        intent.putExtra("HeroInfo5", heroRelations.get(4));
                    if(heroRelations.get(5) != null)
                        intent.putExtra("HeroInfo6", heroRelations.get(5));
                    if(heroRelations.get(6) != null)
                        intent.putExtra("HeroInfo7", heroRelations.get(6));
                    if(heroRelations.get(7) != null)
                        intent.putExtra("HeroInfo8", heroRelations.get(7));
                    break;
                case 9:
                    if(heroRelations.get(0) != null)
                        intent.putExtra("HeroInfo1", heroRelations.get(0));
                    if(heroRelations.get(1) != null)
                        intent.putExtra("HeroInfo2", heroRelations.get(1));
                    if(heroRelations.get(2) != null)
                        intent.putExtra("HeroInfo3", heroRelations.get(2));
                    if(heroRelations.get(3) != null)
                        intent.putExtra("HeroInfo4", heroRelations.get(3));
                    if(heroRelations.get(4) != null)
                        intent.putExtra("HeroInfo5", heroRelations.get(4));
                    if(heroRelations.get(5) != null)
                        intent.putExtra("HeroInfo6", heroRelations.get(5));
                    if(heroRelations.get(6) != null)
                        intent.putExtra("HeroInfo7", heroRelations.get(6));
                    if(heroRelations.get(7) != null)
                        intent.putExtra("HeroInfo8", heroRelations.get(7));
                    if(heroRelations.get(8) != null)
                        intent.putExtra("HeroInfo9", heroRelations.get(8));
                    break;
                case 10:
                    if(heroRelations.get(0) != null)
                        intent.putExtra("HeroInfo1", heroRelations.get(0));
                    if(heroRelations.get(1) != null)
                        intent.putExtra("HeroInfo2", heroRelations.get(1));
                    if(heroRelations.get(2) != null)
                        intent.putExtra("HeroInfo3", heroRelations.get(2));
                    if(heroRelations.get(3) != null)
                        intent.putExtra("HeroInfo4", heroRelations.get(3));
                    if(heroRelations.get(4) != null)
                        intent.putExtra("HeroInfo5", heroRelations.get(4));
                    if(heroRelations.get(5) != null)
                        intent.putExtra("HeroInfo6", heroRelations.get(5));
                    if(heroRelations.get(6) != null)
                        intent.putExtra("HeroInfo7", heroRelations.get(6));
                    if(heroRelations.get(7) != null)
                        intent.putExtra("HeroInfo8", heroRelations.get(7));
                    if(heroRelations.get(8) != null)
                        intent.putExtra("HeroInfo9", heroRelations.get(8));
                    if(heroRelations.get(9) != null)
                        intent.putExtra("HeroInfo10", heroRelations.get(9));
                    break;
            }
        }
   }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        // System.out.println("number of item : "+ values.size()); //to Test LogCat display
        return heroList.size();
    } //the adapter return the total number of items list

}