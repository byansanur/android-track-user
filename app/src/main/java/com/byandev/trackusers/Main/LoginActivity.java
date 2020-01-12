package com.byandev.trackusers.Main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.byandev.trackusers.Api.ApiEndPoint;
import com.byandev.trackusers.Api.SharedPrefManager;
import com.byandev.trackusers.Api.UtilsApi;
import com.byandev.trackusers.Models.Login;
import com.byandev.trackusers.R;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

  Button btnLogin;
  EditText npm, password;
  TextView link;
  CheckBox eula;
  ProgressDialog loading;
  Context mContext;
  ApiEndPoint mApiService;
  SharedPrefManager sharedPrefManager;
  Integer  uID;
  String noKtp;
  String no_hp;
  Integer idPrivileges;
  String strNpm, strPass;
  String Username, Nama, Tgl_lahir, noVisa, noPasspor, foto;
  TextInputLayout inputPassword;
  LinearLayout lllogin;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_login);

        mContext = this;
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(mContext);

        btnLogin = findViewById(R.id.btnLogin);
        npm = findViewById(R.id.loginNpm);
        password = findViewById(R.id.loginPassword);
        link = findViewById(R.id.llink);
        eula = findViewById(R.id.cbEula);

        loading = new ProgressDialog(this);
        loading.setMessage("Masuk ...");

        lllogin = findViewById(R.id.lllogin);

        inputPassword = findViewById(R.id.inputPassword);
    }

    @Override
    public void onStart() {
        super.onStart();
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, TentangActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(npm.getText())) {
                    npm.setError("Required");
                } else if (TextUtils.isEmpty(password.getText())){
                    password.setError("Required");
                } else if (!eula.isChecked()) {
                    Toast toast = Toast.makeText(mContext, "Ceklist syarat & Ketentuan",
                        Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0,0);
                    toast.show();
                } else {
                    loading.show();
                    requestLogin();
                }
            }
        });
        if (sharedPrefManager.getSPSudahLogin()) {
            startActivity(new Intent(mContext, HomeActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

    }

    private void requestLogin() {
      mApiService.requestLogin(npm.getText().toString(), password.getText().toString()).enqueue(new Callback<Login>() {
        @Override
        public void onResponse(Call<Login> call, Response<Login> response) {
          if (response.isSuccessful()) {
            if (response.body().getApiStatus() == 1) {
              loading.dismiss();
              Integer userId = response.body().getData().getId();
              uID = userId;
              Nama = response.body().getData().getNama();
              Username = response.body().getData().getUsername();
              Tgl_lahir = response.body().getData().getTglLahir();
              noVisa = response.body().getData().getNoVisa();
              noPasspor = response.body().getData().getNoPasspor();
//              foto = response.body().getData().getFoto();
              noKtp = response.body().getData().getNoKtp();
              no_hp = response.body().getData().getNoHp();
              idPrivileges = response.body().getData().getIdPrivileges();
              Log.d("User Id:","" + userId);

              sharedPrefManager.saveSPInt(SharedPrefManager.SP_ID, uID);
              sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, Nama);
              sharedPrefManager.saveSPString(SharedPrefManager.SP_USERNAME, Username);
              sharedPrefManager.saveSPString(SharedPrefManager.SP_TGL_LAHIR, Tgl_lahir);
              sharedPrefManager.saveSPString(SharedPrefManager.SP_NO_VISA, noVisa);
              sharedPrefManager.saveSPString(SharedPrefManager.SP_NO_PASSPOR, noPasspor);
//              sharedPrefManager.saveSPString(SharedPrefManager.SP_FOTO, foto);
              sharedPrefManager.saveSPString(SharedPrefManager.SP_NO_KTP, noKtp);
              sharedPrefManager.saveSPString(SharedPrefManager.SP_NO_HP, no_hp);
              sharedPrefManager.saveSPInt(SharedPrefManager.SP_ID_PRIVILEGES, idPrivileges);
              sharedPrefManager.saveSPString(SharedPrefManager.SP_TOKEN, response.body().getData().getToken());
              sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);

//              Log.d("Token login",sharedPrefManager.getSpToken());

              startActivity(new Intent(mContext, HomeActivity.class)
                  .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                  .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
              finish();
            } else {
              loading.dismiss();
              Toast.makeText(mContext, response.body().getApiMessage(), Toast.LENGTH_SHORT).show();
            }
          } else {
            loading.dismiss();
            Toast.makeText(mContext, "Koneksi Bermasalah", Toast.LENGTH_SHORT).show();
          }
        }

        @Override
        public void onFailure(Call<Login> call, Throwable t) {
          loading.dismiss();
          Toast.makeText(mContext, "Not Responding"+t.getMessage(), Toast.LENGTH_SHORT).show();
        }
      });
    }

}