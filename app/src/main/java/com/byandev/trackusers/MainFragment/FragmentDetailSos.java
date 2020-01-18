package com.byandev.trackusers.MainFragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.byandev.trackusers.Api.ApiEndPoint;
import com.byandev.trackusers.Api.SharedPrefManager;
import com.byandev.trackusers.Api.UtilsApi;
import com.byandev.trackusers.Main.DetailSosActivity;
import com.byandev.trackusers.Main.MapActivity;
import com.byandev.trackusers.Models.DetailSosModel;
import com.byandev.trackusers.R;
import com.google.android.gms.common.api.Api;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDetailSos extends Fragment {

  private TextView tanggal, nama, pesan, noktp, nohp, novisa, nopasspor;
  private String sNohp;

//  private Button btCall;
  private Context context;
  private ApiEndPoint mApiServices;
  private SharedPrefManager sharedPrefManager;

  private Integer idSos;

  public FragmentDetailSos(){

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle s){
    View view = inflater.inflate(R.layout.fragment_detail_sos, container, false);

    context = getContext();
    mApiServices = UtilsApi.getAPIService();
    sharedPrefManager = new SharedPrefManager(context);

    tanggal = view.findViewById(R.id.tvCreatedAt);
    nama = view.findViewById(R.id.tvNama);
    pesan = view.findViewById(R.id.tvPesan);
    noktp = view.findViewById(R.id.tvKtp);
    nohp = view.findViewById(R.id.tvNoHp);
    novisa = view.findViewById(R.id.tvNovisa);
    nopasspor = view.findViewById(R.id.tvNoPasspor);
//    btCall = view.findViewById(R.id.callBtn);

    idSos = ((DetailSosActivity) Objects.requireNonNull(getActivity())).getIdSos();
    return view;
  }

  private void initNoHp() {
    nohp.setText(sNohp);
  }

  @Override
  public void onResume(){
    super.onResume();
    data();
  }

  @Override
  public void onStart(){
    super.onStart();
//    btCall.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        String phoneNumber = sNohp;
//        Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
//        startActivity(dialPhoneIntent);
//      }
//    });
  }


  private void data() {
    mApiServices.detailSos(idSos).enqueue(new Callback<DetailSosModel>() {
      @Override
      public void onResponse(Call<DetailSosModel> call, Response<DetailSosModel> response) {
        if (response.isSuccessful()) {
          if (response.body().getApiStatus() == 1) {
              nama.setText(response.body().getData().getNama());
              pesan.setText(response.body().getData().getMessage());
              noktp.setText(response.body().getData().getNoKtp());
              sNohp = "+62 "+ response.body().getData().getNoHp();
              novisa.setText(response.body().getData().getNoVisa());
              nopasspor.setText(response.body().getData().getNoPasspor());
              tanggal.setText(response.body().getData().getCreatedAt());

              Log.d("Nomor hp", sNohp);
            initNoHp();

          } else {
            Toast.makeText(context, "Koneksi bermasalah", Toast.LENGTH_LONG).show();
          }
        } else {
          Toast.makeText(context, "Kesalahan server", Toast.LENGTH_LONG).show();
        }
      }

      @Override
      public void onFailure(Call<DetailSosModel> call, Throwable t) {
        Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
      }
    });
  }
}
