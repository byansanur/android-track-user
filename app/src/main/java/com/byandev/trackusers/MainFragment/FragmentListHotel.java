package com.byandev.trackusers.MainFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.byandev.trackusers.Adapter.AdapterRekomListHotel;
import com.byandev.trackusers.Api.ApiEndPoint;
import com.byandev.trackusers.Api.SharedPrefManager;
import com.byandev.trackusers.Api.UtilsApi;
import com.byandev.trackusers.Main.MapActivity;
import com.byandev.trackusers.Main.MapActivityKategoriHotel;
import com.byandev.trackusers.Models.RekomModel;
import com.byandev.trackusers.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentListHotel extends Fragment {

  private RecyclerView recyclerView;
  private ApiEndPoint mApiService;
  SharedPrefManager sharedPrefManager;
  Context context;
  private Integer offset = 15, limit;
  private boolean itShouldLoadMore = true;
  ProgressBar progress;
  FloatingActionButton floatingActionButton;
  LinearLayout iconKosong;

  private ArrayList<RekomModel.Rekom> rekomArrayList;
  private AdapterRekomListHotel adapter;

  public FragmentListHotel(){

  }

  @Override
  public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle s) {
    View view = inflater.inflate(R.layout.fragment_list, container, false);
    context = getContext();
    limit = 15;
    recyclerView = view.findViewById(R.id.rvListHotel);
    recyclerView.setHasFixedSize(true);
    mApiService = UtilsApi.getAPIService();
    sharedPrefManager = new SharedPrefManager(getContext());

    iconKosong = view.findViewById(R.id.iconKosong);
    floatingActionButton = view.findViewById(R.id.fabMap);
    progress = view.findViewById(R.id.progress);

    rekomArrayList = new ArrayList<>();
    adapter = new AdapterRekomListHotel(getContext(), rekomArrayList);

    LinearLayoutManager llm = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(llm);
    recyclerView.setAdapter(adapter);
    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0) {
          if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
            if (itShouldLoadMore) {
              progress.setVisibility(View.VISIBLE);
              loadMore();
            }
          }
        }
      }
    });
    return view;
  }

  @Override
  public void onResume(){
    super.onResume();
    rekomArrayList.clear();
    adapter.notifyDataSetChanged();
    progress.setVisibility(View.VISIBLE);
    firstLoad();
  }

  @Override
  public void onStart(){
    super.onStart();

    floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
    floatingActionButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(context, MapActivityKategoriHotel.class)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK));
        getActivity().finish();
//        Intent a = new Intent(FragmentListHotel.this, )
      }
    });
  }

  private void firstLoad() {
    itShouldLoadMore = false;
    mApiService.rekomList(1, limit, 0).enqueue(new Callback<RekomModel>() {
      @Override
      public void onResponse(Call<RekomModel> call, Response<RekomModel> response) {
        if (response.isSuccessful()) {
          itShouldLoadMore = true;
          if (response.body().getData() != null) {
            List<RekomModel.Rekom> rekomList = response.body().getData();
            rekomArrayList.addAll(rekomList);
            adapter.notifyDataSetChanged();
            if (rekomArrayList.size() >= 1) {
              iconKosong.setVisibility(LinearLayout.INVISIBLE);
            } else {
              iconKosong.setVisibility(LinearLayout.VISIBLE);
            }
            progress.setVisibility(View.GONE);
          }
        }
      }

      @Override
      public void onFailure(Call<RekomModel> call, Throwable t) {
        itShouldLoadMore = true;
        progress.setVisibility(View.GONE);
        Toast.makeText(context, "message " + t.getMessage(), Toast.LENGTH_LONG).show();
      }
    });
  }

  private void loadMore() {
    itShouldLoadMore = false;
    mApiService.rekomList(1, limit, offset).enqueue(new Callback<RekomModel>() {
      @Override
      public void onResponse(Call<RekomModel> call, Response<RekomModel> response) {
        if (response.isSuccessful()) {
          itShouldLoadMore = true;
          if (response.body().getData() != null) {
            List<RekomModel.Rekom> rekomList = response.body().getData();
            rekomArrayList.addAll(rekomList);
            adapter.notifyDataSetChanged();
            int index = rekomArrayList.size();
            offset = index;
            progress.setVisibility(View.GONE);
          }
        }
      }

      @Override
      public void onFailure(Call<RekomModel> call, Throwable t) {
        itShouldLoadMore = true;
        progress.setVisibility(View.GONE);
        Toast.makeText(context, "message " + t.getMessage(), Toast.LENGTH_LONG).show();
      }
    });
  }
}
