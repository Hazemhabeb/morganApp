package com.merjanapp.merjan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.merjanapp.merjan.R;
import com.merjanapp.merjan.activity.ActivityDetailActivity;
import com.merjanapp.merjan.model.ResultActivityModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ResultActivityAdapter extends RecyclerView.Adapter<ResultActivityAdapter.ViewHolder> {

    private List<ResultActivityModel> contents;
    private Context mContext;

    public ResultActivityAdapter(List<ResultActivityModel> contents, Context mContext) {
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
                .inflate(R.layout.item_activity_result, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.name.setText(contents.get(position).getName());
        holder.city.setText(contents.get(position).getCityName());
        holder.price.setText(contents.get(position).getPrice());
        holder.detail.setText(contents.get(position).getDetail());



        if (mContext != null)
            Glide.with(mContext).load(contents.get(position).getImage()).into(holder.image);

        holder.cardParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, ActivityDetailActivity.class);
                i.putExtra("data",contents.get(position).getId());

                mContext.startActivity(i);

            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        //init the views
        @BindView(R.id.resultIV) ImageView image;
        @BindView(R.id.nameTV) TextView name;
        @BindView(R.id.priceTV) TextView price;
        @BindView(R.id.cityTV)TextView city;
        @BindView(R.id.detailTV)TextView detail;
        @BindView(R.id.cardParent)CardView cardParent;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);

        }
    }

}