package com.byandev.trackusers.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SosListModel {
  @SerializedName("api_status")
  @Expose
  private Integer apiStatus;
  @SerializedName("api_message")
  @Expose
  private String apiMessage;
  @SerializedName("data")
  @Expose
  private List<Sos> data = null;

  public Integer getApiStatus() {
    return apiStatus;
  }

  public void setApiStatus(Integer apiStatus) {
    this.apiStatus = apiStatus;
  }

  public String getApiMessage() {
    return apiMessage;
  }

  public void setApiMessage(String apiMessage) {
    this.apiMessage = apiMessage;
  }

  public List<Sos> getData() {
    return data;
  }

  public void setData(List<Sos> data) {
    this.data = data;
  }

  public class Sos {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_sos_jamaah")
    @Expose
    private Integer idSosJamaah;
    @SerializedName("id_users_sender")
    @Expose
    private Integer idUsersSender;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public Integer getIdSosJamaah() {
      return idSosJamaah;
    }

    public void setIdSosJamaah(Integer idSosJamaah) {
      this.idSosJamaah = idSosJamaah;
    }

    public Integer getIdUsersSender() {
      return idUsersSender;
    }

    public void setIdUsersSender(Integer idUsersSender) {
      this.idUsersSender = idUsersSender;
    }

    public String getNama() {
      return nama;
    }

    public void setNama(String nama) {
      this.nama = nama;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public String getLat() {
      return lat;
    }

    public void setLat(String lat) {
      this.lat = lat;
    }

    public String getLng() {
      return lng;
    }

    public void setLng(String lng) {
      this.lng = lng;
    }

    public String getCreatedAt() {
      return createdAt;
    }

    public void setCreatedAt(String createdAt) {
      this.createdAt = createdAt;
    }

  }

}
