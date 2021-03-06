package com.talitaalbu.android.livrodereceitas_culinaria.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.talitaalbu.android.livrodereceitas_culinaria.R;
import com.talitaalbu.android.livrodereceitas_culinaria.model.Step;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder>{
    List<Step> mSteps;
    Context mContext;
    StepAdapter.OnItemClickListener mOnItemClickListener;

    public StepAdapter(List<Step> steps, Context context,OnItemClickListener listener ) {
        mSteps = steps;
        mContext = context;
        mOnItemClickListener = listener;
    }

    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new StepViewHolder(inflater.inflate(R.layout.step_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(StepViewHolder stepViewHoler, final int position) {
        stepViewHoler.onBind(mSteps.get(position), position);
        stepViewHoler.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null)
                    mOnItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.step_order_text)
        TextView mTvOrder;
        @BindView(R.id.step_name_text)
        TextView mTvDescription;

        public StepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        public void onBind(Step step, int position){
            mTvOrder.setText(String.valueOf(position));
            mTvDescription.setText(step.getShortDescription());
        }

        @Override
        public void onClick(View view) {

        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
