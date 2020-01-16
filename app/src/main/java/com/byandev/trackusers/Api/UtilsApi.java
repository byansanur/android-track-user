package com.byandev.trackusers.Api;

public class UtilsApi {
  public static final String BASE_URL_API = "http://192.168.50.52:4001/api/v1/";


  // Mendeklarasikan Interface BaseApiService
  public static ApiEndPoint getAPIService() {
    return RetrofitClient.getClient(BASE_URL_API).create(ApiEndPoint.class);
  }
}
