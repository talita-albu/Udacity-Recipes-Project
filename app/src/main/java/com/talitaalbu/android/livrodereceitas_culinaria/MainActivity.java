package com.talitaalbu.android.livrodereceitas_culinaria;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.constraint.ConstraintLayout;
import android.support.test.espresso.IdlingResource;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.talitaalbu.android.livrodereceitas_culinaria.adapter.RecipeAdapter;
import com.talitaalbu.android.livrodereceitas_culinaria.services.RecipesTask;
import com.talitaalbu.android.livrodereceitas_culinaria.testeutil.RecipeIdlingResource;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_recipes_list)
    RecyclerView mRecyclerRecipe;
    @BindView(R.id.progress_recipes_list)
    ProgressBar mPbRecipe;
    @BindView(R.id.constraint_info)
    ConstraintLayout mMessageRecipeLayout;
    @BindView((R.id.tvInformation))
    TextView tvInformation;
    @BindView(R.id.pull_to_refresh)
    SwipeRefreshLayout mPullToRefresh;

    @Nullable
    private RecipeIdlingResource mIdlingResource;

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {

        if (mIdlingResource == null) {
            mIdlingResource = new RecipeIdlingResource();
        }
        return mIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerRecipe.setLayoutManager(manager);
        mPullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadRecipes();
            }
        });

        getIdlingResource();
        mIdlingResource.setIdleState(false);

        loadRecipes();
    }

    private void loadRecipes()
    {
        try {
            if (RecipesTask.isConected(this)) {
                mPullToRefresh.setRefreshing(true);

                setupRecyclerView();
                RecipesTask task = new RecipesTask(mPbRecipe);
                RecipeAdapter adapter = new RecipeAdapter(task.execute().get(), this);

                if (adapter != null && adapter.getItemCount() > 0) {
                    mRecyclerRecipe.setAdapter(adapter);
                    mPullToRefresh.setRefreshing(false);

                    mMessageRecipeLayout.setVisibility(View.INVISIBLE);
                    mPbRecipe.setVisibility(View.INVISIBLE);
                    mRecyclerRecipe.setVisibility(View.VISIBLE);

                } else {
                    mMessageRecipeLayout.setVisibility(View.VISIBLE);
                    mPbRecipe.setVisibility(View.INVISIBLE);
                    mRecyclerRecipe.setVisibility(View.INVISIBLE);
                    tvInformation.setText(this.getString(R.string.no_recipe));
                }
            } else {
                mPullToRefresh.setRefreshing(false);
                mMessageRecipeLayout.setVisibility(View.VISIBLE);
                mPbRecipe.setVisibility(View.INVISIBLE);
                mRecyclerRecipe.setVisibility(View.INVISIBLE);
                tvInformation.setText(this.getString(R.string.no_network));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        mIdlingResource.setIdleState(true);
    }

    private void setupRecyclerView() {
        mRecyclerRecipe.setHasFixedSize(true);

        boolean tablet = getResources().getBoolean(R.bool.tablet);
        if (tablet) {
            mRecyclerRecipe.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        } else {
            mRecyclerRecipe.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        }

        mRecyclerRecipe.addItemDecoration(new SpacingItemDecoration((int) getResources().getDimension(R.dimen.margin_medium)));
    }
}

