package com.byandev.trackusers.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailRekomModel {
  @SerializedName("api_status")
  @Expose
  private Integer apiStatus;
  @SerializedName("api_message")
  @Expose
  private String apiMessage;
  @SerializedName("data")
  @Expose
  private DataDetail data;

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

  public DataDetail getData() {
    return data;
  }

  public void setData(DataDetail data) {
    this.data = data;
  }

  public class DataDetail {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("id_type")
    @Expose
    private Integer idType;
    @SerializedName("type_rekom")
    @Expose
    private String typeRekom;

    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public String getNama() {
      return nama;
    }

    public void setNama(String nama) {
      this.nama = nama;
    }

    public String getAlamat() {
      return alamat;
    }

    public void setAlamat(String alamat) {
      this.alamat = alamat;
    }

    public String getFoto() {
      return foto;
    }

    public void setFoto(String foto) {
      this.foto = foto;
    }

    public String getRating() {
      return rating;
    }

    public void setRating(String rating) {
      this.rating = rating;
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

    public Integer getIdType() {
      return idType;
    }

    public void setIdType(Integer idType) {
      this.idType = idType;
    }

    public String getTypeRekom() {
      return typeRekom;
    }

    public void setTypeRekom(String typeRekom) {
      this.typeRekom = typeRekom;
    }

  }

}
