package com.merjanapp.merjan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.merjanapp.merjan.R;
import com.merjanapp.merjan.model.JourDistinationModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hazem_ on 12/8/2016.
 */

public class JourDestinationAdapter extends RecyclerView.Adapter<JourDestinationAdapter.ViewHolder> {

    List<JourDistinationModel> contents;
    Context mContext;
    public JourDestinationAdapter(List<JourDistinationModel> contents, Context mContext) {
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
                .inflate(R.layout.item_jour_destination, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.titleTV.setText(contents.get(position).getTitle());
        holder.descTV.setText(contents.get(position).getDesc());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {



        @BindView(R.id.titleTV)TextView titleTV;
        @BindView(R.id.descTV)TextView descTV;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);

        }
    }

}