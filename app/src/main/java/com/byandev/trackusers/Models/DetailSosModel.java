package com.byandev.trackusers.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailSosModel {
  @SerializedName("api_status")
  @Expose
  private Integer apiStatus;
  @SerializedName("api_message")
  @Expose
  private String apiMessage;
  @SerializedName("data")
  @Expose
  private DataDetailSos data;

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

  public DataDetailSos getData() {
    return data;
  }

  public void setData(DataDetailSos data) {
    this.data = data;
  }

  public static class DataDetailSos {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_sos_jamaah")
    @Expose
    private Integer idSosJamaah;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("id_users_sender")
    @Expose
    private Integer idUsersSender;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("no_ktp")
    @Expose
    private String noKtp;
    @SerializedName("no_hp")
    @Expose
    private String noHp;
    @SerializedName("no_visa")
    @Expose
    private String noVisa;
    @SerializedName("no_passpor")
    @Expose
    private String noPasspor;
    @SerializedName("role")
    @Expose
    private String role;

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

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public Integer getIdUsersSender() {
      return idUsersSender;
    }

    public void setIdUsersSender(Integer idUsersSender) {
      this.idUsersSender = idUsersSender;
    }

    public String getCreatedAt() {
      return createdAt;
    }

    public void setCreatedAt(String createdAt) {
      this.createdAt = createdAt;
    }

    public String getNama() {
      return nama;
    }

    public void setNama(String nama) {
      this.nama = nama;
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

    public String getNoKtp() {
      return noKtp;
    }

    public void setNoKtp(String noKtp) {
      this.noKtp = noKtp;
    }

    public String getNoHp() {
      return noHp;
    }

    public void setNoHp(String noHp) {
      this.noHp = noHp;
    }

    public String getNoVisa() {
      return noVisa;
    }

    public void setNoVisa(String noVisa) {
      this.noVisa = noVisa;
    }

    public String getNoPasspor() {
      return noPasspor;
    }

    public void setNoPasspor(String noPasspor) {
      this.noPasspor = noPasspor;
    }

    public String getRole() {
      return role;
    }

    public void setRole(String role) {
      this.role = role;
    }

  }
}
