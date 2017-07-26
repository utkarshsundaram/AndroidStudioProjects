package com.example.user.newsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.user.newsapp.Adapters.MyRecyclerAdapters;
import com.example.user.newsapp.Models.ArticlesModelData;
import com.example.user.newsapp.Models.HeadlineModelData;
import com.kelltontech.Constants;
import com.kelltontech.ui.IScreen;
import com.kelltontech.utils.ConnectivityUtils;
import com.kelltontech.volley.ext.GsonObjectRequest;
import com.kelltontech.volley.ext.RequestManager;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.user.newsapp.Utilities.Constants.NewsData;
import static com.example.user.newsapp.Utilities.Constants.REQUEST_DATA;
import static com.example.user.newsapp.Utilities.Constants.urltolink;

public class NewsShowActivity extends AppCompatActivity implements IScreen,View.OnClickListener, MyRecyclerAdapters.OnItemClickListener{
    ProgressDialog progressDialog;
    ArrayList<ArticlesModelData>articleslist;
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapters mRecycleAdapters;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_show);
        createProgressDialog();
        getData(REQUEST_DATA);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(NewsShowActivity.this, layoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);


    }


    @Override
    public void updateUi(boolean status, int actionID, Object serviceResponse) {
        switch (actionID) {
            case 1:
                if (serviceResponse instanceof HeadlineModelData)
                {
                    Log.d("resultData", "here");
                    HeadlineModelData headlineModelData=(HeadlineModelData)serviceResponse;
                    articleslist=(ArrayList<ArticlesModelData>)headlineModelData.getAriticles();
                    mRecycleAdapters = new MyRecyclerAdapters(getApplicationContext(),articleslist,this);
                    mRecycleAdapters.notifyDataSetChanged();
                    mRecyclerView.setAdapter(mRecycleAdapters);
                }
        }
    }

    @Override
    public void onEvent(int eventId, Object eventData)
    {

    }

    @Override
    public void getData(int actionID)
    {
        if (!ConnectivityUtils.isNetworkEnabled(this))
        {
            return;
        }
        if (actionID==REQUEST_DATA)
        {
            try
            {
                RequestManager.addRequest(new GsonObjectRequest<HeadlineModelData>(urltolink, new HashMap<String, String>(), null, HeadlineModelData.class, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                      Toast.makeText(NewsShowActivity.this,""+error,Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected void deliverResponse(HeadlineModelData response)
                    {
                        progressDialog.dismiss();
                        updateUi(true,REQUEST_DATA,response);
                    }
                });
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    private void createProgressDialog()
    {
        progressDialog = new ProgressDialog(NewsShowActivity.this, R.style.Theme_AppCompat_Dialog_Alert);
        progressDialog.setMessage(Html.fromHtml("<font color='#00000'>Loading the news </font>"));
        progressDialog.show();

    }

    @Override
    public void onClick(View v)
    {

    }

    @Override
    public void onClick(int position)
    {
        ArticlesModelData articlesModeldata=articleslist.get(position);
        Intent intent=new Intent(this,NewsDetailsActivity.class);
        intent.putExtra(NewsData,articlesModeldata);
        startActivity(intent);

    }

    @Override
    public void onItemLongClickListener(int position)
    {

    }
}
