package com.example.user.nassconevent.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.user.nassconevent.R;
import com.example.user.nassconevent.adapter.MyRecyclerAdapters;
import com.example.user.nassconevent.interfaces.Constants;
import com.example.user.nassconevent.model.SpeakersDataModel;

import java.util.ArrayList;

public class SpeakersListActivity extends AppCompatActivity implements View.OnClickListener, MyRecyclerAdapters.OnItemClickListener {


    private RecyclerView mRecyclerView;
    private MyRecyclerAdapters mRecycleAdapters;
    private Intent sendthespeakersdata;
    ImageView Imageviewnexticon;

    private ArrayList<SpeakersDataModel> speakersDetailsArrayList;
    private int getPostionOFCurrentspeakers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sendthespeakersdata = getIntent();
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_speakers_list);
        Intent intent = getIntent();
        speakersDetailsArrayList = intent.getParcelableArrayListExtra(Constants.speakerListKey);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);      //It defines the layout of the recycler view
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(SpeakersListActivity.this, layoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecycleAdapters = new MyRecyclerAdapters(getApplicationContext(),speakersDetailsArrayList,this);
        mRecyclerView.setAdapter(mRecycleAdapters);
        Imageviewnexticon = (ImageView) findViewById(R.id.imageOfSpeakerId);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onClick(int position) {
        SpeakersDataModel dataModel=speakersDetailsArrayList.get(position);
        Intent intent = new Intent(this, SpeakersdetailsActivity.class);
        intent.putExtra(Constants.speakerListKey, dataModel);
        startActivity(intent);

    }

    @Override
    public void onItemLongClickListener(int position) {

    }
}