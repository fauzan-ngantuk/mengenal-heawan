package com.fakuzan.mengenalhewan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fakuzan.mengenalhewan.interfaces.Playable;

public class DetailActivity extends AppCompatActivity implements Playable {

    ImageView imageView;
    TextView TvDeskripsiKelas;
    TextView TvDeskripsiHewan;
    Button BtnCekSuara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = (ImageView) findViewById(R.id.iv_gambar_hewan);
        TvDeskripsiKelas = (TextView) findViewById(R.id.tv_deskripsi_kelas);
        TvDeskripsiHewan = (TextView) findViewById(R.id.tv_deskripsi_hewan);
        BtnCekSuara = (Button) findViewById(R.id.btn_cek_suara);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String namaHewan = "";
        String gambarHewan = "";
        String deskripsiKelas = "";
        String deskripsiHewan = "";

        if (bundle != null) {
            namaHewan = (String) bundle.get(HewanActivity.NAMA_HEWAN).toString();
            gambarHewan = (String) bundle.get(HewanActivity.GAMBAR_HEWAN).toString();
            deskripsiKelas = (String) bundle.get(HewanActivity.DESKRIPSI_KELAS).toString();
            deskripsiHewan = (String) bundle.get(HewanActivity.DESKRIPSI_HEWAN).toString();
        }

        String suaraHewan = gambarHewan;
        final int rawId = getApplicationContext().getResources().getIdentifier(suaraHewan, "raw", getApplicationContext().getPackageName());

        BtnCekSuara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play(getApplicationContext(), rawId);
            }
        });

        if (rawId==0){
            BtnCekSuara.setEnabled(false);
            BtnCekSuara.setBackgroundColor(Color.GRAY);
        }

        getSupportActionBar().setTitle(namaHewan);

        imageView.setImageDrawable(getDrawable(this.getResources().getIdentifier(gambarHewan, "drawable", this.getPackageName())));
        TvDeskripsiKelas.setText(deskripsiKelas);
        TvDeskripsiHewan.setText(deskripsiHewan);

    }

    @Override
    public void play(Context context, int audio) {
        final int raw = audio;
        final Context appContext = context;
        new Thread(new Runnable() {
            @Override
            public void run() {
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer = MediaPlayer.create(appContext, raw);
                mediaPlayer.setLooping(false);
                mediaPlayer.start();
            }
        }).start();
    }
}
