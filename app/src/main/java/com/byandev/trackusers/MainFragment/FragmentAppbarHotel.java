package com.byandev.trackusers.MainFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.byandev.trackusers.Adapter.AdapterRekomListHotel;
import com.byandev.trackusers.Api.ApiEndPoint;
import com.byandev.trackusers.Api.SharedPrefManager;
import com.byandev.trackusers.Api.UtilsApi;
import com.byandev.trackusers.Models.RekomModel;
import com.byandev.trackusers.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class FragmentAppbarHotel extends Fragment {

  private Context context;
  ApiEndPoint mApiService;
  SharedPrefManager sharedPrefManager;

  private Toolbar toolbar;
  private TextView tvPencarian;

  Call<RekomModel> call = null;
  private RecyclerView recyclerView;
  ArrayList<RekomModel.Rekom> rekoms;
  AdapterRekomListHotel adapter;

  public FragmentAppbarHotel() {

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle s) {
    final View view = inflater.inflate(R.layout.fragment_app_bar_hotel, container, false);

    context = getContext();
    mApiService = UtilsApi.getAPIService();
    sharedPrefManager = new SharedPrefManager(getContext());

    toolbar = view.findViewById(R.id.toolbar);
    toolbar.setTitle("List hotel");
//    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
//    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        Intent a = new Intent(getContext(), KategoriActivity.class);
//        startActivity(a);
//        getActivity().finish();
//      }
//    });
//    setHasOptionsMenu(true);
//    ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

    tvPencarian = view.findViewById(R.id.tvPencarian);
    tvPencarian.setVisibility(View.GONE);

    ViewPager viewPager = view.findViewById(R.id.frame_container);
    ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
    pagerAdapter.addFragment(new FragmentListHotel(), "list");
    viewPager.setAdapter(pagerAdapter);

    recyclerView = view.findViewById(R.id.recyclerView);
    rekoms = new ArrayList<>();
    adapter = new AdapterRekomListHotel(context, rekoms);
    LinearLayoutManager ll = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(ll);
    recyclerView.setAdapter(adapter);


    return view;
  }

  @Override
  public void onStart() {
    super.onStart();
  }

//  @Override
//  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//    inflater.inflate(R.menu.search, menu);
//    MenuItem menuItem = menu.findItem(R.id.action_search);
//
//    final SearchView searchView = (SearchView) menuItem.getActionView();
//    searchView.setQueryHint("Cari hotel");
//
//    try {
//      EditText etSearch = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
//      etSearch.setTextColor(getResources().getColor(R.color.quantum_white_text));
//      etSearch.setHintTextColor(getResources().getColor(R.color.quantum_white_text));
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//
//    MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
//      @Override
//      public boolean onMenuItemActionExpand(MenuItem item) {
//        return false;
//      }
//
//      @Override
//      public boolean onMenuItemActionCollapse(MenuItem item) {
//        searchView.setQuery("", false);
//        tvPencarian.setVisibility(View.GONE);
//        recyclerView.setVisibility(View.INVISIBLE);
//        rekoms.clear();
//        adapter.notifyDataSetChanged();
//        return true;
//      }
//    });
//    searchView.setOnSearchClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        tvPencarian.setText("Ketikan untuk mencari");
//        tvPencarian.setVisibility(View.GONE);
//      }
//    });
//    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//      @Override
//      public boolean onQueryTextSubmit(String query) {
//        return false;
//      }
//
//      @Override
//      public boolean onQueryTextChange(String newText) {
//        if (call != null) {
//          call.cancel();
//        }
//        if (newText.length() >= 1) {
//          tvPencarian.setVisibility(View.INVISIBLE);
//          callFilter(newText);
//        }else {
//          tvPencarian.setText("Ketik untuk mencari");
//          tvPencarian.setText(TextView.VISIBLE);
//          rekoms.clear();
//          adapter.notifyDataSetChanged();
//        }
//        return true;
//      }
//    });
//    super.onCreateOptionsMenu(menu, inflater);
//  }
//
//  private void callFilter(String newText) {
//    tvPencarian.setVisibility(View.VISIBLE);
//    tvPencarian.setText("Sedang mencari");
//    call = mApiService.search(1, newText);
//    call.enqueue(new Callback<RekomModel>() {
//      @Override
//      public void onResponse(Call<RekomModel> call, Response<RekomModel> response) {
//        if (response.isSuccessful()) {
//          if (response.body().getData() != null) {
//
//            tvPencarian.setText("Hasil Pencarian");
//            rekoms.clear();
//            adapter.notifyDataSetChanged();
//            recyclerView.setVisibility(RecyclerView.VISIBLE);
//
//            List<RekomModel.Rekom> list = response.body().getData();
//
//            if (list.size() <= 0) {
//                tvPencarian.setText("Pencarian Tidak Ditemukan");
//            }
//            rekoms.addAll(list);
//
//            adapter.notifyDataSetChanged();
//
//          }
//        }
//      }
//
//      @Override
//      public void onFailure(Call<RekomModel> call, Throwable t) {
//        if (call.isCanceled()) {
//
//        } else {
//          tvPencarian.setText("Gagal Mencari");
//        }
//      }
//    });
//  }

  @Override
  public void  onResume() {
    super.onResume();
  }

  class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
      super(fm);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
      return mFragmentList.get(position);
    }
    @Override
    public int getCount() {
      return mFragmentList.size();
    }
    public void addFragment(Fragment fragment, String title) {
      mFragmentList.add(fragment);
      mFragmentTitleList.add(title);
    }
    @Override
    public CharSequence getPageTitle(int position){
      return mFragmentTitleList.get(position);
    }
  }
}
