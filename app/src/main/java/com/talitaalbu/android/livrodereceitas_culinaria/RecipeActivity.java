package com.talitaalbu.android.livrodereceitas_culinaria;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.talitaalbu.android.livrodereceitas_culinaria.adapter.IngredientAdapter;
import com.talitaalbu.android.livrodereceitas_culinaria.adapter.RecipeAdapter;
import com.talitaalbu.android.livrodereceitas_culinaria.adapter.StepAdapter;
import com.talitaalbu.android.livrodereceitas_culinaria.model.Ingredient;
import com.talitaalbu.android.livrodereceitas_culinaria.model.Recipe;
import com.talitaalbu.android.livrodereceitas_culinaria.model.Step;
import com.talitaalbu.android.livrodereceitas_culinaria.services.RecipesTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.talitaalbu.android.livrodereceitas_culinaria.adapter.RecipeAdapter.SEND_INGREDIENTS;
import static com.talitaalbu.android.livrodereceitas_culinaria.adapter.RecipeAdapter.SEND_STEPS;


public class RecipeActivity extends AppCompatActivity {

    @BindView(R.id.recycler_ingredients_list)
    RecyclerView mRecyclerIngredients;
    @BindView(R.id.recycler_steps_list)
    RecyclerView mRecyclerSteps;
    @BindView(R.id.progress_recipes_list)
    ProgressBar mPbRecipe;
    @BindView(R.id.constraint_info)
    ConstraintLayout mMessageRecipeLayout;
    @BindView((R.id.tvInformation))
    TextView tvInformation;

    Recipe mRecipe;

    @Nullable
    //private RecipeIdlingResource mIdlingResource;

    @VisibleForTesting
    @NonNull
//    public IdlingResource getIdlingResource() {
//
//        if (mIdlingResource == null) {
//            mIdlingResource = new RecipeIdlingResource();
//        }
//        return mIdlingResource;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(this);

        mRecipe = new Recipe();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(SEND_INGREDIENTS)) {
            List<Ingredient> ingredients = bundle.getParcelableArrayList(SEND_INGREDIENTS);
            mRecipe.setIngredients(ingredients);
        }

        if (bundle != null && bundle.containsKey(SEND_STEPS)) {
            List<Step> steps = bundle.getParcelableArrayList(SEND_STEPS);
            mRecipe.setSteps(steps);
        }

        fillInformation();
    }

    public void fillInformation() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        LinearLayoutManager managerIngredients = new LinearLayoutManager(this);
        managerIngredients.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerSteps.setLayoutManager(manager);
        mRecyclerIngredients.setLayoutManager(managerIngredients);

        mRecyclerSteps.setHasFixedSize(true);
        mRecyclerIngredients.setHasFixedSize(true);

        IngredientAdapter adapter = new IngredientAdapter(mRecipe.getIngredients(), this);
        StepAdapter sAdapter = new StepAdapter(mRecipe.getSteps(), this);

        if (adapter != null && adapter.getItemCount() > 0) {
            mRecyclerIngredients.setAdapter(adapter);
        } else {
            mMessageRecipeLayout.setVisibility(View.VISIBLE);
            mPbRecipe.setVisibility(View.INVISIBLE);
            mRecyclerIngredients.setVisibility(View.INVISIBLE);
            tvInformation.setText(this.getString(R.string.message_error));
        }

        if (sAdapter != null && sAdapter.getItemCount() > 0) {
            mRecyclerSteps.addItemDecoration(new SpacingItemDecoration((int) getResources().getDimension(R.dimen.margin_medium)));
            mRecyclerSteps.setAdapter(sAdapter);
        } else {
            mMessageRecipeLayout.setVisibility(View.VISIBLE);
            mPbRecipe.setVisibility(View.INVISIBLE);
            mRecyclerSteps.setVisibility(View.INVISIBLE);
            tvInformation.setText(this.getString(R.string.message_error));
        }
    }
}

