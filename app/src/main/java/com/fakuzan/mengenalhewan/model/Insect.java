package com.fakuzan.mengenalhewan.model;

/**
 * Created by intel on 19/12/2017.
 */

public class Insect extends Hewan {

    private static String namaKelas = "Insect";
    private String deskripsiKelas, gambarKelas;

    public Insect(String deskripsiKelas, String gambarKelas) {
        super("absract", "absract", "absract", "absract");
        this.deskripsiKelas = deskripsiKelas;
        this.gambarKelas = gambarKelas;
    }

    public Insect(String nama, String deskripsi, String suara, String gambar, String deskripsiKelas) {
        super(nama, deskripsi, suara, gambar);
        this.deskripsiKelas = deskripsiKelas;
    }

    public static String getNamaKelas() {
        return namaKelas;
    }

    public String getDeskripsiKelas() {
        return deskripsiKelas;
    }

    public void setDeskripsiKelas(String deskripsiKelas) {
        this.deskripsiKelas = deskripsiKelas;
    }

    public String getGambarKelas() {
        return gambarKelas;
    }

    public void setGambarKelas(String gambarKelas) {
        this.gambarKelas = gambarKelas;
    }

}
