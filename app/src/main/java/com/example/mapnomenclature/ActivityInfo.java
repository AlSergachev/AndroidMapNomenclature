package com.example.mapnomenclature;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class ActivityInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        goToNewPage();
    }

    public void goToNewPage()
    {
        Button btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(v -> ActivityInfo.this.finish());
    }

}