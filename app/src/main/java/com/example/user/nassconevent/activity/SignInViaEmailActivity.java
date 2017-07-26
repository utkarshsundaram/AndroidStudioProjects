package com.example.user.nassconevent.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.text.Html;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.user.nassconevent.R;
import com.example.user.nassconevent.interfaces.Constants;
import com.example.user.nassconevent.model.LoginModel;
import com.example.user.nassconevent.model.OtpModel;
import com.example.user.nassconevent.utilities.GetHashKeyAndGetTimeStamp;
import com.example.user.nassconevent.utilities.RequestBody;
import com.example.user.nassconevent.utilities.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kelltontech.ui.IScreen;
import com.kelltontech.utils.ConnectivityUtils;
import com.kelltontech.volley.ext.JsonRequest;
import com.kelltontech.volley.ext.RequestManager;

import java.sql.Timestamp;
import java.util.HashMap;

import static android.widget.Toast.LENGTH_SHORT;
import static com.example.user.nassconevent.interfaces.ApiClient.loginUrl;
import static com.example.user.nassconevent.interfaces.ApiClient.otpURL;
import static com.example.user.nassconevent.interfaces.Constants.REQUEST_OTP;
import static com.example.user.nassconevent.interfaces.Constants.REQUEST_TO_CREATE_ACCOUNT_AND_lOGIN;

public class SignInViaEmailActivity extends AppCompatActivity implements View.OnClickListener, IScreen {

    EditText editTextFirstName, editTextLastName, editTextEmail;
    Button buttonSubmitForRegistrationViaEmail;

    String emailgetFromUser;
    String firstNameGetFromUser;
    String lastNameGetFromUser;
    ProgressDialog progressDialog;
    int orignalOtp;
    int getOtp;
    EditText editTextOfDialog;
    User newUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_via_email);
        editTextFirstName = (EditText) findViewById(R.id.EditTextfirstname);
        editTextLastName = (EditText) findViewById(R.id.EditTextlastname);
        editTextEmail = (EditText) findViewById(R.id.EditTextemailid);
        buttonSubmitForRegistrationViaEmail = (Button) findViewById(R.id.ButtonSubmitdetails);

        buttonSubmitForRegistrationViaEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == buttonSubmitForRegistrationViaEmail.getId()) {
            firstNameGetFromUser = editTextFirstName.getText().toString();
            lastNameGetFromUser = editTextLastName.getText().toString();
            emailgetFromUser = editTextEmail.getText().toString();
            createProgressDialog();
            getData(REQUEST_OTP);
        }
    }

    @Override
    public void updateUi(boolean status, int actionID, Object serviceResponse) {

        switch (actionID) {
            case Constants.REQUEST_OTP:
                if (serviceResponse instanceof OtpModel) {
                    OtpModel otpModel = (OtpModel) serviceResponse;
                    orignalOtp = otpModel.getData().getOtp();
                    createOtpDialog();
                }
                break;
            case Constants.REQUEST_TO_CREATE_ACCOUNT_AND_lOGIN:
                if (serviceResponse instanceof LoginModel) {
                    LoginModel loginModel = (LoginModel) serviceResponse;
                    Toast.makeText(SignInViaEmailActivity.this, "logged in " + loginModel.getSuccess() + newUser.getEmail(), Toast.LENGTH_LONG).show();
                    Intent goToScreeActivity = new Intent(SignInViaEmailActivity.this, ScreenActivity.class);
                        startActivity(goToScreeActivity);
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

        if (!ConnectivityUtils.isNetworkEnabled(this)) {
            return;
        }
        if (actionID == Constants.REQUEST_OTP) {

            JsonObject jsonObject = new JsonObject();
            try {
                int time = (int) (System.currentTimeMillis());
                Timestamp tsTemp = new Timestamp(time);
                String timeStampInString = tsTemp.toString();
                String hash = new GetHashKeyAndGetTimeStamp().getHash();
                jsonObject.addProperty("hash", hash);
                jsonObject.addProperty("timestamp", timeStampInString);
                jsonObject.addProperty("email", emailgetFromUser);
                jsonObject.addProperty("firstName", firstNameGetFromUser);
                jsonObject.addProperty("lastName", lastNameGetFromUser);
                newUser = new User(firstNameGetFromUser, lastNameGetFromUser, emailgetFromUser);


            } catch (Exception e) {
                e.printStackTrace();
            }
            RequestManager.initializeWith(getApplicationContext(), new RequestManager.Config("data/data/DSD/pics", 5242880, 4));
            RequestManager.addRequest(new JsonRequest<OtpModel>(otpURL, new HashMap<String, String>(), jsonObject.toString(), new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(SignInViaEmailActivity.this, "" + error, LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Response<OtpModel> parseNetworkResponse(NetworkResponse networkResponse) {
                    String jsonString = new String(networkResponse.data);
                    OtpModel response = new Gson().fromJson(jsonString, OtpModel.class);
                    return Response.success(response, HttpHeaderParser.parseCacheHeaders(networkResponse));
                }

                @Override
                protected void deliverResponse(OtpModel response) {
                    progressDialog.dismiss();
                    updateUi(true, Constants.REQUEST_OTP, response);

                }
            });


        }
        if (actionID == REQUEST_TO_CREATE_ACCOUNT_AND_lOGIN) {

            try {

                RequestBody requestBody = new RequestBody(newUser);
                RequestManager.initializeWith(getApplicationContext(), new RequestManager.Config("data/data/DSD/pics", 5242880, 4));
                createProgressDialog();
                RequestManager.addRequest(new JsonRequest<LoginModel>(loginUrl, new HashMap<String, String>(), requestBody.getjsonObjectRequestForLogin().toString(), new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
                    @Override
                    protected Response<LoginModel> parseNetworkResponse(NetworkResponse networkResponse) {
                        String jsonString = new String(networkResponse.data);
                        LoginModel response = new Gson().fromJson(jsonString, LoginModel.class);
                        return Response.success(response, HttpHeaderParser.parseCacheHeaders(networkResponse));
                    }

                    @Override
                    protected void deliverResponse(LoginModel response) {
                        progressDialog.dismiss();
                        updateUi(true, REQUEST_TO_CREATE_ACCOUNT_AND_lOGIN, response);

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void createProgressDialog() {
        progressDialog = new ProgressDialog(SignInViaEmailActivity.this, R.style.Theme_AppCompat_Dialog_Alert);
        progressDialog.setMessage(Html.fromHtml("<font color=#b5aea7>Loading</font>"));

        progressDialog.show();


    }

    public void createOtpDialog() {
        final Dialog dialog = new Dialog(SignInViaEmailActivity.this);
        dialog.setContentView(R.layout.dialog_layout_xml);
        dialog.setTitle("Title...");
        editTextOfDialog = (EditText) dialog.findViewById(R.id.editTextOTP);
        Button dialogButton = (Button) dialog.findViewById(R.id.buttonOk);
        ImageView imageViewCloseDialog = (ImageView) dialog.findViewById(R.id.imageViewCloseId);


        dialog.show();
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOtp = Integer.parseInt(editTextOfDialog.getText().toString());

                if (getOtp == orignalOtp) {
                    dialog.dismiss();
                    getData(REQUEST_TO_CREATE_ACCOUNT_AND_lOGIN);
                    Toast.makeText(SignInViaEmailActivity.this, "We are in Dialog" + getOtp, LENGTH_SHORT).show();
                }
            }
        });

    }


}

