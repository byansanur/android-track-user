package com.byandev.trackusers.MainFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.byandev.trackusers.Api.ApiEndPoint;
import com.byandev.trackusers.Api.SharedPrefManager;
import com.byandev.trackusers.Api.UtilsApi;
import com.byandev.trackusers.Main.DetailSosActivity;
import com.byandev.trackusers.Models.DetailSosModel;
import com.byandev.trackusers.R;
import com.google.android.gms.common.api.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDetailSos extends Fragment {

  private TextView tanggal, nama, pesan, noktp, nohp, novisa, nopasspor;
  private String sTanggal, sNama, sPesan, sNoVisa, sNoPasspor;
  private String iNoKtp, iNoHp;

  private Context context;
  private ApiEndPoint mApiServices;
  private SharedPrefManager sharedPrefManager;

  private Integer id;

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

    id = ((DetailSosActivity)getActivity()).getId();
    return view;
  }

  @Override
  public void onResume(){
    super.onResume();
    data();
  }

  @Override
  public void onStart(){
    super.onStart();
    initStringText();
  }

  private void initStringText() {
    tanggal.setText(sTanggal);
    nama.setText(sNama);
    pesan.setText(sPesan);
    noktp.setText(iNoKtp);
    nohp.setText(iNoHp);
    novisa.setText(sNoVisa);
    nopasspor.setText(sNoPasspor);
  }

  private void data() {
    mApiServices.detailSos(id).enqueue(new Callback<DetailSosModel>() {
      @Override
      public void onResponse(Call<DetailSosModel> call, Response<DetailSosModel> response) {
        if (response.isSuccessful()) {
          if (response.body().getApiStatus() == 1) {
            try {
              sTanggal = response.body().getData().getCreatedAt();
              sNama = response.body().getData().getNama();
              sPesan = response.body().getData().getMessage();
              iNoKtp = String.valueOf(response.body().getData().getNoKtp());
              iNoHp = String.valueOf(response.body().getData().getNoHp());
              sNoVisa = response.body().getData().getNoVisa();
              sNoPasspor = response.body().getData().getNoPasspor();
            } catch (Exception e){
              e.printStackTrace();
            }
          } else {
            Toast.makeText(context, "Koneksi bermasalah", Toast.LENGTH_LONG).show();
          }
        } else {
          Toast.makeText(context, "Kesalahan server", Toast.LENGTH_LONG).show();
        }
      }

      @Override
      public void onFailure(Call<DetailSosModel> call, Throwable t) {

      }
    });
  }
}
