package com.byandev.trackusers.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {

  @SerializedName("api_status")
  @Expose
  private Integer apiStatus;
  @SerializedName("api_message")
  @Expose
  private String apiMessage;
  @SerializedName("data")
  @Expose
  private DataLogin data;

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

  public DataLogin getData() {
    return data;
  }

  public void setData(DataLogin data) {
    this.data = data;
  }

  public class DataLogin {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("tgl_lahir")
    @Expose
    private String tglLahir;
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
    @SerializedName("id_privileges")
    @Expose
    private Integer idPrivileges;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("token")
    @Expose
    private String token;

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

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getTglLahir() {
      return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
      this.tglLahir = tglLahir;
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

    public Integer getIdPrivileges() {
      return idPrivileges;
    }

    public void setIdPrivileges(Integer idPrivileges) {
      this.idPrivileges = idPrivileges;
    }

    public String getRole() {
      return role;
    }

    public void setRole(String role) {
      this.role = role;
    }

    public String getCreatedAt() {
      return createdAt;
    }

    public void setCreatedAt(String createdAt) {
      this.createdAt = createdAt;
    }

    public String getToken() {
      return token;
    }

    public void setToken(String token) {
      this.token = token;
    }

  }


}
