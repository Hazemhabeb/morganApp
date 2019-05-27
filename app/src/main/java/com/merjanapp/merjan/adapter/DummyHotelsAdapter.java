package com.merjanapp.merjan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.merjanapp.merjan.R;
import com.merjanapp.merjan.model.dummyModel;

import java.util.List;

/**
 * Created by hazem_ on 12/8/2016.
 */

public class DummyHotelsAdapter extends RecyclerView.Adapter<DummyHotelsAdapter.ViewHolder> {

    List<dummyModel> contents;
    Context mContext;
    public DummyHotelsAdapter(List<dummyModel> contents, Context mContext) {
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

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        holder.parent_categ.setBackgroundColor(mContext.getColor(contents.get(position).getColor()));

    }

    static class ViewHolder extends RecyclerView.ViewHolder {



        ViewHolder(View view) {
            super(view);


        }
    }

}