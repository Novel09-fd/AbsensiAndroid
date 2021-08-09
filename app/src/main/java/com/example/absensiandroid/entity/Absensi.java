
package com.example.absensiandroid.entity;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Absensi {

    @SerializedName("id")
    private long id;
    @SerializedName("user")
    private String user;
    @SerializedName("dataFotoMasuk")
    private String fotoMasuk;
    @SerializedName("dataFotoKeluar")
    private String fotoKeluar;
    @SerializedName("tanggalMasuk")
    private String tanggalMasuk;
    @SerializedName("tanggalKeluar")
    private String tanggalKeluar;
    @SerializedName("jamMasuk")
    private int jamMasuk;
    @SerializedName("jamKeluar")
    private int jamKeluar;
    @SerializedName("gps")
    private String gps;

    public Absensi(long id, String user, String fotoMasuk, String fotoKeluar, String tanggalMasuk, String tanggalKeluar, int jamMasuk, int jamKeluar, String gps) {
        this.id = id;
        this.user = user;
        this.fotoMasuk = fotoMasuk;
        this.fotoKeluar = fotoKeluar;
        this.tanggalMasuk = tanggalMasuk;
        this.tanggalKeluar = tanggalKeluar;
        this.jamMasuk = jamMasuk;
        this.jamKeluar = jamKeluar;
        this.gps = gps;
    }
    public Absensi() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFotoMasuk() {
        return fotoMasuk;
    }

    public void setFotoMasuk(String fotoMasuk) {
        this.fotoMasuk = fotoMasuk;
    }

    public String getFotoKeluar() {
        return fotoKeluar;
    }

    public void setFotoKeluar(String fotoKeluar) {
        this.fotoKeluar = fotoKeluar;
    }

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(String tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public String getTanggalKeluar() {
        return tanggalKeluar;
    }

    public void setTanggalKeluar(String tanggalKeluar) {
        this.tanggalKeluar = tanggalKeluar;
    }

    public int getJamMasuk() {
        return jamMasuk;
    }

    public void setJamMasuk(int jamMasuk) {
        this.jamMasuk = jamMasuk;
    }

    public int getJamKeluar() {
        return jamKeluar;
    }

    public void setJamKeluar(int jamKeluar) {
        this.jamKeluar = jamKeluar;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }
}