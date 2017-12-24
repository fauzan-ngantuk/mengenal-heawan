package com.fakuzan.mengenalhewan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fakuzan.mengenalhewan.adapter.KelasAdapter;
import com.fakuzan.mengenalhewan.interfaces.CustomItemClickListener;
import com.fakuzan.mengenalhewan.model.Amfibi;
import com.fakuzan.mengenalhewan.model.Aves;
import com.fakuzan.mengenalhewan.model.Hewan;
import com.fakuzan.mengenalhewan.model.Insect;
import com.fakuzan.mengenalhewan.model.Mamalia;
import com.fakuzan.mengenalhewan.model.Reptil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Hewan> hewan = new ArrayList<>();

    public static final String NAMA_KELAS = "nama_kelas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv_kelas);

        hewan.add(new Mamalia(getString(R.string.deskripsi_mamalia), "mamalia"));
        hewan.add(new Aves(getString(R.string.deskripsi_aves), "aves"));
        hewan.add(new Amfibi(getString(R.string.deskripsi_amfibi), "amfibi"));
        hewan.add(new Insect(getString(R.string.deskripsi_mamalia), "insect"));
        hewan.add(new Reptil(getString(R.string.deskripsi_mamalia), "reptil"));

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        KelasAdapter kelasAdapter = new KelasAdapter(getApplicationContext(), hewan, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                String nama = "";
                try {
                    if (hewan.get(position).getClass().isAssignableFrom(Class.forName(Mamalia.class.getName()))){
                        nama = ((Mamalia) hewan.get(position)).getNamaKelas();
                    }
                    if (hewan.get(position).getClass().isAssignableFrom(Class.forName(Amfibi.class.getName()))){
                        nama = ((Amfibi) hewan.get(position)).getNamaKelas();
                    }
                    if (hewan.get(position).getClass().isAssignableFrom(Class.forName(Aves.class.getName()))){
                        nama = ((Aves) hewan.get(position)).getNamaKelas();
                    }
                    if (hewan.get(position).getClass().isAssignableFrom(Class.forName(Insect.class.getName()))){
                        nama = ((Insect) hewan.get(position)).getNamaKelas();
                    }
                    if (hewan.get(position).getClass().isAssignableFrom(Class.forName(Reptil.class.getName()))){
                        nama = ((Reptil) hewan.get(position)).getNamaKelas();
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(MainActivity.this, HewanActivity.class);
                intent.putExtra(NAMA_KELAS, nama);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(kelasAdapter);

    }
}
