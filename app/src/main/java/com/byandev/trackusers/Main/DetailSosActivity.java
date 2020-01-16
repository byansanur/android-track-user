package com.byandev.trackusers.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.byandev.trackusers.Api.ApiEndPoint;
import com.byandev.trackusers.Api.SharedPrefManager;
import com.byandev.trackusers.Api.UtilsApi;
import com.byandev.trackusers.MainFragment.FragmentDetailRekom;
import com.byandev.trackusers.MainFragment.FragmentDetailSos;
import com.byandev.trackusers.Models.DetailSosModel;
import com.byandev.trackusers.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSosActivity extends AppCompatActivity {

  private Context context;
  private ApiEndPoint mApiServices;
  SharedPrefManager sharedPrefManager;

  private Integer id;
  public Integer getId() {
    return id;
  }


  private Toolbar toolbar;
//  private TextView tanggal, nama, pesan, noktp, nohp, novisa, nopasspor;

//  private DetailSosModel detailSosModel = new DetailSosModel();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail_sos);

    context = this;
    mApiServices = UtilsApi.getAPIService();
    sharedPrefManager = new SharedPrefManager(context);

    toolbar = findViewById(R.id.toolbar);
    id = getIntent().getIntExtra("id", 0);

    toolbar.setTitle("Detail");

    loadFragment(new FragmentDetailSos());

  }

  private void loadFragment(FragmentDetailSos fragment) {
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.replace(R.id.frame_container, fragment);
    ft.addToBackStack(null);
    ft.commitAllowingStateLoss();
  }


  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
  }

}
