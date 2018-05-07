package com.talitaalbu.android.livrodereceitas_culinaria.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.talitaalbu.android.livrodereceitas_culinaria.R;
import com.talitaalbu.android.livrodereceitas_culinaria.fragment.StepDetailFragment;
import com.talitaalbu.android.livrodereceitas_culinaria.model.Step;

import java.util.List;

public class StepFragmentAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<Step> mSteps;

    public StepFragmentAdapter(Context context, List<Step> steps, FragmentManager fm) {
        super(fm);
        this.mContext = context;
        this.mSteps = steps;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle arguments = new Bundle();
        arguments.putParcelable(StepDetailFragment.STEP_KEY, mSteps.get(position));
        StepDetailFragment fragment = new StepDetailFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return String.format(mContext.getString(R.string.step), position);
    }

    @Override
    public int getCount() {
        return mSteps.size();
    }


}
