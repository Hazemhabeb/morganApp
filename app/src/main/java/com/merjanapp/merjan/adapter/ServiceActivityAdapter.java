package com.merjanapp.merjan.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.merjanapp.merjan.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hazem_ on 12/8/2016.
 */

public class ServiceActivityAdapter extends RecyclerView.Adapter<ServiceActivityAdapter.ViewHolder> {

    private List<String> contents;
    private Context mContext;
    public ServiceActivityAdapter(List<String> contents, Context mContext) {
        this.contents = contents;
        this.mContext = mContext;
    }


    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;


        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_service_activity, parent, false);
        return new ViewHolder(view);


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.numberTV.setText((position+1)+"");

        if (position==0){
            holder.topV.setBackgroundColor(mContext.getResources().getColor(R.color.wight));
        }else if (position==contents.size()-1){
            holder.bottomV.setBackgroundColor(mContext.getResources().getColor(R.color.wight));
        }

        holder.titleTV.setText(contents.get(position));


    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.topV)View topV;
        @BindView(R.id.bottomV)View bottomV;
        @BindView(R.id.leftV)View leftV;
        @BindView(R.id.numberTV)TextView numberTV;
        @BindView(R.id.titleTV)TextView titleTV;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }


    }

}