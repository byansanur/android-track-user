package com.byandev.trackusers.Main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.byandev.trackusers.Api.ApiEndPoint;
import com.byandev.trackusers.Api.UtilsApi;
import com.byandev.trackusers.Models.DetailSosModel;
import com.byandev.trackusers.Models.RekomModel;
import com.byandev.trackusers.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapActivitySos extends AppCompatActivity implements OnMapReadyCallback {

  private GoogleMap mMap;
  private DetailSosModel.DataDetailSos sos = new DetailSosModel.DataDetailSos();
  private Integer idSos;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_map);

    SupportMapFragment mapFragment = (SupportMapFragment)
        getSupportFragmentManager().findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);
    idSos = ((DetailSosActivity)getParent()).getIdSos();
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    mMap = googleMap;
    getAllDataLocationLatLng();
  }

  @Override
  public void onBackPressed(){
    super.onBackPressed();
    Intent i = new Intent(MapActivitySos.this,DetailSosActivity.class);
    startActivity(i);
    finish();
  }

  private void getAllDataLocationLatLng() {
    final ProgressDialog bar = new ProgressDialog(this);
    bar.setMessage("Menampilkan data rekomendasi");
    bar.show();

    ApiEndPoint mApiServices = UtilsApi.getAPIService();
    Call<DetailSosModel> call = mApiServices.detailSos(idSos);
    call.enqueue(new Callback<DetailSosModel>() {
      @Override
      public void onResponse(Call<DetailSosModel> call, Response<DetailSosModel> response) {
        if (response.isSuccessful()) {
          bar.dismiss();
          if (response.body().getApiStatus() == 1) {
            sos = response.body().getData();
            initMakers(sos);
          }
        }
      }

      @Override
      public void onFailure(Call<DetailSosModel> call, Throwable t) {

      }
    });
//    ApiEndPoint mApiServices = UtilsApi.getAPIService();
//    Call<DetailSosModel> call = mApiServices.detailSos(idSos);
//    call.enqueue(new Callback<DetailSosModel>() {
//      @Override
//      public void onResponse(Call<DetailSosModel> call, Response<DetailSosModel> response) {
//        if (response.isSuccessful()) {
//          bar.dismiss();
//          if (response.body().getApiStatus() == 1) {
//            list = response.body().getData();
//            initMakers(list);
//          }
//        }
//      }
//
//      @Override
//      public void onFailure(Call<DetailSosModel> call, Throwable t) {
//        bar.dismiss();
//        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
//      }
//    });
  }

  private void initMakers(DetailSosModel.DataDetailSos list) {
    for (int i = 0; i != list.getId(); i++) {
      LatLng loc = new LatLng(Double.parseDouble(sos.getLat()), Double.parseDouble(sos.getLng()));
      mMap.addMarker(new MarkerOptions().position(loc).title(sos.getNama()));
//      LatLng loc = new LatLng(Double.parseDouble(list.getLat()), Double.parseDouble(list.getLng()));
//      mMap.addMarker(new MarkerOptions().position(loc).title(list.getNama()));
//      LatLng latLng = new LatLng(Double.parseDouble(list.getLat()), Double.parseDouble(list.getLng()));
//      mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng.latitude, latLng.longitude), 11.0f));
    }
  }
}
