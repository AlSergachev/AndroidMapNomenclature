package com.example.mapnomenclature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class WorkingActivity extends AppCompatActivity implements View.OnClickListener {

    EditText f_deg, f_min, f_sec, s_deg, s_min, s_sec;
    int x_deg, x_min, y_deg, y_min;
    double x_sec, y_sec;
    Spinner scale;
    Button btn_calculation;
    double x, y;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working);

        f_deg = findViewById(R.id.first_deg);
        f_min = findViewById(R.id.first_min);
        f_sec = findViewById(R.id.first_sec);
        s_deg = findViewById(R.id.second_deg);
        s_min = findViewById(R.id.second_min);
        s_sec = findViewById(R.id.second_sec);
        scale = findViewById(R.id.spinner);
        btn_calculation = findViewById(R.id.btn_calculation);

        btn_calculation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        getCoordinates();

        if (checkCoordinates())
        {
            this.transformationOfCoordinates();
            int selected = scale.getSelectedItemPosition();
            Intent intent = new Intent("android.intent.action.ResultActivity");
            intent.putExtra("X", x);
            intent.putExtra("Y", y);
            intent.putExtra("NUMBER_SCALE", selected);
            startActivity(intent);
        }
        else
        {
            String str = "Incorrect coordinate value entered. \n Введено некорректное значение координат";
            Toast toast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void getCoordinates()
    {
        x_deg = Integer.parseInt(f_deg.getText().toString());
        x_min = Integer.parseInt(f_min.getText().toString());
        x_sec = Double.parseDouble(f_sec.getText().toString());
        y_deg = Integer.parseInt(s_deg.getText().toString());
        y_min = Integer.parseInt(s_min.getText().toString());
        y_sec = Double.parseDouble(s_sec.getText().toString());
    }

    public boolean checkCoordinates()
    {
        return (x_deg >= 32 && x_deg < 80) && (x_min < 60) && (x_sec < 60)
                && (y_deg >= 0 && y_deg < 180) && (y_min < 60) && (y_sec < 60);
    }

    public void transformationOfCoordinates()
    {
        x = x_deg + (x_min / 60.) + (x_sec / 3600.);
        y = y_deg + (y_min / 60.) + (y_sec / 3600.);
    }
}