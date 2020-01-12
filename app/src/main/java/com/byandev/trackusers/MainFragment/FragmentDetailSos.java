package com.byandev.trackusers.MainFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.byandev.trackusers.Api.ApiEndPoint;
import com.byandev.trackusers.Api.SharedPrefManager;
import com.byandev.trackusers.Api.UtilsApi;
import com.byandev.trackusers.Main.DetailSosActivity;
import com.byandev.trackusers.R;
import com.google.android.gms.common.api.Api;

public class FragmentDetailSos extends Fragment {

  private TextView tanggal, nama, pesan, noktp, nohp, novisa, nopasspor;

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

//    idSos = ((DetailSosActivity)getActivity().getIdSos());
//    idSos =
    return view;
  }

  @Override
  public void onResume(){
    super.onResume();
    data();
  }

  private void data() {
  }
}
