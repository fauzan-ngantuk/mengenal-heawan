package com.fakuzan.mengenalhewan;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.fakuzan.mengenalhewan.database.HewanHelper;
import com.fakuzan.mengenalhewan.model.HewanDb;
import com.fakuzan.mengenalhewan.preference.AppPreference;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PreLoadActivity extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_load);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        new LoadData().execute();

    }

    private class LoadData extends AsyncTask<Void, Integer, Void> {

        final String TAG = LoadData.class.getSimpleName();
        HewanHelper hewanHelper;
        AppPreference appPreference;
        double progress;
        double maxprogress = 100;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            hewanHelper = new HewanHelper(PreLoadActivity.this);
            appPreference = new AppPreference(PreLoadActivity.this);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Boolean firstRun = appPreference.getFirstRun();

            if (firstRun) {
                ArrayList<HewanDb> hewanDbs = preLoadRaw();

                hewanHelper.open();

                progress = 30;
                publishProgress((int) progress);
                Double progressMaxInsert = 80.0;
                Double progressDiff = (progressMaxInsert - progress) / hewanDbs.size();


                /*
                Gunakan ini untuk query insert yang transactional
                 */
//                hewanHelper.beginTransaction();

                try {
                    for (HewanDb model : hewanDbs) {
                        hewanHelper.insert(model);
                        progress += progressDiff;
                        publishProgress((int) progress);
                    }
                    // Jika semua proses telah di set success maka akan di commit ke database
                    hewanHelper.setTransactionSuccess();
                } catch (Exception e) {
                    // Jika gagal maka do nothing
                    Log.e(TAG, "doInBackground: Exception");
                }
//                hewanHelper.endTransaction();

                // Close helper ketika proses query sudah selesai
                hewanHelper.close();

                /*
                Set preference first run ke false
                Agar proses preload tidak dijalankan untuk kedua kalinya
                */
                appPreference.setFirstRun(false);

                publishProgress((int) maxprogress);

            } else {
                try {
                    synchronized (this) {
                        this.wait(2000);

                        publishProgress(50);

                        this.wait(2000);
                        publishProgress((int) maxprogress);
                    }
                } catch (Exception e) {
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent i = new Intent(PreLoadActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }

    }

    public ArrayList<HewanDb> preLoadRaw() {
        ArrayList<HewanDb> hewanDbs = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try {
            Resources res = getResources();
            InputStream raw_hewan = res.openRawResource(R.raw.hewan);

            reader = new BufferedReader(new InputStreamReader(raw_hewan));
            int count = 0;
            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");

                HewanDb hewanDb;

                hewanDb = new HewanDb(splitstr[0], splitstr[1], splitstr[2], splitstr[3], splitstr[4]);
                hewanDbs.add(hewanDb);
                count++;
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hewanDbs;
    }

}
