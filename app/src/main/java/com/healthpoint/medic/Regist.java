package com.healthpoint.medic;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.healthpoint.medic.database.DatabaseHelper;
import com.healthpoint.medic.model.AccessToken;
import com.healthpoint.medic.model.User;
import com.healthpoint.medic.network.APIClient;
import com.healthpoint.medic.network.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Regist extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "Regist";

    private final AppCompatActivity activity = Regist.this;
    Button regist;
    Button back;
    RelativeLayout relativeLayout;
    TextInputLayout textInputLayoutUsername;
    TextInputLayout textInputLayoutPassword;
    TextInputEditText editTextusername;
    TextInputEditText editTextpassword;
    InputValidation inputValidation;
    DatabaseHelper databaseHelper;
    User user;
    APIInterface service;
    Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        mContext = this;
        service = APIClient.createService(APIInterface.class);
        initViews();
        initListeners();
        initObjects();
    }
    private void initViews() {
        relativeLayout = (RelativeLayout) findViewById(R.id.relative);
        regist = (Button) findViewById(R.id.btnregister);
        back = (Button) findViewById(R.id.back);

        textInputLayoutUsername = (TextInputLayout) findViewById(R.id.textInputLayoutUsername);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        editTextusername = (TextInputEditText) findViewById(R.id.username);
        editTextpassword = (TextInputEditText) findViewById(R.id.password);
    }
    private void initListeners() {
        regist.setOnClickListener(this);

        back.setOnClickListener(this);
    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();

    }
    private void kirimAPI(){
        String username = editTextusername.getText().toString();
        String password = editTextpassword.getText().toString();

        if(username.isEmpty()){
            editTextusername.setError("masukkan username");
            editTextusername.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextpassword.setError("masukkan password");
            editTextpassword.requestFocus();
            return;
        }
        if(password.length()<8){
            editTextpassword.setError("Password harus terdiri dari 8 karakter ");
            editTextpassword.requestFocus();
            return;
        }

        Call<AccessToken> call =  service.register(username,password);
        call.enqueue(new Callback<AccessToken>() {

            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                if(response.isSuccessful()){
                    Log.i(TAG,"onResponse:Berhasil");
                    startActivity(new Intent(Regist.this, Login.class));
                    Toast.makeText(Regist.this,"Register Berhasil",Toast.LENGTH_SHORT).show();

                    finish();
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Log.w(TAG, "onFailure: " + t.getMessage());
                Toast.makeText(Regist.this,"Register Gagal",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnregister:
                kirimAPI();
//                postDataToSQLite();
                break;

            case R.id.back:
                finish();
                break;
        }
    }
    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(editTextusername, textInputLayoutUsername, getString(R.string.error_message_username))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(editTextpassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }

        if (!databaseHelper.checkUser(editTextusername.getText().toString().trim())) {
            user.setPassword(editTextpassword.getText().toString().trim());
            user.getUsername();
            databaseHelper.addUser(user);

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(relativeLayout, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(relativeLayout, getString(R.string.error_username_exists), Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        editTextpassword.setText(null);
    }
}
