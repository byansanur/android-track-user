package com.byandev.trackusers.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.byandev.trackusers.Api.ApiEndPoint;
import com.byandev.trackusers.Api.SharedPrefManager;
import com.byandev.trackusers.Api.UtilsApi;
import com.byandev.trackusers.Models.ProfileModel;
import com.byandev.trackusers.R;

import devs.mulham.horizontalcalendar.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

  private Context context;
  private ApiEndPoint mApiServices;
  SharedPrefManager sharedPrefManager;

  private TextView tvNama, tvUsername, tvTgllahir, tvNohp, tvNoktp, tvNoPaspor, tvNoVisa;
  private Toolbar toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);

    context = this;
    mApiServices = UtilsApi.getAPIService();
    sharedPrefManager = new SharedPrefManager(context);

    tvNama = findViewById(R.id.tvNama);
    tvUsername = findViewById(R.id.tvUserName);
    tvTgllahir  = findViewById(R.id.tvTglLahir);
    tvNohp = findViewById(R.id.tvNoHp);
    tvNoktp = findViewById(R.id.tvNoKtp);
    tvNoPaspor = findViewById(R.id.tvNoPasspor);
    tvNoVisa = findViewById(R.id.tvNovisa);

    toolbar = findViewById(R.id.toolbar);
    toolbar.setTitle("Profile");
  }

  @Override
  public void onStart(){
    super.onStart();
  }

  @Override
  public void onResume(){
    super.onResume();
    initData();
  }

  private void initData() {
    mApiServices.profile(
        sharedPrefManager.getSpId(),
        sharedPrefManager.getSpIdPrivileges()
    ).enqueue(new Callback<ProfileModel>() {
      @Override
      public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
        if (response.isSuccessful()) {
          if (response.body().getApiStatus() == 1) {
            tvNama.setText(response.body().getData().getNama());
            tvUsername.setText(response.body().getData().getUsername());
            tvTgllahir.setText(response.body().getData().getTglLahir());
            tvNohp.setText(response.body().getData().getNoHp());
            tvNoktp.setText(response.body().getData().getNoKtp());
            tvNoPaspor.setText(response.body().getData().getNoPasspor());
            tvNoVisa.setText(response.body().getData().getNoVisa());
          }else {
            Toast.makeText(context, "Internet connection", Toast.LENGTH_LONG).show();
          }
        } else {
          Toast.makeText(context, "Kesalahan jaringan", Toast.LENGTH_LONG).show();
        }
      }

      @Override
      public void onFailure(Call<ProfileModel> call, Throwable t) {
        Toast.makeText(context, "not responding : "+t.getMessage(), Toast.LENGTH_LONG).show();
      }
    });
  }

  @Override
  public void onBackPressed(){
    Intent a = new Intent(context, HomeActivity.class);
    startActivity(a);
    finish();
  }

}
