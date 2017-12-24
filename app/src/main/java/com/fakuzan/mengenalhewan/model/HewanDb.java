package com.fakuzan.mengenalhewan.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by intel on 19/12/2017.
 */

public class HewanDb implements Parcelable {

    private String nama, suara, deskripsi, gambar, jenis;

    public HewanDb(String nama, String suara, String deskripsi, String gambar, String jenis) {
        this.nama = nama;
        this.suara = suara;
        this.deskripsi = deskripsi;
        this.gambar = gambar;
        this.jenis = jenis;
    }

    protected HewanDb(Parcel in) {
        nama = in.readString();
        suara = in.readString();
        deskripsi = in.readString();
        gambar = in.readString();
        jenis = in.readString();
    }

    public static final Creator<HewanDb> CREATOR = new Creator<HewanDb>() {
        @Override
        public HewanDb createFromParcel(Parcel in) {
            return new HewanDb(in);
        }

        @Override
        public HewanDb[] newArray(int size) {
            return new HewanDb[size];
        }
    };

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSuara() {
        return suara;
    }

    public void setSuara(String suara) {
        this.suara = suara;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.nama);
        parcel.writeString(this.suara);
        parcel.writeString(this.deskripsi);
        parcel.writeString(this.gambar);
        parcel.writeString(this.jenis);
    }

}
