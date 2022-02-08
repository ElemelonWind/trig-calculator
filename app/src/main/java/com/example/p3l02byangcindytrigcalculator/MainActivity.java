package com.example.p3l02byangcindytrigcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    TextView input;
    RadioButton degree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        degree = findViewById(R.id.degree);
    }
    
    public void solve(View view) {
        String type = view.getTag().toString();
        String text = input.getText().toString();
        System.out.println(text);
        try {
            double num = Double.parseDouble(text);
            double result;
            boolean isDegree = degree.isChecked();
            switch(type) {
                case "sin":
                    if(isDegree) num = Math.toRadians(num);
                    result = Math.sin(num);
                    break;
                case "cos":
                    if(isDegree) num = Math.toRadians(num);
                    result = Math.cos(num);
                    break;
                case "tan":
                    if(isDegree) num = Math.toRadians(num);
                    result = Math.tan(num);
                    break;
                case "arcsin":
                    result = Math.asin(num);
                    break;
                case "arccos":
                    result = Math.acos(num);
                    break;
                case "arctan":
                    result = Math.atan(num);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + type);
            }
            if(Double.isNaN(result)) {
                input.setText("ERROR");
            } else {
                if(type.substring(0, 3).equals("arc") && isDegree) result = Math.toRadians(result);
                result = round(result, 3);
                input.setText(result + "");
            }
        } catch(Exception e) {
            input.setText("please enter a NUMBER");
            return;
        }
    }

    public void clear(View view) {
        input.setText("0.0");
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}