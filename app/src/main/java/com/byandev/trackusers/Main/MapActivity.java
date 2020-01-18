package com.byandev.trackusers.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.byandev.trackusers.Api.ApiEndPoint;
import com.byandev.trackusers.Api.UtilsApi;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

  private GoogleMap mMap;
  private List<RekomModel.Rekom> list = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_map);

    SupportMapFragment mapFragment = (SupportMapFragment)
        getSupportFragmentManager().findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    mMap = googleMap;
    mMap.setMyLocationEnabled(true);
    getAllDataLocationLatLng();
  }

  @Override
  public void onBackPressed(){
    super.onBackPressed();
    Intent i = new Intent(MapActivity.this,KategoriActivity.class);
    startActivity(i);
    finish();
  }

  private void getAllDataLocationLatLng() {
    final ProgressDialog bar = new ProgressDialog(this);
    bar.setMessage("Menampilkan data rekomendasi");
    bar.show();

    ApiEndPoint mApiServices = UtilsApi.getAPIService();
    Call<RekomModel> call = mApiServices.rekomListMap();
    call.enqueue(new Callback<RekomModel>() {
      @Override
      public void onResponse(Call<RekomModel> call, Response<RekomModel> response) {
        if (response.isSuccessful()) {
          bar.dismiss();
          if (response.body().getApiStatus() == 1) {
            list = response.body().getData();
            initMakers(list);
          }
        }
      }

      @Override
      public void onFailure(Call<RekomModel> call, Throwable t) {
        bar.dismiss();
        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
      }
    });
  }

  private void initMakers(List<RekomModel.Rekom> list) {
    for (int i = 0; i < list.size(); i++) {
      LatLng loc = new LatLng(Double.parseDouble(list.get(i).getLat()), Double.parseDouble(list.get(i).getLng()));
      mMap.addMarker(new MarkerOptions().position(loc).title(list.get(i).getNama() + "\n" + list.get(i).getTypeRekom()));
      LatLng latLng = new LatLng(Double.parseDouble(list.get(0).getLat()), Double.parseDouble(list.get(0).getLng()));
      mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng.latitude, latLng.longitude), 11.0f));
    }
  }
}
