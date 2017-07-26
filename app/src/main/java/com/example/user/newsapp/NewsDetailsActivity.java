package com.example.user.newsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.user.newsapp.Models.ArticlesModelData;
import com.squareup.picasso.Picasso;

import static com.example.user.newsapp.Utilities.Constants.NewsData;

public class NewsDetailsActivity extends AppCompatActivity {
WebView webViewForNewsDetails;
    ImageView imageViewForNewsPhoto;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        webViewForNewsDetails=(WebView)findViewById(R.id.webViewDetailsofNews);
        //imageViewForNewsPhoto=(ImageView)findViewById(R.id.imageViewOfNewsDetails);
        Intent intent=getIntent();
        if(intent!=null)
        {

            final ArticlesModelData articlesModelData=getIntent().getParcelableExtra(NewsData);
             webViewForNewsDetails.loadUrl(articlesModelData.getUrl());
             webViewForNewsDetails.setWebViewClient(new CustomWebViewClient());
           // Picasso.with(NewsDetailsActivity.this).load(articlesModelData.getUrlToImage()).resize(100,100).into(imageViewForNewsPhoto);
        }

    }

    class CustomWebViewClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon)
        {
            progressDialog = new ProgressDialog(NewsDetailsActivity.this, R.style.Theme_AppCompat_Dialog_Alert);
            progressDialog.setMessage(Html.fromHtml("<font color='#00000'>Loading.... </font>"));
            progressDialog.show();
        }

        @Override
        public void onPageFinished(WebView view, String url)
        {
            progressDialog.dismiss();
        }
    }
   // private void createProgressDialog()
   // {
        //progressDialog = new ProgressDialog(NewsDetailsActivity.this, R.style.Theme_AppCompat_Dialog_Alert);
       // progressDialog.setMessage(Html.fromHtml("<font color='#00000'>Loading .... </font>"));
        //progressDialog.show();

   // }
}
