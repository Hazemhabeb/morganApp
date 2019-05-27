package com.merjanapp.merjan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.merjanapp.merjan.R;
import com.merjanapp.merjan.model.SearchJourModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hazem_ on 12/8/2016.
 */

public class SearchJourAdapter extends RecyclerView.Adapter<SearchJourAdapter.ViewHolder> {

    List<SearchJourModel> contents;
    Context mContext;
    public SearchJourAdapter(List<SearchJourModel> contents, Context mContext) {
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
                .inflate(R.layout.item_search_autocomplete, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.nameTV.setText(contents.get(position).getName());
        holder.typeTV.setText(" ( "+contents.get(position).getType()+" ) ");

        holder.searchParnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(contents.get(position));
            }
        });

    }

    static class ViewHolder extends RecyclerView.ViewHolder {



        @BindView(R.id.nameTV)TextView nameTV;
        @BindView(R.id.typeTV)TextView typeTV;
        @BindView(R.id.searchParnt)LinearLayout searchParnt;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);



        }
    }

}