package com.example.user.nassconevent.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.text.Html;
import com.example.user.nassconevent.R;
import com.example.user.nassconevent.interfaces.Constants;
import com.example.user.nassconevent.model.SpeakersDataModel;

public class SpeakersdetailsActivity extends AppCompatActivity {

    Button buttonforBiodataofspeakers;
    Button buttonforSpeakerevents;
    TextView textviewNameofspeakers;
    TextView textviewCompanynameofspeakers;
    TextView textviewDescriptionofthespeakers;
    Button buttonForeventsofspeakers;
    //String nameofspeakers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speakersdetails);
        buttonforBiodataofspeakers = (Button) findViewById(R.id.Buttonforbiodata);
        buttonforSpeakerevents = (Button) findViewById(R.id.buttonforevents);
        textviewNameofspeakers = (TextView) findViewById(R.id.textViewnamesofSpeakers);
        textviewDescriptionofthespeakers = (TextView) findViewById(R.id.Textviewforsfullspeakerdetail);
        buttonForeventsofspeakers=(Button)findViewById(R.id.buttonforevents);
        textviewCompanynameofspeakers = (TextView) findViewById(R.id.TextViewDetailsofSpeakers);
        Intent intent=getIntent();
        if(intent!=null)
        {
            final SpeakersDataModel dataModel=getIntent().getParcelableExtra(Constants.speakerListKey);

            textviewCompanynameofspeakers.setText(dataModel.getPost().toString());
            // nameofspeakers=textviewNameofspeakers.getText().toString();
                //if (nameofspeakers.indexOf('A')==1)
                //{
                // textviewNameofspeakers.setText(nameofspeakers.toString());
                   // textviewNameofspeakers.setTextColor(Color.BLUE);
                //}
            textviewNameofspeakers.setText(dataModel.getName().toString());
            buttonforBiodataofspeakers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    buttonforBiodataofspeakers.setBackgroundColor(Integer.parseInt(Constants.colourbrown));
                   textviewDescriptionofthespeakers.setText(dataModel.getDescription().toString());
                }
            });
        }

    buttonForeventsofspeakers.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            textviewDescriptionofthespeakers.setText("no events available");
        }
    });
    }
}
