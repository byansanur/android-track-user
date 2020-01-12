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
import com.byandev.trackusers.Main.DetailRekom;
import com.byandev.trackusers.Models.DetailRekomModel;
import com.byandev.trackusers.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDetailRekom extends Fragment {

  private Context context;
  ApiEndPoint mApiService;
  SharedPrefManager sharedPrefManager;

  private TextView judul, nama, alamat, rating, type;
  private Integer id;

  public FragmentDetailRekom() {

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle s) {
    final View view = inflater.inflate(R.layout.fragment_detail_rekom, container, false);

    context = getContext();
    mApiService = UtilsApi.getAPIService();
    sharedPrefManager = new SharedPrefManager(context);

    judul = view.findViewById(R.id.judulRekom);
    nama = view.findViewById(R.id.tvNama);
    alamat = view.findViewById(R.id.tvAlamat);
    rating = view.findViewById(R.id.tvRating);
    type = view.findViewById(R.id.tvType);

    id = ((DetailRekom)getActivity()).getId();

    return view;
  }

  @Override
  public void onResume(){
    super.onResume();
    getDetail();
  }

  @Override
  public void onStart(){
    super.onStart();
  }

  private void getDetail() {
    mApiService.detail(id).enqueue(new Callback<DetailRekomModel>() {
      @Override
      public void onResponse(Call<DetailRekomModel> call, Response<DetailRekomModel> response) {
        if (response.isSuccessful()) {
          if (response.body() != null && response.body().getApiStatus() == 1) {
            judul.setText(String.valueOf(response.body().getData().getNama() + "\n" + response.body().getData().getTypeRekom()));
            nama.setText(String.valueOf(response.body().getData().getNama()));
            alamat.setText(String.valueOf(response.body().getData().getAlamat()));
            rating.setText(String.valueOf(response.body().getData().getRating()));
            type.setText(String.valueOf(response.body().getData().getTypeRekom()));
          }
        }
      }

      @Override
      public void onFailure(Call<DetailRekomModel> call, Throwable t) {
        Toast.makeText(context, "Pesan Gagal bang "+ t.getMessage(),Toast.LENGTH_LONG).show();
      }
    });
  }
}
