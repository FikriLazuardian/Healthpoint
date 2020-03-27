package com.healthpoint.medic;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.healthpoint.medic.model.AccessToken;
import com.healthpoint.medic.model.ApiError;
import com.healthpoint.medic.network.APIClient;
import com.healthpoint.medic.network.APIInterface;
import com.healthpoint.medic.tokenmanager.TokenManager;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Login";

    TextInputEditText username, password;
    @BindView(R.id.rootLayout)
    RelativeLayout rootLayout;
    @BindView(R.id.btnlogin)
    Button loginbtn;
//    TextView registbtn;
   // DatabaseHelper databaseHelper;
    InputValidation inputValidation;
    TextInputLayout textInputLayoutUsername, textInputLayoutPassword;
    TokenManager tokenManager;
    APIInterface service;
    AwesomeValidation validator;
    Call<AccessToken> call;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    CheckBox saveLoginCheckbox;
    Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        preferences = getSharedPreferences("loginref",MODE_PRIVATE);
        saveLoginCheckbox= (CheckBox) findViewById(R.id.cbSaveLogin);
        editor = preferences.edit();
        service = APIClient.createService(APIInterface.class);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        validator = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);
        initViews();
        initListeners();
        if(tokenManager.getToken().getAccessToken() != null){
                startActivity(new Intent(Login.this, Dashboard.class));
                finish();

        }
        saveLogin=preferences.getBoolean("savelogin",true);
        if (saveLogin==true){
            username.setText(preferences.getString("username",null));
            password.setText(preferences.getString("password",null));
        }


    }

    private void initViews() {
        username = (TextInputEditText) findViewById(R.id.txtusername);
        password = (TextInputEditText) findViewById(R.id.txtpassword);
//        registbtn = (TextView) findViewById(R.id.txtregister);
        textInputLayoutUsername = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        loginbtn = (Button) findViewById(R.id.btnlogin);

    }

    private void initListeners() {
        loginbtn.setOnClickListener(this);
//        registbtn.setOnClickListener(this);
    }

   // private void initObjects() {
        //databaseHelper = new DatabaseHelper(this);
      //  inputValidation = new InputValidation(this);
    //}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnlogin:
                authentication();
          //      verifyFromSQLite();
                break;
//            case R.id.txtregister:
//                Intent regist = new Intent(Login.this, Regist.class);
//                startActivity(regist);
//                break;
        }
    }

   // private void verifyFromSQLite() {
     //   if (!inputValidation.isInputEditTextFilled(username, textInputLayoutUsername, getString(R.string.error_message_username))) {
        //    return;
       // }
        //if (!inputValidation.isInputEditTextNip(username, textInputLayoutUsername, getString(R.string.error_message_username))) {
        //    return;
       // }
       // if (!inputValidation.isInputEditTextFilled(password, textInputLayoutPassword, getString(R.string.error_message_username))) {
         //   return;
        //}
       // if (databaseHelper.checkUser(username.getText().toString().trim(),
           //     password.getText().toString().trim())) {
           // Intent accountsIntent = new Intent(Login.this, Dashboard.class);
         //   accountsIntent.putExtra("EMAIL", username.getText().toString().trim());
       //     emptyInputText();
     //       startActivity(accountsIntent);
   //     } else {
            // Snack Bar to show success message that record is wrong
      //      Snackbar.make(rootLayout, getString(R.string.error_valid_username_password), Snackbar.LENGTH_LONG).show();
    //    }
  //  }

    private void emptyInputText() {
        username.setText(null);
        password.setText(null);
    }

    private void authentication() {
        /*
        // langsung masuk ga ada pengecekan
        Intent intent = new Intent(Login.this, Dashboard.class);
        Toast.makeText(Login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(Login.this, HomepageJumantik.class);
        startActivity(intent);
        finish();
        */

        // /*
        final String usernameval = username.getText().toString();
        final String userpassval = password.getText().toString();
        username.setError(null);
        password.setError(null);

        validator.clear();
        if (saveLoginCheckbox.isChecked()){
            editor.putBoolean("savelogin",true);
            editor.putString("username",usernameval);
            editor.putString("password",userpassval);
            editor.commit();
        }

        if (validator.validate()) {
            call = service.loginRequest(usernameval, userpassval);
            call.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                    if (response.isSuccessful()) {
                        String msg = response.body().getMessage();
                        if (msg != null) { // klo msg null, berarti gda data loginny jd gagal login
                            Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();
                        } else {
                            tokenManager.saveToken(response.body());
                            Bundle bundle = new Bundle();
                            bundle.putInt("id_pengguna", response.body().getIdPengguna());
                            bundle.putString("username", response.body().getUsername());
                            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                            intent.putExtras(bundle);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                            Toast.makeText(Login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } else {
                        if (response.code() == 422) {
                            handleErrors(response.errorBody());
                        }
                        if (response.code() == 401) {
                            ApiError apiError = Utils.converErrors(response.errorBody());
                            Toast.makeText(Login.this, apiError.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {
                    Log.w(TAG, "onFailure: " + t.getMessage());
                    Toast.makeText(Login.this, "Login Gagal", Toast.LENGTH_SHORT).show();

                }
            });
        }
// */
    }
    private void handleErrors(ResponseBody response) {

        ApiError apiError = Utils.converErrors(response);

        for (Map.Entry<String, List<String>> error : apiError.getErrors().entrySet()) {
            if (error.getKey().equals("username")) {
                username.setError(error.getValue().get(0));
            }
            if (error.getKey().equals("password")) {
                password.setError(error.getValue().get(0));
            }
        }

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(call != null){
            call.cancel();
            call = null;
        }
    }
}


