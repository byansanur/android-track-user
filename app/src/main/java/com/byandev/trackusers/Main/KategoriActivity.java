package com.byandev.trackusers.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.byandev.trackusers.Api.ApiEndPoint;
import com.byandev.trackusers.Api.SharedPrefManager;
import com.byandev.trackusers.Api.UtilsApi;
import com.byandev.trackusers.R;

public class KategoriActivity extends AppCompatActivity {

  private CardView card1, card2, card3;
  private ImageView backHome, map;

  Context context;
  ApiEndPoint mApiService;
  SharedPrefManager sharedPrefManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_kategori);

    context = this;
    mApiService = UtilsApi.getAPIService();
    sharedPrefManager = new SharedPrefManager(this);

    backHome = findViewById(R.id.backHome);
    map = findViewById(R.id.icMapActivity);
    card1 = findViewById(R.id.rekHotel);
    card2 = findViewById(R.id.rekKuliner);
    card3 = findViewById(R.id.rekTravel);

  }

  @Override
  public void onResume(){
    super.onResume();
    listener();
  }

  private void listener() {
    card1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // list hotel
        startActivity(new Intent(context, ListHotelActivity.class));
        finish();
      }
    });
    card2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // list kuliner
        startActivity(new Intent(context, ListKulinerActivity.class));
        finish();
      }
    });
    card3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // list travel
        startActivity(new Intent(context, ListTravelActivity.class));
        finish();
      }
    });
    backHome.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(context, HomeActivity.class));
        finish();
      }
    });
    map.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(context, MapActivity.class));
        finish();
      }
    });
  }

  @Override
  public void onBackPressed(){
    super.onBackPressed();
    startActivity(new Intent(context, HomeActivity.class));
    finish();
  }

}
