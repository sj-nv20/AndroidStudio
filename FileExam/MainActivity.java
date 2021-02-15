package com.example.fileexam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button save_btn, load_btn, raw_load_btn, sd_load_btn, mkdir_btn, rmdir_btn;
        final EditText raw_edt, sd_edt;

        save_btn = (Button)findViewById(R.id.save_btn);
        load_btn = (Button)findViewById(R.id.load_btn);
        raw_load_btn = (Button)findViewById(R.id.raw_load_btn);
        raw_edt = (EditText)findViewById(R.id.raw_edt);
        sd_load_btn = (Button)findViewById(R.id.sd_load_btn);
        sd_edt = (EditText)findViewById(R.id.sd_edt);
        mkdir_btn = (Button)findViewById(R.id.mkdir_btn);
        rmdir_btn = (Button)findViewById(R.id.rmdir_btn);

        ActivityCompat.requestPermissions(this,
                new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 999);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream output = openFileOutput("file.txt", Context.MODE_PRIVATE);
                    String str = "내장 메모리 파일 저장 테스트";
                    output.write(str.getBytes());
                    output.close();
                    Toast.makeText(getApplicationContext(), "file.txt가 생성되었습니다.",Toast.LENGTH_SHORT).show();
                }catch (IOException e) { }
            }
        });

        load_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FileInputStream input = openFileInput("file.txt");
                    byte[] text = new byte[100];
                    input.read(text);
                    String str = new String(text);
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                    input.close();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "파일없음", Toast.LENGTH_SHORT).show();
                }
            }
        });

        raw_load_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    InputStream inputS = getResources().openRawResource(R.raw.raw_test);
                    byte[] txt = new byte[inputS.available()];
                    inputS.read(txt);
                    raw_edt.setText(new String(txt));
                    inputS.close();
                }catch (IOException e) {
                }
            }
        });

        sd_load_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FileInputStream inFs = new FileInputStream("/sdcard/sd_test.txt");
                    byte[] txt = new byte[inFs.available()];
                    inFs.read(txt);
                    sd_edt.setText(new String(txt));
                    inFs.close();
                }catch (IOException e) {
                }
            }
        });

        final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        final File myDir = new File(strSDpath + "/mkdir");

        mkdir_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myDir.mkdir();
            }
        });

        rmdir_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myDir.delete();
            }
        });
    }
}