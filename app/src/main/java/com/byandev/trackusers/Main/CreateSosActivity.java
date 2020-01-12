package com.byandev.trackusers.Main;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.byandev.trackusers.Api.ApiEndPoint;
import com.byandev.trackusers.Api.SharedPrefManager;
import com.byandev.trackusers.Api.UtilsApi;
import com.byandev.trackusers.Models.SosCreateModel;
import com.byandev.trackusers.R;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateSosActivity extends AppCompatActivity {

  private EditText edtPesan;
  private Button btSend;
  private Context context;
  ApiEndPoint mApiServices;
  SharedPrefManager sharedPrefManager;
  private Toolbar toolbar;
  private ProgressBar loading;

  LocationManager locMgr;
  Location location;
  String provider;
//  double lat, lng;
  String lati, longi;

  private Integer idSos;

  private SosCreateModel.DataCreate s;

  @RequiresApi(api = Build.VERSION_CODES.M)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_sos);

    context = this;
    mApiServices = UtilsApi.getAPIService();
    sharedPrefManager = new SharedPrefManager(context);
    edtPesan = findViewById(R.id.edtpesan);
    btSend = findViewById(R.id.btnsend);
    toolbar = findViewById(R.id.toolbar);
    toolbar.setTitle("Sos");
    loading = findViewById(R.id.loading);


    initLatLng();
      listener();


  }

  @RequiresApi(api = Build.VERSION_CODES.M)
  private void initLatLng() {
    locMgr = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
    Criteria c = new Criteria();
    provider = locMgr.getBestProvider(c, false);
    if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
      // TODO: Consider calling
      //    Activity#requestPermissions
      // here to request the missing permissions, and then overriding
      //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
      //                                          int[] grantResults)
      // to handle the case where the user grants the permission. See the documentation
      // for Activity#requestPermissions for more details.
      return;
    }
    location = locMgr.getLastKnownLocation(provider);
    if (location != null) {
      double lat = location.getLatitude();
      double lng = location.getLongitude();
//      lati.equals(lat);
//      longi.equals(lng);
      lat = Double.parseDouble(lati);
      lng = Double.parseDouble(longi);
    }
  }

  private void listener() {
    btSend.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (TextUtils.isEmpty(edtPesan.getText())) {
          edtPesan.setError("Required");
        } else {
          loading.setVisibility(View.VISIBLE);
          btSend.setVisibility(View.GONE);
          createSOS();
        }
      }
    });
  }

  private void createSOS() {
    mApiServices.sosCreate(
        edtPesan.getText().toString(),
        sharedPrefManager.getSpId(),
        lati, longi
    ).enqueue(new Callback<SosCreateModel>() {
      @Override
      public void onResponse(Call<SosCreateModel> call, Response<SosCreateModel> response) {
        loading.setVisibility(View.GONE);
        if (response.isSuccessful()) {
          if (response.body().getApiStatus() == 1) {
//            idSos = response.body().getData().getId();
            s = response.body().getData();
            Toast.makeText(context, "Berhasil meminta bantuan", Toast.LENGTH_LONG).show();
            finish();
//            Intent a = new Intent(context, DetailSosActivity.class);
//            a.putExtra("id", idSos);
//            context.startActivity(a);
          } else {
            loading.setVisibility(View.GONE);
            Toast.makeText(context, "Gagal meminta bantuan", Toast.LENGTH_SHORT).show();
          }
        }
        else {
          loading.setVisibility(View.GONE);
          Toast.makeText(context, "Gagal kesalahan jaringan", Toast.LENGTH_SHORT).show();
        }

      }

      @Override
      public void onFailure(Call<SosCreateModel> call, Throwable t) {
        loading.setVisibility(View.GONE);
        Toast.makeText(context, "Gagal Kesalahan server", Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override
  public void onStart() {
    super.onStart();
  }

  @Override
  public void onBackPressed(){
//    super.onBackPressed();
    AlertDialog.Builder ad  = new AlertDialog.Builder(context);
    ad.setMessage("Batal meminta bantuan ?").setCancelable(true)
        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            Intent a = new Intent(context, HomeActivity.class);
            startActivity(a);
            finish();
          }
        }).show();
  }

}
