package com.byandev.trackusers.Main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.byandev.trackusers.Api.ApiEndPoint;
import com.byandev.trackusers.Api.SharedPrefManager;
import com.byandev.trackusers.Api.UtilsApi;
import com.byandev.trackusers.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements androidx.appcompat.widget.Toolbar.OnMenuItemClickListener {


  public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
  Context context;
  SharedPrefManager sharedPrefManager;
  ApiEndPoint mApiService;
  private Handler handler = new Handler();
  private Integer intVersion = 0, xmlVersion;
  private Boolean logoutcheck = false;
  private Runnable r;
  private TextView greeting, tanggal;
  private FloatingActionButton fab;
  androidx.appcompat.widget.Toolbar toolbar;
  private String dateSkrg, dateKmrn;

  private CardView cardViewSos, cardViewRek, cardListSos;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.home_activity);

      context = this;
      sharedPrefManager = new SharedPrefManager(context);
      mApiService = UtilsApi.getAPIService();

      cardViewSos = findViewById(R.id.cardSos);
      cardViewRek = findViewById(R.id.cardrek);
      cardListSos = findViewById(R.id.cardActivitySos);

      checkAndRequestPermissions();
      xmlVersion = Integer.parseInt(getResources().getString(R.string.version));

      toolbar = findViewById(R.id.toolbar);
      toolbar.setTitle("TJU App");
      toolbar.inflateMenu(R.menu.menu_main);
      toolbar.setOnMenuItemClickListener(this);

      fab = findViewById(R.id.fab);
      greeting = findViewById(R.id.tvGreating);
      tanggal = findViewById(R.id.tvTanggal);

      Calendar startDate = Calendar.getInstance();
      startDate.add(Calendar.MONTH, -1);
      Calendar endDate = Calendar.getInstance();
      endDate.add(Calendar.MONTH, 1);

      inisialisasiSalam();

    }




  @Override
  public void onResume() {
    super.onResume();
    listener();

  }

  private void listener() {
      cardViewSos.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          //dialog yes or no
          AlertDialog.Builder adb = new AlertDialog.Builder(context);
          adb.setMessage("Apakah anda memerlukan bantuan ?").setCancelable(false)
              .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                  Intent a = new Intent(context, CreateSosActivity.class);
                  startActivity(a);
                  finish();
                }
              })
              .setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
              }).show();
        }
      });
      cardViewRek.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          // intent kategori
          startActivity(new Intent(context, KategoriActivity.class).
              addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
          finish();
        }
      });
      cardListSos.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          startActivity(new Intent(context, ListSosActivity.class).
              addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
          finish();
        }
      });
      fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          // intent profile
          startActivity(new Intent(context, ProfileActivity.class).
              addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
          finish();
        }
      });
  }

  @SuppressLint("ResourceType")
  @RequiresApi(api = Build.VERSION_CODES.M)
  private void inisialisasiSalam() {
    Date date =new Date();
    @SuppressLint("SimpleDateFormat")
    DateFormat df = new SimpleDateFormat("dd MMMM, yyyy");
    String hariini = df.format(Calendar.getInstance().getTime());
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int hour = cal.get(Calendar.HOUR_OF_DAY);
    String salam = null;
    if (hour >= 12 && hour < 17) {
      salam = "Selamat siang";
    } else if (hour >= 17 && hour < 21) {
      salam = "Selamat sore";
    } else if (hour >= 21 && hour < 24) {
      salam = "Selamat malam";
    } else {
      salam = "Selamat pagi";
    }
    greeting.setText(salam +", "+ sharedPrefManager.getSPNama());
//    fab.setImageIcon(textAsBitmap(""+sharedPrefManager.getSPNama().charAt(0), 40, Color.WHITE));
    tanggal.setText(hariini);
  }

  @Override
  public boolean onMenuItemClick(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.sos:
        return true;
      case R.id.tentang :
        Intent i = new Intent(this, TentangActivity.class);
        startActivity(i);
        return true;
      case R.id.logout:
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setMessage("Logout ?").setCancelable(true)
            .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
              }
            })
            .setPositiveButton("Ya, Logout", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                sharedPrefManager.clearSharedPreferences();
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                finish();
              }
            })
            .show();
        return true;
    }
    return false;
  }

  @Override
  protected void onPause() {
    super.onPause();
    logoutcheck = false;
    handler.removeCallbacks(r);
  }



  @Override
  public void onBackPressed() {
    super.onBackPressed();

  }


  @Override
  protected void onStop() {
    super.onStop();
    logoutcheck = false;
    handler.removeCallbacks(r);

  }
//
//  @SuppressLint("NewApi")
//  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//  public static Icon textAsBitmap(String text, float textSize, int textColor) {
//    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//    paint.setTextSize(textSize);
//    paint.setColor(textColor);
//    paint.setTextAlign(Paint.Align.LEFT);
//    float baseline = -paint.ascent(); // ascent() is negative
//    int width = (int) (paint.measureText(text) + 0.0f); // round
//    int height = (int) (baseline + paint.descent() + 0.0f);
//    Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//
//    Canvas canvas = new Canvas(image);
//    canvas.drawText(text, 0, baseline, paint);
//    return Icon.createWithBitmap(image);
//  }

  private boolean checkAndRequestPermissions() {
    int telpon = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
    int location = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
    int storage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    int storageRead = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
    List<String> listPermissionsNeeded = new ArrayList<>();

    if (telpon != PackageManager.PERMISSION_GRANTED) {
      listPermissionsNeeded.add(Manifest.permission.CALL_PHONE);
    }
    if (storage != PackageManager.PERMISSION_GRANTED) {
      listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }
    if (location != PackageManager.PERMISSION_GRANTED) {
      listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
    }
    if (storageRead != PackageManager.PERMISSION_GRANTED) {
      listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
    }
    if (!listPermissionsNeeded.isEmpty()) {
      ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
      return false;
    }
    return true;
  }

}
