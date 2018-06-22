package com.example.catchman.studlife.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.catchman.studlife.R;
import com.example.catchman.studlife.models.NewsResp;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by catchman on 5/28/18.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private View.OnClickListener onClickListener;
    private List<NewsResp> newsModels;

    public NewsAdapter(View.OnClickListener onClickListener, List<NewsResp> newsModels) {
        this.onClickListener = onClickListener;
        this.newsModels = newsModels;
        Log.e("$#@", "NewsAdapter: ");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);
        v.setOnClickListener(onClickListener);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvDescription.setText(newsModels.get(position).getText());
        holder.tvTitle.setText(newsModels.get(position).getDate());
       try{
           Picasso
                   .with(holder.itemView.getContext())
                   .load(newsModels.get(position).getImages().get(0).getUrl())
                   //.placeholder(R.drawable.default_profile_pic)
                   .fit()
                   .centerCrop()
                   .into(holder.newsImage);
       }  catch (Exception ignored){

       }
    }

    @Override
    public int getItemCount() {
        return newsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivNewImage)
        ImageView newsImage;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvDescription)
        TextView tvDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }

}
