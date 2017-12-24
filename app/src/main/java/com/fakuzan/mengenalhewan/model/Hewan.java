package com.fakuzan.mengenalhewan.model;

/**
 * Created by intel on 19/12/2017.
 */

public class Hewan {

    private String nama, deskripsi, suara, gambar;

    public Hewan(String nama, String deskripsi, String suara, String gambar) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.suara = suara;
        this.gambar = gambar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getSuara() {
        return suara;
    }

    public void setSuara(String suara) {
        this.suara = suara;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

}
