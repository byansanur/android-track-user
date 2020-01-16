package com.byandev.trackusers.MainFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.byandev.trackusers.Adapter.AdapterSosList;
import com.byandev.trackusers.Api.ApiEndPoint;
import com.byandev.trackusers.Api.SharedPrefManager;
import com.byandev.trackusers.Api.UtilsApi;
import com.byandev.trackusers.Models.SosListModel;
import com.byandev.trackusers.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentSosList extends Fragment {

  private Context context;
  private ApiEndPoint mApiServices;
  private SharedPrefManager sharedPrefManager;

  private RecyclerView recyclerView;
  private ProgressBar progress;

  ArrayList<SosListModel.Sos> lisSos;
  AdapterSosList adapter;

  private boolean itsShouldLoadMore = true;
  private Integer offset = 15, limit;

  LinearLayout iconKosong;

  public FragmentSosList() {

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle s) {
    final View view = inflater.inflate(R.layout.fragment_list_sos, container, false);

    context = getContext();
    mApiServices = UtilsApi.getAPIService();
    sharedPrefManager = new SharedPrefManager(context);

    limit = 15;

    iconKosong = view.findViewById(R.id.iconKosong);

    recyclerView = view.findViewById(R.id.rvListSos);
    progress = view.findViewById(R.id.progress);

    lisSos = new ArrayList<>();
    adapter = new AdapterSosList(context, lisSos);

    LinearLayoutManager llm = new LinearLayoutManager(context);
    llm.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerView.setLayoutManager(llm);
    recyclerView.setAdapter(adapter);
    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0) {
          if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
            if (itsShouldLoadMore) {
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
  public void onResume() {
    super.onResume();
    lisSos.clear();
    adapter.notifyDataSetChanged();
    progress.setVisibility(View.VISIBLE);
    firstLoad();
  }

  @Override
  public void onStart(){
    super.onStart();
  }

  private void firstLoad() {
    itsShouldLoadMore = false;
    mApiServices.listSos(sharedPrefManager.getSpId(), limit, 0).enqueue(new Callback<SosListModel>() {
      @Override
      public void onResponse(Call<SosListModel> call, Response<SosListModel> response) {
        if (response.isSuccessful()) {
          itsShouldLoadMore = true;
          if (response.body().getApiStatus() == 1) {
            List<SosListModel.Sos> sosModel = response.body().getData();
            lisSos.addAll(sosModel);
            adapter.notifyDataSetChanged();
            if (lisSos.size() >= 1) {
              iconKosong.setVisibility(LinearLayout.INVISIBLE);
            } else {
              iconKosong.setVisibility(LinearLayout.VISIBLE);
            }
            progress.setVisibility(View.GONE);
          }
        }
      }

      @Override
      public void onFailure(Call<SosListModel> call, Throwable t) {
        itsShouldLoadMore = true;
        progress.setVisibility(View.GONE);
        Toast.makeText(context, "message " + t.getMessage(), Toast.LENGTH_LONG).show();
      }
    });
  }

  private void loadMore() {
    itsShouldLoadMore = false;
    mApiServices.listSos(sharedPrefManager.getSpId(), limit, offset).enqueue(new Callback<SosListModel>() {
      @Override
      public void onResponse(Call<SosListModel> call, Response<SosListModel> response) {
        if (response.isSuccessful()) {
          itsShouldLoadMore = true;
          if (response.body().getApiStatus() == 1) {
            List<SosListModel.Sos> sosModel = response.body().getData();
            lisSos.addAll(sosModel);
            adapter.notifyDataSetChanged();
            int index = lisSos.size();
            offset = index;
            progress.setVisibility(View.GONE);
          }
        }
      }

      @Override
      public void onFailure(Call<SosListModel> call, Throwable t) {
        itsShouldLoadMore = true;
        progress.setVisibility(View.GONE);
        Toast.makeText(context, "message " + t.getMessage(), Toast.LENGTH_LONG).show();
      }
    });
  }
}
