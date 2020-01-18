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

public class FragmentAppbarSos extends Fragment {

  private Context context;
  ApiEndPoint mApiService;
  SharedPrefManager sharedPrefManager;

  private Toolbar toolbar;
  private TextView tvPencarian;

  Call<RekomModel> call = null;
  private RecyclerView recyclerView;
  ArrayList<RekomModel.Rekom> rekoms;
  AdapterRekomListHotel adapter;

  public FragmentAppbarSos() {

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle s) {
    final View view = inflater.inflate(R.layout.fragment_app_bar_sos, container, false);

    context = getContext();
    mApiService = UtilsApi.getAPIService();
    sharedPrefManager = new SharedPrefManager(getContext());

    toolbar = view.findViewById(R.id.toolbar);
    toolbar.setTitle("Sos Activity");

    tvPencarian = view.findViewById(R.id.tvPencarian);
    tvPencarian.setVisibility(View.GONE);

    ViewPager viewPager = view.findViewById(R.id.frame_container);
    ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
    pagerAdapter.addFragment(new FragmentSosList(), "list");
    viewPager.setAdapter(pagerAdapter);



    return view;
  }

  @Override
  public void onStart() {
    super.onStart();
  }


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
