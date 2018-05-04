package com.talitaalbu.android.livrodereceitas_culinaria.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.talitaalbu.android.livrodereceitas_culinaria.R;
import com.talitaalbu.android.livrodereceitas_culinaria.RecipeActivity;
import com.talitaalbu.android.livrodereceitas_culinaria.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHoler> {

    public static String SEND_STEPS = "STEPS";
    public static String SEND_INGREDIENTS = "INGRIDIENTS";

    List<Recipe> mRecipes;
    Context mContext;

    public RecipeAdapter(List<Recipe> recipes, Context context) {
        mRecipes = recipes;
        mContext = context;
    }

    @Override
    public RecipeViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new RecipeViewHoler(inflater.inflate(R.layout.recipe_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecipeViewHoler holder, int position) {
        holder.onBind(mRecipes.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    class RecipeViewHoler extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.recipe_name_text) TextView mTvRecipe;
        private Recipe mRecipe;

        public RecipeViewHoler(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void onBind(Recipe recipe) {
            mRecipe = recipe;
            mTvRecipe.setText(mRecipe.getName());
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, RecipeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(SEND_STEPS, (ArrayList<? extends Parcelable>) mRecipe.getSteps());
            bundle.putParcelableArrayList(SEND_INGREDIENTS, (ArrayList<? extends Parcelable>) mRecipe.getIngredients());


            intent.putExtras(bundle);

//            Intent intentWidget = new Intent(mContext, CookingAppWidget.class);
//            intentWidget.setAction(Constants.ACTION_SHOW_RECIPES);
//            bundle.putParcelableArrayList(Constants.SEND_INGREDIENTS, (ArrayList<? extends Parcelable>) mRecipe.getIngredients());
//            intentWidget.putExtras(bundle);
//            mContext.sendBroadcast(intentWidget);

            mContext.startActivity(intent);
        }
    }
}
