package com.example.user.nassconevent.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.nassconevent.R;
import com.example.user.nassconevent.model.SpeakersDataModel;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

import static com.example.user.nassconevent.R.id.textViewnamesofSpeakers;

public class MyRecyclerAdapters extends RecyclerView.Adapter<MyRecyclerAdapters.CustomViewHolder> {
    private List<SpeakersDataModel> speakersDetailsList;
    private MyRecyclerAdapters.OnItemClickListener onItemClickListener;
    private Context context;
    public MyRecyclerAdapters(Context context,ArrayList<SpeakersDataModel> speakersDetailsList, OnItemClickListener onItemClickListener) {
        this.speakersDetailsList = speakersDetailsList;
        this.onItemClickListener = onItemClickListener;
        this.context=context;

    }

    @Override
    public MyRecyclerAdapters.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_viewforspeakers, viewGroup, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyRecyclerAdapters.CustomViewHolder holder, final int position) {

        SpeakersDataModel speakersDetails = speakersDetailsList.get(position);
        if(speakersDetails.getName().charAt(0)=='A')
        {
            holder.textViewnamesofSpeakers.setTextColor(Color.BLUE);
        }
        holder.textViewnamesofSpeakers.setText(speakersDetails.getName());
        holder.textViewDetailsofSpeakers.setText(speakersDetails.getPost());

        Picasso.with(context).load("https://www.simplifiedcoding.net/wp-content/uploads/2015/10/advertise.png").resize(10,10).into(holder.imageViewspeakersPhoto);
        holder.viewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(position);


            }
        });
        holder.viewGroup.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemClickListener.onItemLongClickListener(position);

                return true;
            }
        });

    }


    @Override
    public int getItemCount() {
        return (null != speakersDetailsList ? speakersDetailsList.size() : 0);
    }

    public interface OnItemClickListener {
        void onClick(int position);

        void onItemLongClickListener(int position);

    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView textViewnamesofSpeakers, textViewDetailsofSpeakers;
        ImageView imageViewspeakersPhoto;
        public View viewGroup;

        public CustomViewHolder(View view) {
            super(view);
            this.textViewnamesofSpeakers = (TextView) view.findViewById(R.id.textViewnamesofSpeakers);
            this.textViewDetailsofSpeakers = (TextView) view.findViewById(R.id.TextViewDetailsofSpeakers);
            this.imageViewspeakersPhoto = (ImageView) view.findViewById(R.id.imageViewspeakersPhoto);
            this.viewGroup = (ViewGroup) view.findViewById(R.id.recycler_viewforspeakersdetail);
        }
    }
}
