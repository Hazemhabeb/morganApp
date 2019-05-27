package com.merjanapp.merjan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.merjanapp.merjan.R;
import com.merjanapp.merjan.model.JourChangeActModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hazem_ on 12/8/2016.
 */

public class JourChangeActAdapter extends RecyclerView.Adapter<JourChangeActAdapter.ViewHolder> {

    List<JourChangeActModel> contents;
    Context mContext;
    public JourChangeActAdapter(List<JourChangeActModel> contents, Context mContext) {
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
                .inflate(R.layout.item_jour_change_act, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        if (mContext != null)
            Glide.with(mContext).load("http://172.107.175.236/MultiMedia/Activity/"+contents.get(position).getImage()).into(holder.roomeImage);

        holder.nameTV.setText(contents.get(position).getName());
        holder.priceTV.setText(contents.get(position).getPrice()+" $ ");

        holder.reservationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(contents.get(position));
            }
        });

    }

    static class ViewHolder extends RecyclerView.ViewHolder {



        @BindView(R.id.roomImage)ImageView roomeImage;
        @BindView(R.id.nameTV)TextView nameTV;
        @BindView(R.id.priceTV)TextView priceTV;
        @BindView(R.id.reservationBtn)Button reservationBtn;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);


        }
    }

}