package com.fakuzan.mengenalhewan;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

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

        final String suaraHewan = gambarHewan;

        getSupportActionBar().setTitle(namaHewan);
        imageView.setImageDrawable(getDrawable(this.getResources().getIdentifier(gambarHewan, "drawable", this.getPackageName())));
        TvDeskripsiKelas.setText(deskripsiKelas);
        TvDeskripsiHewan.setText(deskripsiHewan);

        BtnCekSuara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                int rawId = context.getResources().getIdentifier(suaraHewan, "raw", context.getPackageName());
                playSound(context, rawId);
            }
        });

    }

    private void playSound(final Context context, int audio){
        final int raw = audio;
        new Thread(new Runnable() {
            @Override
            public void run() {
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer = MediaPlayer.create(context, raw);
                mediaPlayer.setLooping(false);
                mediaPlayer.start();
            }
        }).start();
    }

}
