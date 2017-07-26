package com.example.user.nassconevent.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.user.nassconevent.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textViewLoginViaLinkedin;
    TextView textViewLoginViaEmail;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textViewLoginViaLinkedin = (TextView) findViewById(R.id.textViewLoginViaLinkedInId);
        textViewLoginViaEmail = (TextView) findViewById(R.id.textViewLoginViaEmailId);
        textViewLoginViaEmail.setOnClickListener(this);
        textViewLoginViaLinkedin.setOnClickListener(this);
        seTextOntheLoginViaLinkedTextView();
    }

    public void seTextOntheLoginViaLinkedTextView() {


        String formatedText = "<font color=#ffffff>Login</font> <font color=#b5aea7> via LinkedIn</font>";
        if (Build.VERSION.SDK_INT >= 24) {

            textViewLoginViaLinkedin.setText(Html.fromHtml(formatedText, Html.FROM_HTML_MODE_LEGACY));
        } else

        {
            textViewLoginViaLinkedin.setText(Html.fromHtml(formatedText));
        }
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == textViewLoginViaEmail.getId())
        {

            Intent intentGoToEmail=new Intent(LoginActivity.this,SignInViaEmailActivity.class);
            startActivity(intentGoToEmail);
        }
        if (view.getId() == textViewLoginViaLinkedin.getId()) {

        }
    }
}
