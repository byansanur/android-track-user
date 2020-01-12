package com.byandev.trackusers.Api;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    public static final String SP_ADMIN_APP = "spAdminApp";

    public static final String SP_ID = "spId";
    public static final String SP_NAMA = "spNama";
    public static final String SP_USERNAME = "spUsername";
    public static final String SP_TGL_LAHIR = "spTglLahir";
    public static final String SP_NO_KTP = "spNoKtp";
    public static final String SP_NO_HP = "spNoHp";
    public static final String SP_NO_VISA = "spNoVisa";
    public static final String SP_NO_PASSPOR = "spNoPasspor";
    public static final String SP_FOTO = "spFoto";
    public static final String SP_ID_PRIVILEGES = "spIdPrivileges";
    public static final String SP_EMAIL = "spEmail";
    public static final String SP_TOKEN = "spToken";
    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;

    public void clearSharedPreferences(){
        spEditor.clear();
        spEditor.apply();
    }

    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SP_ADMIN_APP, 0);
        sp = context.getSharedPreferences("fileName", Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public Integer getSpId() {
        return sp.getInt(SP_ID, 0);
    }

    public String getSPNama(){
        return sp.getString(SP_NAMA, "");
    }

    public String getSpUsername(){
        return sp.getString(SP_USERNAME, "");
    }

    public String getSpTglLahir(){
        return sp.getString(SP_TGL_LAHIR, "");
    }

    public String getSpNoKtp() {
        return sp.getString(SP_NO_KTP, "");
    }

    public String getSpNoHp() {
        return sp.getString(SP_NO_HP, "");
    }

    public String getSpNoVisa() {
        return sp.getString(SP_NO_VISA, "");
    }

    public String getSpNoPasspor() {
        return sp.getString(SP_NO_PASSPOR, "");
    }

    public String getSpFoto() {
        return sp.getString(SP_FOTO, "");
    }

    public Integer getSpIdPrivileges() {
        return sp.getInt(SP_ID_PRIVILEGES, 0);
    }

    public String getSPEmail(){
        return sp.getString(SP_EMAIL, "");
    }

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
    public String getSpToken() {
        return sp.getString(SP_TOKEN,"");
    }
}
