package com.example.mapnomenclature;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goToNewPage();
    }

    public void goToNewPage()
    {
        Button btn_start = (Button) findViewById(R.id.btn_start);
        Button btn_hint = (Button) findViewById(R.id.btn_hint);
        Button btn_info = (Button) findViewById(R.id.btn_info);

        btn_start.setOnClickListener(
                v -> {
                    Intent intent = new Intent("android.intent.action.WorkingActivity");
                    startActivity(intent);
                }
        );

        btn_hint.setOnClickListener(
                v -> {
                        Intent intent = new Intent("android.intent.action.ActivityHint");
                        startActivity(intent);
                }
        );

        btn_info.setOnClickListener(
                v -> {
                    Intent intent = new Intent("android.intent.action.ActivityInfo");
                    startActivity(intent);
                }
        );
    }
}