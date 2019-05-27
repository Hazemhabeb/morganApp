package com.merjanapp.merjan.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.merjanapp.merjan.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagesActivityAdapter extends RecyclerView.Adapter<ImagesActivityAdapter.ViewHolder> {

    private List<String> contents;
    private Context mContext;
    public ImagesActivityAdapter(List<String> contents, Context mContext) {
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
                .inflate(R.layout.item_images, parent, false);
        return new ViewHolder(view);


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        if (mContext != null)
            Glide.with(mContext).load(contents.get(position)).into(holder.image);


    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.activityImage)ImageView image;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }


    }

}