package com.duyle.buoi1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { // main thread - luong xu ly giao dien
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // long task - ket noi DB / xu ly tac vu mang

        TextView tv1 = findViewById(R.id.tv1);

        ProgressBar progressBar = findViewById(R.id.progress);

        Thread thread = new Thread(new Runnable() { // luong thu 2
            @Override
            public void run() {
                for (int i = 0; i < 10000000; i++) {
                    Log.d("LongTask", i + "");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);

                        tv1.setText("MD17301 - MOB403");
                    }
                });

            }
        });

        AsyncTask<Void, Integer, String> asyncTask = new AsyncTask<Void, Integer, String>() {
            @Override
            protected String doInBackground(Void... voids) {

                for (int i = 1; i < 10000000; i++) {
                    Log.d("LongTask", i + "");

                    if (i % 1000000 == 0) {
                        publishProgress((i / 1000000) * 10);
                    }

                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                progressBar.setVisibility(View.GONE);

                tv1.setText("MD17301 - MOB403");
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);

                progressBar.setProgress(values[0]);
            }
        };

        asyncTask.execute();

        //thread.start();

        // xu ly don luong

    }
}