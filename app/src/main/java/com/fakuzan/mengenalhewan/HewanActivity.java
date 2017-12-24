package com.fakuzan.mengenalhewan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fakuzan.mengenalhewan.adapter.HewanAdapter;
import com.fakuzan.mengenalhewan.adapter.KelasAdapter;
import com.fakuzan.mengenalhewan.database.HewanHelper;
import com.fakuzan.mengenalhewan.interfaces.CustomItemClickListener;
import com.fakuzan.mengenalhewan.model.Amfibi;
import com.fakuzan.mengenalhewan.model.Aves;
import com.fakuzan.mengenalhewan.model.Hewan;
import com.fakuzan.mengenalhewan.model.HewanDb;
import com.fakuzan.mengenalhewan.model.Insect;
import com.fakuzan.mengenalhewan.model.Mamalia;
import com.fakuzan.mengenalhewan.model.Reptil;

import java.util.ArrayList;

public class HewanActivity extends AppCompatActivity {

    public static final String NAMA_HEWAN = "nama_hewan";
    public static final String GAMBAR_HEWAN = "gambar_hewan";
    public static final String DESKRIPSI_KELAS = "deskripsi_kealas";
    public static final String DESKRIPSI_HEWAN = "deskripsi_hewan";

    RecyclerView recyclerView;
    ArrayList<HewanDb> hewanDb = new ArrayList<>();
    ArrayList<Hewan> hewan = new ArrayList<>();
    HewanHelper hewanHelper = new HewanHelper(this);

    String namaKelas= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hewan);

        recyclerView = (RecyclerView) findViewById(R.id.rv_hewan);

        hewanHelper.open();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            namaKelas = (String) bundle.get(MainActivity.NAMA_KELAS).toString();
        }

        getSupportActionBar().setTitle("Hewan " + namaKelas );

        if (namaKelas.equals("Mamalia")) {
            hewanDb = hewanHelper.getAllDataByJenis("mamalia");
            for (int i = 0; i < hewanDb.size(); i++) {
                hewan.add(new Mamalia(hewanDb.get(i).getNama(),
                        hewanDb.get(i).getDeskripsi(),
                        hewanDb.get(i).getSuara(),
                        hewanDb.get(i).getGambar(),
                        getString(R.string.deskripsi_mamalia)
                        ));
            }
        }
        else if (namaKelas.equals("Amfibi")){
            hewanDb = hewanHelper.getAllDataByJenis("amfibi");
            for (int i = 0; i < hewanDb.size(); i++) {
                hewan.add(new Amfibi(hewanDb.get(i).getNama(),
                        hewanDb.get(i).getDeskripsi(),
                        hewanDb.get(i).getSuara(),
                        hewanDb.get(i).getGambar(),
                        getString(R.string.deskripsi_amfibi)
                ));
            }
        }
        else if (namaKelas.equals("Aves")){
            hewanDb = hewanHelper.getAllDataByJenis("aves");
            for (int i = 0; i < hewanDb.size(); i++) {
                hewan.add(new Aves(hewanDb.get(i).getNama(),
                        hewanDb.get(i).getDeskripsi(),
                        hewanDb.get(i).getSuara(),
                        hewanDb.get(i).getGambar(),
                        getString(R.string.deskripsi_aves)
                ));
            }
        }
        else if (namaKelas.equals("Insect")){
            hewanDb = hewanHelper.getAllDataByJenis("insect");
            for (int i = 0; i < hewanDb.size(); i++) {
                hewan.add(new Insect(hewanDb.get(i).getNama(),
                        hewanDb.get(i).getDeskripsi(),
                        hewanDb.get(i).getSuara(),
                        hewanDb.get(i).getGambar(),
                        getString(R.string.insect)
                ));
            }
        }
        else if (namaKelas.equals("Reptil")){
            hewanDb = hewanHelper.getAllDataByJenis("reptil");
            for (int i = 0; i < hewanDb.size(); i++) {
                hewan.add(new Reptil(hewanDb.get(i).getNama(),
                        hewanDb.get(i).getDeskripsi(),
                        hewanDb.get(i).getSuara(),
                        hewanDb.get(i).getGambar(),
                        getString(R.string.deskripsi_reptil)
                ));
            }
        }

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        HewanAdapter hewanAdapter = new HewanAdapter(this, hewan, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                String namaHewan = hewan.get(position).getNama();
                String gambarHewan = hewan.get(position).getGambar();
                String deskripsiHewan = hewan.get(position).getDeskripsi();
                String deskripsiKelas = "";
                try {
                    if (hewan.get(position).getClass().isAssignableFrom(Class.forName(Amfibi.class.getName()))){
                        deskripsiKelas = ((Amfibi) hewan.get(position)).getDeskripsiKelas();
                    }
                    if (hewan.get(position).getClass().isAssignableFrom(Class.forName(Aves.class.getName()))){
                        deskripsiKelas = ((Aves) hewan.get(position)).getDeskripsiKelas();
                    }
                    if (hewan.get(position).getClass().isAssignableFrom(Class.forName(Insect.class.getName()))){
                        deskripsiKelas = ((Insect) hewan.get(position)).getDeskripsiKelas();
                    }
                    if (hewan.get(position).getClass().isAssignableFrom(Class.forName(Reptil.class.getName()))){
                        deskripsiKelas = ((Reptil) hewan.get(position)).getDeskripsiKelas();
                    }
                    if (hewan.get(position).getClass().isAssignableFrom(Class.forName(Mamalia.class.getName()))){
                        deskripsiKelas = ((Mamalia) hewan.get(position)).getDeskripsiKelas();
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(HewanActivity.this, DetailActivity.class);
                intent.putExtra(NAMA_HEWAN, namaHewan);
                intent.putExtra(GAMBAR_HEWAN, gambarHewan);
                intent.putExtra(DESKRIPSI_KELAS, deskripsiKelas);
                intent.putExtra(DESKRIPSI_HEWAN, deskripsiHewan);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(hewanAdapter);

    }
}
