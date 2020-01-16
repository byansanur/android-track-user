package com.byandev.trackusers.Api;

import android.content.Intent;

import com.byandev.trackusers.Models.DetailRekomModel;
import com.byandev.trackusers.Models.DetailSosModel;
import com.byandev.trackusers.Models.Login;
import com.byandev.trackusers.Models.RekomModel;
import com.byandev.trackusers.Models.SosCreateModel;
import com.byandev.trackusers.Models.SosListModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiEndPoint {
  @FormUrlEncoded
  @POST("login_users")
  Call<Login> requestLogin(@Field("username") String username,
                           @Field("password") String password);

  @FormUrlEncoded
  @POST("sos")
  Call<SosCreateModel> sosCreate(@Field("message") String message,
                                 @Field("id_users_sender")Integer idUsers,
                                 @Field("lat") String lat,
                                 @Field("lng") String lng);

  @GET("rekom_list")
  Call<RekomModel> rekomList(@Query("id_type") Integer idType,
                             @Query("limit") Integer limit,
                             @Query("offset") Integer offset);

  @GET("sos/detail")
  Call<DetailSosModel> detailSos(@Query("id") Integer id);

  @GET("sos/list")
  Call<SosListModel> listSos (@Query("id_users_sender") Integer idUsers,
                              @Query("limit") Integer limit,
                              @Query("offset") Integer offset);

  @GET("rekom_list")
  Call<RekomModel> rekomkategori(@Query("id_type") Integer idType);

  @GET("rekom_list")
  Call<RekomModel> rekomListMap();

  @GET("rekom_list")
  Call<RekomModel> search(@Query("id_type") Integer idType,
                          @Query("nama") String nama);

  @GET("rekom_detail")
  Call<DetailRekomModel> detail(@Query("id") Integer id);


}
