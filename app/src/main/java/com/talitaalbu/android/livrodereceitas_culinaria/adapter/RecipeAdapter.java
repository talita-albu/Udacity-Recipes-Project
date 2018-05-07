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
import com.talitaalbu.android.livrodereceitas_culinaria.widget.WidgetService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHoler> {

    public static final String ACTION_SHOW_RECIPES =  "SHOW_RECIPE";
    public static final String SEND_STEPS = "STEPS";
    public static final String SEND_INGREDIENTS = "INGRIDIENTS";
    public static final String SEND_RECIPE_NAME = "RECIPE";

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
            bundle.putString(SEND_RECIPE_NAME, mRecipe.getName());


            intent.putExtras(bundle);

            WidgetService.updateWidget(mContext, mRecipe);

            mContext.startActivity(intent);
        }
    }
}
