package com.example.catchman.studlife.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.catchman.studlife.R;
import com.example.catchman.studlife.models.PosterResp;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by catchman on 6/16/18.
 */

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.ViewHolder> {


    private List<PosterResp> posterList;
    private Context context;


    public PosterAdapter(List<PosterResp> posterList, Context context) {
        this.posterList = posterList;
        Log.e("%$#", "PosterAdapter: " + posterList.size() );
        this.context = context;
    }

    @Override
    public PosterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.poster_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        PosterAdapter.ViewHolder vh = new PosterAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PosterAdapter.ViewHolder holder, int position) {
//        Glide
//                .with(holder.itemView)
//                .load(posterList.get(position).getUrl())
//                .into(holder.ivPhoto);

        Picasso
                .with(context)
                .load(posterList.get(position).getUrl())
                .placeholder(R.drawable.flag_of_none)
                .fit()
                .centerCrop()
                .into(holder.ivPhoto);
    }

    @Override
    public int getItemCount() {
        return posterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivPhoto)
        ImageView ivPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
