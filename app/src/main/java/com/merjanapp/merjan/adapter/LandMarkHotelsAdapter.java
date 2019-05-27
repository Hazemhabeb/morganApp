package com.merjanapp.merjan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.merjanapp.merjan.R;
import com.merjanapp.merjan.model.HotelLandMarkModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hazem_ on 12/8/2016.
 */

public class LandMarkHotelsAdapter extends RecyclerView.Adapter<LandMarkHotelsAdapter.ViewHolder> {

    List<HotelLandMarkModel> contents;
    Context mContext;
    public LandMarkHotelsAdapter(List<HotelLandMarkModel> contents, Context mContext) {
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
                .inflate(R.layout.item_landmark_hotel, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

//        if (mContext != null)
//            Glide.with(mContext).load(contents.get(position).getImage()).into(holder.image);

        holder.image.setImageDrawable(mContext.getResources().getDrawable(contents.get(position).getImageR()));
        holder.name.setText(contents.get(position).getName());
        //todo here to add the distance
        holder.distance.setText(contents.get(position).getDistance());


    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.serviceIV)ImageView image;
        @BindView(R.id.nameTV)TextView name;
        @BindView(R.id.distance)TextView distance;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);


        }
    }

}