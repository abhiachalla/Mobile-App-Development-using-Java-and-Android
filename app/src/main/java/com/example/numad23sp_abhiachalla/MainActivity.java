package com.example.numad23sp_abhiachalla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button_send);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Intent aboutMeIntent = new Intent(getApplicationContext(), AboutMeActivity.class);
                startActivity(aboutMeIntent);

                //sendMessage();


            }
        });

        Button buttonOne = findViewById(R.id.button_clicky);
        buttonOne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity2Intent = new Intent(getApplicationContext(), Activity2.class);
                startActivity(activity2Intent);
            }
        });


        Button linkCollector = findViewById(R.id.link_collector);
        linkCollector.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent linkCollectorIntent = new Intent(getApplicationContext(), LinkCollector.class);
                startActivity(linkCollectorIntent);
            }
        });
    }
    public void sendMessage() {
//        Context context = getApplicationContext();
//        CharSequence text = "Abhi Achalla achalla.a@northeastern.edu!";
//        int duration = Toast.LENGTH_SHORT;
//
//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();
    }


}