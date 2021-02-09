package com.example.startserviceexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    Button btn_start, btn_stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Started Service Exam");

        intent = new Intent(getApplicationContext(), MusicService.class);

        btn_start = (Button)findViewById(R.id.start_music_btn);
        btn_stop = (Button)findViewById(R.id.stop_music_btn);

        btn_start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startService(intent);
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stopService(intent);
                android.util.Log.i("ServiceTest", "stopService()");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        stopService(intent);
        android.util.Log.i("ServiceTest","stopService() onDestroy()");
    }
}