package com.talitaalbu.android.livrodereceitas_culinaria;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.talitaalbu.android.livrodereceitas_culinaria.adapter.StepFragmentAdapter;
import com.talitaalbu.android.livrodereceitas_culinaria.model.Recipe;
import com.talitaalbu.android.livrodereceitas_culinaria.model.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.talitaalbu.android.livrodereceitas_culinaria.adapter.RecipeAdapter.SEND_RECIPE_NAME;
import static com.talitaalbu.android.livrodereceitas_culinaria.adapter.RecipeAdapter.SEND_STEPS;

public class StepDetailActivity extends AppCompatActivity {
    @BindView(R.id.step_tab_layout)
    TabLayout mTlStep;
    @BindView(R.id.step_viewpager)
    ViewPager mVpStep;
    @BindView(android.R.id.content)
    View mParentLayout;

    private String mName;
    private ArrayList<Step> mSteps;
    private int mStepSelectedPosition;

    public static final String STEP_SELECTED_KEY = "step";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        ButterKnife.bind(this);

       Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(SEND_STEPS) && bundle.containsKey(STEP_SELECTED_KEY)) {
            mSteps = bundle.getParcelableArrayList(SEND_STEPS);
            mStepSelectedPosition = bundle.getInt(STEP_SELECTED_KEY);
            mName = bundle.getString(SEND_RECIPE_NAME);
        } else {
            //Misc.makeSnackBar(this, mParentLayout, getString(R.string.no_recipe), true);
            //finish();
        }

        // Show the Up button in the action bar.
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(mName);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        StepFragmentAdapter adapter = new StepFragmentAdapter(getApplicationContext(), mSteps, getSupportFragmentManager());
        mVpStep.setAdapter(adapter);
        mTlStep.setupWithViewPager(mVpStep);
        mVpStep.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (actionBar != null) {
                    actionBar.setTitle(mSteps.get(position).getShortDescription());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mVpStep.setCurrentItem(mStepSelectedPosition);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
