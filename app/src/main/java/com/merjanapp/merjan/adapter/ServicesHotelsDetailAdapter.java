package com.merjanapp.merjan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.merjanapp.merjan.R;
import com.merjanapp.merjan.model.HotelService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hazem_ on 12/8/2016.
 */

public class ServicesHotelsDetailAdapter extends RecyclerView.Adapter<ServicesHotelsDetailAdapter.ViewHolder> {

    List<HotelService> contents;
    Context mContext;
    public ServicesHotelsDetailAdapter(List<HotelService> contents, Context mContext) {
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
                .inflate(R.layout.item_service_detail_hotel, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (mContext != null)
            Glide.with(mContext).load(contents.get(position).getImage()).into(holder.image);

        holder.name.setText(contents.get(position).getName());

    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.serviceIV)ImageView image;
        @BindView(R.id.nameTV)TextView name;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);


        }
    }

}