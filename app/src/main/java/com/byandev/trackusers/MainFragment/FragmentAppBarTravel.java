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
import androidx.viewpager.widget.ViewPager;

import com.byandev.trackusers.Api.ApiEndPoint;
import com.byandev.trackusers.Api.SharedPrefManager;
import com.byandev.trackusers.Api.UtilsApi;
import com.byandev.trackusers.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentAppBarTravel extends Fragment {

  private Context context;
  ApiEndPoint mApiService;
  SharedPrefManager sharedPrefManager;
  private Toolbar toolbar;
  private TextView tvPencarian;


  public FragmentAppBarTravel() {

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle s){
    final View view = inflater.inflate(R.layout.fragment_app_bar_kuliner, container, false);

    context = getContext();
    mApiService = UtilsApi.getAPIService();
    sharedPrefManager = new SharedPrefManager(getContext());

    toolbar = view.findViewById(R.id.toolbar);
    toolbar.setTitle("List Travel");
    tvPencarian = view.findViewById(R.id.tvPencarian);
    tvPencarian.setVisibility(View.GONE);

    ViewPager viewPager = view.findViewById(R.id.frame_container);
    ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
    adapter.addFragment(new FragmentListTravel(), "list");
    viewPager.setAdapter(adapter);

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
