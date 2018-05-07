package com.talitaalbu.android.livrodereceitas_culinaria.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.talitaalbu.android.livrodereceitas_culinaria.R;
import com.talitaalbu.android.livrodereceitas_culinaria.model.Ingredient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {
    List<Ingredient> mIngredient;
    Context mContext;

    public IngredientAdapter(List<Ingredient> ingredients, Context context) {
        mIngredient = ingredients;
        mContext = context;
    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new IngredientViewHolder(inflater.inflate(R.layout.ingredient_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        holder.onBind(mIngredient.get(position));
    }

    @Override
    public int getItemCount() {
        return mIngredient.size();
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ingredient_text)
        TextView mTvDescription;

        public IngredientViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        public void onBind(Ingredient ingredient){
            StringBuilder builder = new StringBuilder();
            builder.append(ingredient.getQuantity());
            builder.append(" ");
            builder.append(ingredient.getType());
            builder.append(" - - ");
            builder.append(ingredient.getIngredient());


            mTvDescription.setText(builder.toString());
        }
    }
}
