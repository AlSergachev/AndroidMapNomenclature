package com.example.mapnomenclature;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    double x, y;
    int scale;

    public TextView tv_north, tv_south, tv_west, tv_east,
            tv_latitude, tv_longitude, tv_scale, tv_nomenclature;

    static String str_north, str_south, str_west, str_east;

    enum SCALE {
        ONE_MILLION,
        ONE_HUNDRED_THOUSAND,
        FIFTY_THOUSAND,
        TWENTY_FIVE_THOUSAND,
        TEN_THOUSAND,
        FIVE_THOUSAND,
        TWO_THOUSAND
    }

    @SuppressLint("SetTextI18n")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        x = intent.getDoubleExtra("X", 1.0);
        y = intent.getDoubleExtra("Y", 1.0);
        scale = intent.getIntExtra("NUMBER_SCALE", 1);

        tv_east = findViewById(R.id.tv_east);
        tv_west = findViewById(R.id.tv_west);
        tv_south = findViewById(R.id.tv_south);
        tv_north = findViewById(R.id.tv_north);
        tv_latitude = findViewById(R.id.tv_latitude);
        tv_longitude = findViewById(R.id.tv_longitude);
        tv_scale = findViewById(R.id.tv_scale);
        tv_nomenclature = findViewById(R.id.tv_nomenclature);

        @SuppressLint("DefaultLocale") String str_x = String.format("%.5f", x);
        @SuppressLint("DefaultLocale") String str_y = String.format("%.5f", y);
        String latitude = "B = " + str_x;
        String longitude = "L = " + str_y;
        tv_latitude.setText(latitude);
        tv_longitude.setText(longitude);

        One_million MyMapOM = new One_million(4, 6);
        int N2 = MyMapOM.setColumnNumber(y);
        char N1 = MyMapOM.setStringNumber(x);
        double east2 = MyMapOM.setBorder1m(x, y, "east");
        double north2 = MyMapOM.setBorder1m(x, y, "north");

        One_hundred_thousand MyMapOHT = new One_hundred_thousand(1./3, 0.5);
        int N3 = MyMapOHT.setSquareNumber(x, y, north2, east2, 12);
        double north3 = MyMapOHT.setBorder(x, y, north2, east2, 12, "north");
        double east3 = MyMapOHT.setBorder(x, y, north2, east2, 12, "east");

        Fifty_thousand MyMapFfT = new Fifty_thousand(1./6, 0.25);
        char N4 = MyMapFfT.setSquareNumber50th(x, y, north3, east3);
        double north4 = MyMapFfT.setBorder(x, y, north3, east3, 2, "north");
        double east4 = MyMapFfT.setBorder(x, y, north3, east3, 2, "east");

        Twenty_five_thousand MyMapTFT = new Twenty_five_thousand(1./12, 1./8);
        char N5 = MyMapTFT.setSquareNumber25th(x, y, north4, east4);
        double north5 = MyMapTFT.setBorder(x, y, north4, east4, 2, "north");
        double east5 = MyMapTFT.setBorder(x, y, north4, east4, 2, "east");

        Ten_thousand MyMapTT = new Ten_thousand(1./24, 1./16);
        int N6 = MyMapTT.setSquareNumber(x, y, north5, east5, 2);

        Five_thousand MyMapFvT = new Five_thousand(1./48, 1./32);
        int n4 = MyMapFvT.setSquareNumber(x, y, north3, east3, 16);
        double north_n4 = MyMapFvT.setBorder(x, y, north3, east3, 16, "north");
        double east_n4 = MyMapFvT.setBorder(x, y, north3, east3, 16, "east");

        Two_thousand MyMapTwT = new Two_thousand(1./144, 1./96);
        char n5 = MyMapTwT.setSquareNumber2th(x, y, north_n4, east_n4);

        String QN1 = Character.toString(N1);
        String QN2 = Integer.toString(N2);
        String QN_One_million = QN1 + "-" + QN2;
        String QN3 = Integer.toString(N3);
        String QN_One_hundred_thousand = QN_One_million + "-" + QN3;
        String QN4 = Character.toString(N4);
        String QN_Fifty_thousand = QN_One_hundred_thousand + "-" + QN4;
        String QN5 = Character.toString(N5);
        String QN_Twenty_five_thousand = QN_Fifty_thousand + "-" + QN5;
        String QN6 = Integer.toString(N6);
        String QN_Ten_thousand = QN_Twenty_five_thousand + "-" + QN6;

        String qn4 = Integer.toString(n4);
        String QN_Five_thousand = QN_One_hundred_thousand + "(" + qn4 + ")";

        String qn5 = Character.toString(n5);
        String QN_Two_thousand = QN_One_hundred_thousand + "(" + qn4 + "-" + qn5 + ")";

        if (scale == SCALE.ONE_MILLION.ordinal())
        {
            tv_scale.setText("M 1 : 1 000 000");
            tv_nomenclature.setText(QN_One_million);
            MyMapOM.getBorder1m(x, y);
            setTextToTextView(str_north,
                    str_south,
                    str_west,
                    str_east);
        }
        else if (scale == SCALE.ONE_HUNDRED_THOUSAND.ordinal())
        {
            tv_scale.setText("M 1 : 100 000");
            tv_nomenclature.setText(QN_One_hundred_thousand);
            MyMapOHT.getBorder(x, y, north2, east2, 12);
            setTextToTextView(str_north,
                    str_south,
                    str_west,
                    str_east);
        }
        else if (scale == SCALE.FIFTY_THOUSAND.ordinal())
        {
            tv_scale.setText("M 1 : 50 000");
            tv_nomenclature.setText(QN_Fifty_thousand);
            MyMapFfT.getBorder(x, y, north3, east3, 2);
            setTextToTextView(str_north,
                    str_south,
                    str_west,
                    str_east);
        }
        else if (scale == SCALE.TWENTY_FIVE_THOUSAND.ordinal())
        {
            tv_scale.setText("M 1 : 25 000");
            tv_nomenclature.setText(QN_Twenty_five_thousand);
            MyMapTFT.getBorder(x, y, north4, east4, 2);
            setTextToTextView(str_north,
                    str_south,
                    str_west,
                    str_east);
        }
        else if (scale == SCALE.TEN_THOUSAND.ordinal())
        {
            tv_scale.setText("M 1 : 10 000");
            tv_nomenclature.setText(QN_Ten_thousand);
            MyMapTT.getBorder(x, y, north5, east5, 2);
            setTextToTextView(str_north,
                    str_south,
                    str_west,
                    str_east);
        }
        else if (scale == SCALE.FIVE_THOUSAND.ordinal())
        {
            tv_scale.setText("M 1 : 5 000");
            tv_nomenclature.setText(QN_Five_thousand);
            MyMapFvT.getBorder(x, y, north3, east3, 16);
            setTextToTextView(str_north,
                    str_south,
                    str_west,
                    str_east);
        }
        else if (scale == SCALE.TWO_THOUSAND.ordinal())
        {
            tv_scale.setText("M 1 : 2 000");
            tv_nomenclature.setText(QN_Two_thousand);
            MyMapTwT.getBorder(x, y, north_n4, east_n4, 3);
            setTextToTextView(str_north,
                    str_south,
                    str_west,
                    str_east);
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Scale selection error.", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    public void setTextToTextView(String n, String s, String w, String e)
    {
        tv_north.setText(n);
        tv_south.setText(s);
        tv_west.setText(w);
        tv_east.setText(e);
    }

    public void coordinateTransformation(double value, String side)
    {
        String t_v_min, t_v_sec;

        int v_grad = (int) Math.floor(value);
        int v_min = (int) Math.floor((value - v_grad) * 60);
        int v_sec = (int) Math.round((((value - v_grad) * 60) - v_min) * 60);

        if (v_sec == 60){v_min += 1; v_sec = 0;}

        String t_v_grad = Integer.toString(v_grad);

        if (v_min < 10)
            t_v_min = "0" + (v_min);
        else
            t_v_min = Integer.toString(v_min);

        if (v_sec < 10)
            t_v_sec = "0" + (v_sec);
        else
            t_v_sec = Integer.toString(v_sec);

        if (side.equals("north"))
        {
            str_north = t_v_grad + "°" + t_v_min + '\'' + t_v_sec +'\"';
        }
        else if (side.equals("south"))
        {
            str_south = t_v_grad + "°" + t_v_min + '\'' + t_v_sec +'\"';
        }
        else if (side.equals("east"))
        {
            str_east = t_v_grad + "°" + t_v_min + '\'' + t_v_sec +'\"';
        }
        else if (side.equals("west"))
        {
            str_west = t_v_grad + "°" + t_v_min + '\'' + t_v_sec +'\"';
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Coordinates output error", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
}

class One_million extends ResultActivity
{
    protected double sx, sy;
    protected int column_number, string_number, z, m, n, sheet_size;
    protected double north_result, south_result, west_result, east_result;

    public One_million(double m_sx, double m_sy) {
        sx = m_sx; sy = m_sy; column_number = 0;
        string_number = 0; z = 0; m = 0; n = 0; sheet_size = 0;
    }



    char setStringNumber(double x)        //char setStringNumber(double x, double y)
    {
        while (m < 12)
        {
            double north = (80 - sx * m);
            double south = (80 - sx - sx * m);
            if (x >= south && x <= north) {
                m++;
                north_result = north;
                string_number = m;
            }
            else {
                m++;
            }
        }
        m = 0;
        char[] a = new char[]{'T', 'S', 'R', 'Q', 'P', 'O', 'N', 'M', 'L', 'K', 'J', 'I'};
        return a[string_number - 1];
    }

    int setColumnNumber(double y)         //int setColumnNumber(double x, double y)
    {
        while(n < 60)
        {
            double west = (0 + sy + n * sy);
            double east = (0 + n * sy);
            if (y >= east && y <= west) {
                n++;
                east_result = east;
                column_number = 30 + n;
            }
            else {
                n++;
            }
        }
        n = 0;
        return column_number;
    }

    double setBorder1m(double x, double y, String side)
    {
        while(n < 60)
        {
            double west = (0 + sy + n * sy);
            double east = (0 + n * sy);
            if (y >= east && y <= west) {
                n++;
                west_result = west;
                east_result = east;
                break;
            }
            else {
                n++;
            }
        }
        while(m < 12)
        {
            double north = (80 - sx * m);
            double south = (80 - sx - sx * m);
            if (x >= south && x <= north) {
                m++;
                north_result = north;
                south_result = south;
                break;
            }
            else {
                m++;
            }
        }
        m = 0;
        n = 0;
        if (side.equals("north"))
            return north_result;
        else if (side.equals("east"))
            return east_result;
        else if (side.equals("west"))
            return west_result;
        else if (side.equals("south"))
            return south_result;
        else
            return 0;
    }

    void getBorder1m(double x, double y)
    {
        double north_border = setBorder1m(x, y, "north");
        double south_border = setBorder1m(x, y, "south");
        double west_border = setBorder1m(x, y, "west");
        double east_border = setBorder1m(x, y, "east");
        super.coordinateTransformation(north_border, "north");
        coordinateTransformation(south_border, "south");
        coordinateTransformation(west_border, "west");
        coordinateTransformation(east_border, "east");
    }

}

class One_hundred_thousand extends One_million
{
    int square_number = 0;

    public One_hundred_thousand(double m_sx, double m_sy){super(m_sx,m_sy);}

    int setSquareNumber(double x, double y, double N, double E, int sheet_size)
    {
        while (m < sheet_size) {
            while (n < sheet_size) {
                double north = (N - sx * m);
                double south = (N - sx - sx * m);
                double west = (E + sy + n * sy);
                double east = (E + n * sy);
                if (y >= east && y <= west && x >= south && x <= north) {
                    n++;
                    square_number = z + n;
                }
                else {
                    n++;
                }
            }
            z += n;
            n = 0;
            m++;
        }
        m = 0;
        n = 0;
        return square_number;
    }

    double setBorder(double x, double y, double N, double E, int sheet_size, String side)
    {
        while (m < sheet_size) {
            while (n < sheet_size) {
                double north = (N - sx * m);
                double south = (N - sx - sx * m);
                double west = (E + sy + n * sy);
                double east = (E + n * sy);
                if (y >= east && y <= west && x >= south && x <= north) {
                    n++;
                    north_result = north;
                    south_result = south;
                    west_result = west;
                    east_result = east;
                    break;
                }
                else {
                    n++;
                }
            }
            z += n;
            n = 0;
            m++;
        }
        m = 0;
        n = 0;
        if (side.equals("north"))
            return north_result;
        else if (side.equals("east"))
            return east_result;
        else if (side.equals("west"))
            return west_result;
        else if (side.equals("south"))
            return south_result;
        else
            return 0;
    }

    void getBorder(double x, double y, double N, double E, int sheet_size)
    {
        double north_border = setBorder(x, y, N, E, sheet_size, "north");
        double south_border = setBorder(x, y, N, E, sheet_size, "south");
        double west_border = setBorder(x, y, N, E, sheet_size, "west");
        double east_border = setBorder(x, y, N, E, sheet_size, "east");
        coordinateTransformation(north_border, "north");
        coordinateTransformation(south_border, "south");
        coordinateTransformation(west_border, "west");
        coordinateTransformation(east_border, "east");
    }
}

class Fifty_thousand extends One_hundred_thousand
{
    Fifty_thousand(double m_sx, double m_sy){super(m_sx,m_sy);}

    char setSquareNumber50th(double x, double y, double N, double E)
    {
        while (m < 2) {
            while (n < 2) {
                double north = (N - sx * m);
                double south = (N - sx - sx * m);
                double west = (E + sy + n * sy);
                double east = (E + n * sy);
                if (y >= east && y <= west && x >= south && x <= north) {
                    n++;
                    square_number = z + n;
                }
                else {
                    n++;
                }
            }
            z += n;
            n = 0;
            m++;
        }
        char[] a = new char[]{'А', 'Б', 'В', 'Г'};
        m = 0;
        n = 0;
        return a[square_number - 1];
    }
}

class Twenty_five_thousand extends Fifty_thousand
{
    Twenty_five_thousand(double m_sx, double m_sy){super(m_sx,m_sy);}

    char setSquareNumber25th(double x, double y, double N, double E)
    {
        while (m < 2) {
            while (n < 2) {
                double north = (N - sx * m);
                double south = (N - sx - sx * m);
                double west = (E + sy + n * sy);
                double east = (E + n * sy);
                if (y >= east && y <= west && x >= south && x <= north) {
                    n++;
                    square_number = z + n;
                }
                else {
                    n++;
                }
            }
            z += n;
            n = 0;
            m++;
        }
        char[] a = new char[]{'а', 'б', 'в', 'г'};
        m = 0;
        n = 0;
        return a[square_number - 1];
    }
}

class Ten_thousand extends Twenty_five_thousand
{
    Ten_thousand(double m_sx, double m_sy){super(m_sx,m_sy);}
}

class Five_thousand extends One_hundred_thousand
{
    Five_thousand(double m_sx, double m_sy){super(m_sx,m_sy);}
}

class Two_thousand extends Five_thousand
{
    Two_thousand(double m_sx, double m_sy){super(m_sx,m_sy);}

    char setSquareNumber2th(double x, double y, double N, double E)
    {
        while (m < 3) {
            while (n < 3) {
                double north = (N - sx * m);
                double south = (N - sx - sx * m);
                double west = (E + sy + n * sy);
                double east = (E + n * sy);
                if (y >= east && y <= west && x >= south && x <= north) {
                    n++;
                    square_number = z + n;
                }
                else {
                    n++;
                }
            }
            z += n;
            n = 0;
            m++;
        }
        char[] a = new char[]{'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и'};
        m = 0;
        n = 0;
        return a[square_number - 1];
    }
}