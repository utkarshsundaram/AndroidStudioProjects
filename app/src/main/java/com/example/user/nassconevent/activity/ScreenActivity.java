package com.example.user.nassconevent.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.user.nassconevent.interfaces.Constants;
import com.example.user.nassconevent.R;
import com.example.user.nassconevent.model.SpeakerListModel;
import com.example.user.nassconevent.model.SpeakersDataModel;
import com.example.user.nassconevent.utilities.GetHashKeyAndGetTimeStamp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.kelltontech.ui.IScreen;
import android.text.Html;
import com.kelltontech.utils.ConnectivityUtils;
import com.kelltontech.volley.ext.GsonObjectRequest;
import com.kelltontech.volley.ext.RequestManager;

import java.util.ArrayList;
import java.util.HashMap;
import static android.R.attr.button;
import static com.example.user.nassconevent.interfaces.Constants.DATA_REQUEST;

public class ScreenActivity extends AppCompatActivity implements IScreen {

    ArrayList<SpeakersDataModel> arrayListSpeakerData;
    private final String TAG = ScreenActivity.class.getSimpleName();
    TextView textViewforSpeakerslist;
    ProgressDialog progressDialog;

    GetHashKeyAndGetTimeStamp getHashKeyAndGetTimeStamp = new GetHashKeyAndGetTimeStamp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
       builder.setTitle(Html.fromHtml("<font color='#000000'>NasscomEvent</font>")).setMessage(Html.fromHtml("<font color='#000000'>Do you want to get log out?</font>"));

               builder .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();

        alert.show();
        setContentView(R.layout.activity_screen);
        textViewforSpeakerslist = (TextView) findViewById(R.id.textViewSpeakers);
        Log.d(Constants.TAG, "getData --> " + getHashKeyAndGetTimeStamp.getHash());
        Log.d(Constants.TAG, "getTime" + getHashKeyAndGetTimeStamp);

        textViewforSpeakerslist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createProgressDialog();
                getData(DATA_REQUEST);

            }
        });

    }

    @Override
    public void updateUi(boolean status, int actionID, Object serviceResponse) {

        switch (actionID) {
            case DATA_REQUEST:
                if (serviceResponse instanceof SpeakerListModel) {
                    SpeakerListModel speakerListModel = (SpeakerListModel) serviceResponse;

                    Log.d("resultData", "here");


                    arrayListSpeakerData = (ArrayList<SpeakersDataModel>) speakerListModel.getSpeakers_data();


                    Intent intentGoToSpeakerListActivity = new Intent(this, SpeakersListActivity.class);
                    intentGoToSpeakerListActivity.putParcelableArrayListExtra(Constants.speakerListKey,arrayListSpeakerData);
                    startActivity(intentGoToSpeakerListActivity);
                }
                break;
            default:
        }
    }

    @Override
    public void onEvent(int eventId, Object eventData) {

    }

    @Override
    public void getData(int actionID) {
        Log.d(Constants.TAG, "getData --> " + getHashKeyAndGetTimeStamp.getHash() + " == " + getHashKeyAndGetTimeStamp.getTimeStamp());

        if (!ConnectivityUtils.isNetworkEnabled(this)) {
            return;
        }
        if (actionID == DATA_REQUEST) {
            //JsonObject jsonObject = new JsonObject();

            try {

               // String hash="4193bf9cde3fd5be82c36b4b97e95d4f";    new HashMap<String, String>()
                //String timestamp="1495723203";
                //String conference_id="20300";
               //jsonObject.addProperty("hash", hash);
                //jsonObject.addProperty("timestamp",timestamp);
               //jsonObject.addProperty("conference_id",conference_id);
               // HashMap<String,String> header = new HashMap<>();
                //header.put("hash", hash);
                //header.put("timestamp",timestamp);
                //header.put("conference_id",conference_id);
                String url = "http://test.kelltontech.net/eventengine/getspeakers";
                url = url + "?conference_id=20300";
               url = url + "&timestamp=" + "1495723203";
                url = url + "&hash=4193bf9cde3fd5be82c36b4b97e95d4f";

                RequestManager.addRequest(new GsonObjectRequest<SpeakerListModel>(url,new HashMap<String, String>(),null,SpeakerListModel.class, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse");

                    }
                }) {
                    @Override
                    protected void deliverResponse(SpeakerListModel response)
                    {
                        progressDialog.dismiss();
                        Log.e(TAG, "deliverResponse");
                        updateUi(true, DATA_REQUEST, response);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void createProgressDialog() {
        progressDialog = new ProgressDialog(ScreenActivity.this, R.style.Theme_AppCompat_Dialog_Alert);
        progressDialog.setMessage(Html.fromHtml("<font color='#00000'>Loading the speakers data</font>"));
        progressDialog.show();


    }
}
