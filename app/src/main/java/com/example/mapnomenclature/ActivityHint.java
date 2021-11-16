package com.example.mapnomenclature;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityHint extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        goToNewPage();
    }

    public void goToNewPage()
    {
        Button btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(v -> ActivityHint.this.finish());
    }
}