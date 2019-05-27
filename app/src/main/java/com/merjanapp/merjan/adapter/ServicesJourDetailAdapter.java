package com.merjanapp.merjan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.merjanapp.merjan.R;
import com.merjanapp.merjan.model.JourServiceModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hazem_ on 12/8/2016.
 */

public class ServicesJourDetailAdapter extends RecyclerView.Adapter<ServicesJourDetailAdapter.ViewHolder> {

    List<JourServiceModel> contents;
    Context mContext;
    public ServicesJourDetailAdapter(List<JourServiceModel> contents, Context mContext) {
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

//        if (mContext != null)
//            Glide.with(mContext).load(contents.get(position).getImage()).into(holder.image);
//
        holder.name.setText(contents.get(position).getName());
        holder.image.setImageDrawable(mContext.getResources().getDrawable(contents.get(position).getImage()));

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