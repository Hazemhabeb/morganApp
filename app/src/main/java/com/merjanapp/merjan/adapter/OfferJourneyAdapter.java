package com.merjanapp.merjan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.merjanapp.merjan.R;
import com.merjanapp.merjan.activity.DetailJourActivity;
import com.merjanapp.merjan.model.JournyOfferModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OfferJourneyAdapter extends RecyclerView.Adapter<OfferJourneyAdapter.ViewHolder> {

    private List<JournyOfferModel> contents;
    private Context mContext;

    public OfferJourneyAdapter(List<JournyOfferModel> contents, Context mContext) {
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
                .inflate(R.layout.item_main_offers, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.name.setText(contents.get(position).getName());


        if (mContext != null)
            Glide.with(mContext).load(contents.get(position).getImage()).into(holder.offerImage);


        holder.cardTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(mContext, DetailJourActivity.class);

                String url = "http://172.107.175.236:800/api/journey/JourneyDetails?journeyid=" + contents.get(position).getId()
                        + "&RoomId=" + 0
                        + "&FlightId=" + 0
                        + "&NightId=" + 0;


                Log.d("google", "this is the detail journy url " + url);

                i.putExtra("data", url);
                i.putExtra("room", 0);
                i.putExtra("night", 0);
                mContext.startActivity(i);


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