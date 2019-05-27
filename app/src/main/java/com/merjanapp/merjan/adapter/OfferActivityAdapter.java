package com.merjanapp.merjan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.merjanapp.merjan.R;
import com.merjanapp.merjan.activity.ActivityDetailActivity;
import com.merjanapp.merjan.activity.DetailHotelActivity;
import com.merjanapp.merjan.model.OfferActivityModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OfferActivityAdapter extends RecyclerView.Adapter<OfferActivityAdapter.ViewHolder> {

    private List<OfferActivityModel> contents;
    private Context mContext;
    private int type;

    public OfferActivityAdapter(List<OfferActivityModel> contents, Context mContext,int type) {
        this.contents = contents;
        this.mContext = mContext;
        this.type = type;
    }


    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;


        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main_offers, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.name.setText(contents.get(position).getOfferName());


        if (mContext != null)
            Glide.with(mContext).load(contents.get(position).getOfferImageUrl()).into(holder.offerImage);



        holder.cardTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type==1) {
                    Intent i = new Intent(mContext, ActivityDetailActivity.class);
                    i.putExtra("data", contents.get(position).getActivityID());

                    mContext.startActivity(i);

                }else if (type==2){
                    Intent i = new Intent(mContext, DetailHotelActivity.class);
                    i.putExtra("data",contents.get(position).getActivityID());

                    mContext.startActivity(i);
                }
            }
        });

    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        //init the views
        @BindView(R.id.offerImage) ImageView offerImage;
        @BindView(R.id.nameTV) TextView name;
        @BindView(R.id.cardTV) TextView cardTV;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);

        }
    }

}