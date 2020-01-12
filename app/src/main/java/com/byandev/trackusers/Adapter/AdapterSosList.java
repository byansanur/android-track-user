package com.byandev.trackusers.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.byandev.trackusers.Api.ApiEndPoint;
import com.byandev.trackusers.Api.SharedPrefManager;
import com.byandev.trackusers.Api.UtilsApi;
import com.byandev.trackusers.Main.DetailRekom;
import com.byandev.trackusers.Main.DetailSosActivity;
import com.byandev.trackusers.Models.RekomModel;
import com.byandev.trackusers.Models.SosListModel;
import com.byandev.trackusers.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AdapterSosList extends RecyclerView.Adapter<AdapterSosList.ViewHolder> {

  private Context context;

  ArrayList<SosListModel.Sos> list;

  ApiEndPoint mApiServices;
  SharedPrefManager sharedPrefManager;

  public AdapterSosList(Context context, List<SosListModel.Sos> list1) {
    this.context = context;
    this.list = (ArrayList<SosListModel.Sos>) list1;
  }


  @NonNull
  @Override
  public AdapterSosList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sos,parent, false);
    mApiServices = UtilsApi.getAPIService();
    sharedPrefManager = new SharedPrefManager(context);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull AdapterSosList.ViewHolder holder, int position) {
    final SosListModel.Sos ls = list.get(position);
    holder.nama.setText(ls.getNama());
    holder.tanggal.setText(convertTime(ls.getCreatedAt()));
    holder.cardView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent a = new Intent(context, DetailSosActivity.class)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        a.putExtra("id", ls.getId());
        context.startActivity(a);
      }
    });
  }

  @Override
  public int getItemCount() {
    return list.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView nama, tanggal;
    CardView cardView;
    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      nama = itemView.findViewById(R.id.tvNama);
      tanggal = itemView.findViewById(R.id.tvCreatedAt);
      cardView = itemView.findViewById(R.id.card);
    }
  }

  private String convertTime(String time) {
    @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("dd MMM, yyyy");
    java.util.Date date = null;
    try {
      date = format.parse(time);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    String convertedDate = time;
    try {
      convertedDate = format1.format(date);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return convertedDate;
  }
}
