package com.example.user.newsapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.newsapp.Models.ArticlesModelData;
import com.example.user.newsapp.Models.HeadlineModelData;
import com.example.user.newsapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by user on 5/6/17.
 */

public class MyRecyclerAdapters extends RecyclerView.Adapter<MyRecyclerAdapters.CustomViewHolder> {
    private ArrayList<ArticlesModelData> articleslistofnews;
    private MyRecyclerAdapters.OnItemClickListener onItemClickListener;
    private Context context;

    public MyRecyclerAdapters(Context context, ArrayList<ArticlesModelData> articleslistofnews, OnItemClickListener onItemClickListener) {
        this.articleslistofnews = articleslistofnews;
        this.onItemClickListener = onItemClickListener;
        this.context = context;

    }

    @Override
    public MyRecyclerAdapters.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerviewfornewsdisplay, viewGroup, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyRecyclerAdapters.CustomViewHolder holder, final int position) {
        ArticlesModelData articlesModelData = articleslistofnews.get(position);
        holder.textViewNewsHeadline.setText(articlesModelData.getTitle());
        holder.textViewNewsDescription.setText(articlesModelData.getDescription());
        Picasso.with(context).load(articlesModelData.getUrlToImage()).resize(75,75).into(holder.imageViewNewsPhoto);
        holder.viewgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(position);
            }
        });
        holder.viewgroup.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemClickListener.onItemLongClickListener(position);

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != articleslistofnews ? articleslistofnews.size() : 0);
    }

    public interface OnItemClickListener {
        void onClick(int position);

        void onItemLongClickListener(int position);

    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNewsHeadline, textViewNewsDescription;
        ImageView imageViewNewsPhoto;
        public View viewgroup;

        public CustomViewHolder(View view) {
            super(view);
            this.textViewNewsHeadline = (TextView) view.findViewById(R.id.textViewNewsHeadline);
            this.textViewNewsDescription = (TextView) view.findViewById(R.id.textViewNewsDescription);
            this.imageViewNewsPhoto = (ImageView) view.findViewById(R.id.imageViewNewsPhoto);
            this.viewgroup = (ViewGroup) view.findViewById(R.id.recycler_viewfornewsdisplay);
        }
    }


}
