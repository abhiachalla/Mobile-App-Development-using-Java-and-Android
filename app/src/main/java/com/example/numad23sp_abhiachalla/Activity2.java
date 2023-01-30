package com.example.numad23sp_abhiachalla;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);


    }

    public void clickyButton(View view) {
        String text = "Pressed: ";
        switch(view.getId()) {
            case R.id.button_A:
                text+="A";
                break;
            case R.id.button_B:
                text+="B";
                break;
            case R.id.button_C:
                text+="C";
                break;
            case R.id.button_D:
                text+="D";
                break;
            case R.id.button_E:
                text+="E";
                break;
            case R.id.button_F:
                text+="F";
                break;
        }
        TextView textView = findViewById(R.id.textView);
        textView.setText(text);
    }
}
