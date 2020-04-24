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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.androidproject.models.Hero;
import com.github.androidproject.models.HeroInfo;
import com.github.androidproject.R;
import com.github.androidproject.activites.ActivityInformation;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

    private List<Hero> heroList; //liste des héros
    private List<HeroInfo> heroInfoList; //liste des infos sur les héros.
    private Hero currentHero;
    private HeroInfo currentHeroInfo;

    public static final String EXTRA_TEXT_IMAGE = "com.github.androidproject.EXTRA_TEXT_IMAGE";
    public static final String EXTRA_TEXT_FULL_IMAGE = "com.github.androidproject.EXTRA_TEXT_FULL_IMAGE";
    public static final String EXTRA_TEXT_RELATIONSHIP_IMAGE1 = "com.github.androidproject.EXTRA_TEXT_RELATIONSHIP_IMAGE1";
    public static final String EXTRA_TEXT_RELATIONSHIP_IMAGE2 = "com.github.androidproject.EXTRA_TEXT_RELATIONSHIP_IMAGE2";
    public static final String EXTRA_TEXT_RELATIONSHIP_IMAGE3 = "com.github.androidproject.EXTRA_TEXT_RELATIONSHIP_IMAGE3";
    public static final String EXTRA_TEXT_RELATIONSHIP_IMAGE4 = "com.github.androidproject.EXTRA_TEXT_RELATIONSHIP_IMAGE4";
    public static final String EXTRA_TEXT_RELATIONSHIP_IMAGE5 = "com.github.androidproject.EXTRA_TEXT_RELATIONSHIP_IMAGE5";
    public static final String EXTRA_TEXT_RELATIONSHIP_IMAGE6 = "com.github.androidproject.EXTRA_TEXT_RELATIONSHIP_IMAGE6";
    public static final String EXTRA_TEXT_RELATIONSHIP_IMAGE7 = "com.github.androidproject.EXTRA_TEXT_RELATIONSHIP_IMAGE7";
    public static final String EXTRA_TEXT_RELATIONSHIP_IMAGE8 = "com.github.androidproject.EXTRA_TEXT_RELATIONSHIP_IMAGE8";
    public static final String EXTRA_TEXT_RELATIONSHIP_IMAGE9 = "com.github.androidproject.EXTRA_TEXT_RELATIONSHIP_IMAGE9";
    public static final String EXTRA_TEXT_RELATIONSHIP_IMAGE10 = "com.github.androidproject.EXTRA_TEXT_RELATIONSHIP_IMAGE10";

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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
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
                }

                if (currentHeroInfo != null) {
                    openActivityInformation(v, currentHero, currentHeroInfo, heroList, heroInfoList);
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
                    Picasso.get().load("https://www.app.asso.fr/wp-content/uploads/2018/04/informer-140x140.png").into(holder.imageView);
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

        //nombre de relations
        int totalRelations = heroInfo.getRelationships().get(0).getRelations().size();

        // TODO: 25/04/2020 compter le nombre de relations du héro //
        // TODO: 25/04/2020 compter le nombre de relations du héro //
        // TODO: 25/04/2020 récuperer le id des relations et chercher les images correspondants à ces id //
        // TODO: 25/04/2020 passer ces images de l'autre côté de la view (sendrelationpictures) //


        //String imageRelationship = (heroInfo.getRelationshipList().get(0).getRelationsList());


        view.getContext().startActivity(intent);
    }

    public void getRelationsPictures(){

    }
  /*
   public void sendRelationsPictures(Intent intent, HeroInfo currentHeroInfo, int totalRelations) {


       switch (totalRelations) {
           case "1":
               //String imageRelation1 = (currentHeroInfo.getRelationshipList().get(0).getRelationsList());
               //intent.putExtra(EXTRA_TEXT_RELATIONSHIP_IMAGE1, imageRelation1);
               break;
           case "2":

               break;
           case "3":

               break;
           case "4":

               break;
           case "5":

               break;
           case "6":

               break;
           case "7":

               break;
           case "8":

               break;
           case "9":

               break;
           case "10":

               break;
       }
   }*/

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        // System.out.println("number of item : "+ values.size()); //to Test LogCat display
        return heroList.size();
    } //the adapter return the total number of items list

}