package com.example.explntentexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("영화 선호도 투표");

        final int voteCount[] = new int[9];
        for(int i = 0; i < 9; i++)
            voteCount[i] = 0;

        ImageView image[] = new ImageView[9];

        int imageId[] = {R.id.iv1, R.id.iv2, R.id.iv3,
                         R.id.iv4, R.id.iv5, R.id.iv6,
                         R.id.iv7, R.id.iv8, R.id.iv9 };

        final String imgName[] = {"괴물", "웰컴투 동막골", "흑성탈출",
                                  "아저씨", "아바타", "매트릭스",
                                  "AI", "글래디에이터", "레옹" };

        for( int i = 0; i < imageId.length; i++) {
            final int index;
            index = i;
            image[index] = (ImageView)findViewById(imageId[index]);

            image[index].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    voteCount[index]++;
                    Toast.makeText(getApplicationContext(),
                            imgName[index]+":총"+voteCount[index]+"표",Toast.LENGTH_SHORT).show();
                }
            });
        }

        Button btnFinish = (Button) findViewById(R.id.btnResult);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("VoteCount", voteCount);
                intent.putExtra("ImageName", imgName);
                startActivity(intent);
            }
        });
    }

}