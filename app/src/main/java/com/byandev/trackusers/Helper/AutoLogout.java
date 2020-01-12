package com.byandev.trackusers.Helper;

import com.byandev.trackusers.Api.ApiEndPoint;
import com.byandev.trackusers.Api.AppController;
import com.byandev.trackusers.Api.SharedPrefManager;
import com.byandev.trackusers.Api.UtilsApi;

public class AutoLogout {

    private int hour;
    private SharedPrefManager sharedPrefManager = new SharedPrefManager(AppController.getInstance());
    private ApiEndPoint mApiService = UtilsApi.getAPIService();
//
//    public void SaveAutoLogout () {
//        Date currentTime = Calendar.getInstance().getTime();
//        Calendar x = Calendar.getInstance();
//        x.setTime(currentTime);
//        hour = x.get(Calendar.HOUR_OF_DAY);
//
//        Log.d("hour", String.valueOf(hour));
//
//        if (hour >= 23 && hour <= 24) {
//            x.add(Calendar.DATE, 1);
//        } else {
//            x.set(Calendar.HOUR_OF_DAY, 23);
//        }
//
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            String output = sdf1.format(x.getTime());
//            sharedPrefManager.saveSPString(SharedPrefManager.SP_LOGOUT_DATE,output);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public boolean CheckLogout(){
//        String dtSrart = sharedPrefManager.getSpLogoutDate();
//        if (!dtSrart.equals("")) {
//            Date currentTime = Calendar.getInstance().getTime();
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            try {
//                Date date = format.parse(dtSrart);
//                Log.d("Time", "not_conv = " +dtSrart+" sp = "+date.getTime()+" now = "+currentTime.getTime());
//                if (currentTime.getTime() >= date.getTime()) {
//                    String userAgent = System.getProperty("http.agent");
//                    Integer userId = sharedPrefManager.getSpId();
////                    mApiService.logout(userAgent, "Logout", userId, "" + BuildConfig.VERSION_NAME)
////                            .enqueue(new Callback<ResponseBody>() {
//////                       @Override
////                       public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
////
////                       }
////                       public void onFailure(Call<ResponseBody> call, Throwable t){
////
////                       }
////                    });
//                    sharedPrefManager.clearSharedPreferences();
//                    return true;
//                } else  {
//                    return false;
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                sharedPrefManager.clearSharedPreferences();
//                return true;
//            }
//        } else {
//            sharedPrefManager.clearSharedPreferences();
//            return true;
//        }
//    }
//    public void logOutNow(Context context) {
//        sharedPrefManager.clearSharedPreferences();
//        Toast.makeText(context, "Silahkan Login kembali "+"\ud83d\ude18", Toast.LENGTH_LONG).show();
//        Intent in = new Intent(context, LoginActivity.class);
//        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        context.startActivity(in);
//    }
}
