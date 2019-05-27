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
import com.merjanapp.merjan.activity.ResultHotelActivity;
import com.merjanapp.merjan.model.CityActivityModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityHotelAdapter extends RecyclerView.Adapter<CityHotelAdapter.ViewHolder> {

    private List<CityActivityModel> contents;
    private Context mContext;
    public CityHotelAdapter(List<CityActivityModel> contents, Context mContext) {
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
                .inflate(R.layout.item_activity_city, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.city.setText(contents.get(position).getName());
        holder.country.setText(contents.get(position).getCounty());

        if (mContext != null)
            Glide.with(mContext).load(contents.get(position).getImage()).into(holder.image);


        holder.cardTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, ResultHotelActivity.class);
                i.putExtra("data",contents.get(position));
                mContext.startActivity(i);
            }
        });

    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        //init the views
//        LinearLayout parent_categ;
        @BindView(R.id.cityImage) ImageView image;
        TextView city,country,cardTV;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            city=view.findViewById(R.id.cityTV);
            country=view.findViewById(R.id.countryTV);
            cardTV=view.findViewById(R.id.cardTV);

        }
    }

}